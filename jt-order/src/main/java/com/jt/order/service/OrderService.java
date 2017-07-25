package com.jt.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.Order;

@Service
public class OrderService{
	@Autowired
	private OrderMapper orderMapper;
	
	public Order queryById(String orderId){
		return orderMapper.queryByOrderId(orderId);
	}
	/**
	 * 增加订单
	 * @param userId
	 * @param order
	 * @return
	 */
	public String create(Long userId,Order order){
		String orderId=userId+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		orderMapper.create(order);
		return orderId;
	}
	//根据昵称查询订单详情
	public List<Order> queryByBuyerNick(String buyerNick, Integer page, Integer count) {
		//先根据用户名查找所有的订单号，在根据订单号查询所有的订单信息
		String[] orderIds= orderMapper.selectByBuyerNick(buyerNick);
		List<Order> orderList= new ArrayList<Order>();
		for(String orderId:orderIds){
			Order order = orderMapper.queryByOrderId(orderId);
			orderList.add(order);
			
		}
		
		return orderList;
	}
	

}
