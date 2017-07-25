package com.jt.order.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	//创建订单  http://order.jt.com/order/create
	@RequestMapping("/create")
	@ResponseBody
	public String Create(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException{
		Order order = MAPPER.readValue(json, Order.class);
		Long userId=order.getUserId();
		String orderId = orderService.create(userId,order);
		return orderId;
		
	}
	//根据订单ID查询订单http://order.jt.com/order/query/71487577654381
	@RequestMapping("query/{orderId}")
	@ResponseBody
	public Order queryById(@PathVariable String orderId){
		return orderService.queryById(orderId);
		
	}
	/**
	 * 根据昵称分页查询订单
		请求方法	GET
		URL	http://order.jt.com/order/query/tony/1/5
		参数	/query/{buyerNick}/{page}/{count}

	 */
	@RequestMapping("/query/{buyerNick}/{page}/{count}")
	@ResponseBody
	public List<Order> queryByUserName(@PathVariable String buyerNick,@PathVariable Integer page,@PathVariable Integer count){
		return orderService.queryByBuyerNick(buyerNick,page,count);
		
	}
	
	

}
