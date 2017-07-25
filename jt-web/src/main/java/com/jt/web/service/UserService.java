package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.User;

@Service
public class UserService {
	@Autowired
	private HttpClientService httpClientService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String saveRegister(User user) throws Exception{
		String url = "http://sso.jt.com/user/register";
		Map<String,String> map = new HashMap();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getEmail());
		//jsonData代表SysResutl，但不能直接返回，SysResult写的不规范
		String jsonData = httpClientService.doPost(url, map);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		String username = jsonNode.get("data").asText();//从sysresult中获取data中的username
		return username;
	}

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public String doLogin(String username, String password) {
		String ticket=null;
		try {
			String url="http://sso.jt.com/user/login";
			Map<String,String> params = new HashMap<String,String>();
			params.put("u", username);
			params.put("p", password);
			String jsonData = httpClientService.doPost(url, params);
			ticket=MAPPER.readTree(jsonData).get("data").asText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

}
