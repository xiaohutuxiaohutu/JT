package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.service.RedisService;
import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.User;
import com.jt.web.threadlocal.UserThreadLocal;
import com.mysql.jdbc.StringUtils;

public class CartInterceptor implements HandlerInterceptor {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//先判断是否登录，从cookie中获取JT_TICKET是否能获取到，如果ticket为空，则没有登录，跳转到登录页面
		String ticket = CookieUtils.getCookieValue(request, "JT_TICKET");
		if(!StringUtils.isNullOrEmpty(ticket)){//ticket值存在
			//根据ticket值从redis中获取用户名
			String url="http://sso.jt.com/user/query/"+ticket;
			String jsonData = httpClientService.doGet(url);
			if(!StringUtils.isNullOrEmpty(jsonData)){
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				String userData=jsonNode.get("data").asText();
				User user = MAPPER.readValue(userData, User.class);
				UserThreadLocal.set(user);
				return true;
			}
		}
		response.sendRedirect("/user/login.html");
		return false;//false不放行
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
