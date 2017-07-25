package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
@Service
public class UserService extends BaseService<User> {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisService redisService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 用户注册检查
	 * @param val
	 * @param typeNum
	 * @return
	 */
	public SysResult check(String val,Integer typeNum){
		Map<String,Object> map = new HashMap<String,Object>();
		if(1==typeNum){
			map.put("colname", "username");
		}else if(2==typeNum){
			map.put("colname", "phone");
		}else if(3==typeNum){
			map.put("colname", "email");
		}else{
			return SysResult.build(201, "参数不对");
		}
		map.put("val", val);
		Integer i = userMapper.check(map);
		if(0==i){
			return SysResult.oK(false);
		}else{
			return SysResult.oK(true);
		}
		
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public String saveRegist(User user){
		user.setCreated(new Date());
		//因为页面不填写，随便写的值，防止数据库唯一校验出错
		user.setEmail("temp_"+user.getPhone());
		userMapper.insertSelective(user);
		return user.getUsername();
	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @throws JsonProcessingException
	 */
	public SysResult saveLogin(String username,String password) throws JsonProcessingException{
		//根据用户名查询，然后与密码进行比较，安全
		String ticket="";
		User user =new User();
		user.setUsername(username);
		User curUser = super.queryByWhere(user);
		System.out.println("curUsr="+curUser);
		if(null!=curUser){
			//进行密码比较
			String newPassword = DigestUtils.md5Hex(password);
			if(newPassword.equals(curUser.getPassword())){
				//3 生成ticket，唯一性，动态性，混淆
				ticket = DigestUtils.md5Hex(System.currentTimeMillis()+curUser.getUsername()+curUser.getId());
				//4 吧当前用户的信息放入redis,并设置生存时间
				redisService.set(ticket, MAPPER.writeValueAsString(curUser),60*60*12*7);
				return SysResult.oK(ticket);
			}
			return SysResult.build(201, "用户名或密码不存在", ticket);
		}else{
			return SysResult.build(201, "用户名或密码不存在", ticket);
		}
		//return ticket;
	}

}
