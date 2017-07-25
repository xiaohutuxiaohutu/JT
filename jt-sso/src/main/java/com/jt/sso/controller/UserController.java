package com.jt.sso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	//http://sso.jt.com/user/check/{param}/{type}  需求业务接口文档
	/**
	 * 1.检查数据是否可用
	 * @param param
	 * @param type
	 * @return
	 */
	@RequestMapping("check/{param}/{type}")
	@ResponseBody
	public SysResult check(@PathVariable String param,@PathVariable Integer type){
		/*Boolean b = userService.check(param, type);
		return SysResult.oK(b);	*/
		return userService.check(param, type);
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public SysResult regist(@Valid User user,BindingResult result){
		//校验数据合法性
		if(result.hasErrors()){
			List<ObjectError> errors =result.getAllErrors();
			List<String> myErrors = new ArrayList<String>();
			for(ObjectError objectError:errors){
				myErrors.add(objectError.getDefaultMessage());
			}
			return SysResult.build(202, StringUtils.join(myErrors,'|'));
		}
		try {
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			String username = userService.saveRegist(user);
			return SysResult.oK(username);
		} catch (Exception e) {
			return SysResult.build(201, "注册失败",user.getUsername());
		}
	}
	/**
	 * 登录
	 * @param u
	 * @param p
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody	
	public SysResult login(String u,String p){
		String ticket=null;
		try {
			//String ticket = userService.saveLogin(u,p);
			SysResult sr = userService.saveLogin(u, p);
			ticket = (String) sr.getData();
			return SysResult.oK(ticket);
		} catch (Exception e) {
			return SysResult.build(201, "登录失败",ticket);
		}
	}
	/**
	 * 通过ticket查询用户信息
	 * @param ticket
	 * @return
	 */
	@RequestMapping("/query/{ticket}")
	@ResponseBody	
	public SysResult queryTicket(@PathVariable String ticket){
		try {
			String userJson = redisService.get(ticket);
			return SysResult.oK(userJson);
		} catch (Exception e) {
			return SysResult.build(201, "查询失败");
		}
	}
	
	
	
}
