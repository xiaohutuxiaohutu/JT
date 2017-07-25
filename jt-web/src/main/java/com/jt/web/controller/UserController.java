package com.jt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//jdValidate.js 599行
	
	//跳转到登录页面
	@RequestMapping("/user/login")
	public String login(){
		return "login";
	}
	
	//跳转注册页面
	@RequestMapping("/user/register")
	public String register(){
		return "register";
	}
	
	//用户注册
	@RequestMapping("/user/doRegister")
	@ResponseBody
	public SysResult doRegister(User user) throws Exception{
		String username = userService.saveRegister(user);
		return SysResult.oK(username);
	}
	//用户登录
	@RequestMapping("user/doLogin")
	@ResponseBody
	public SysResult doLogin(String username,String password,HttpServletRequest request,HttpServletResponse response){
		/**
		 * 1 根据用户名查询用户信息
		 * 2 根据插到的信息比对密码，
		 * 3 如果正确将用户信心写入到cookie中
		 */
		String ticket = userService.doLogin(username,password);
		if(ticket!=null && ticket!=""){
			try {
				//String ticket = userService.doLogin(username,password);
				
				CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
				return SysResult.oK();
			} catch (Exception e) {
				return SysResult.build(201, "");
			}
		}else{
			return SysResult.build(201, "");
		}
		/*try {
			//String ticket = userService.doLogin(username,password);
			
			CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}	*/	
	}
	//登出
	@RequestMapping("/user/logout")
	public String Logout(HttpServletRequest request,HttpServletResponse response){
		
		CookieUtils.deleteCookie(request, response, "JT_TICKET");
		return "redirect:/index.html";
	}
	
	
}
