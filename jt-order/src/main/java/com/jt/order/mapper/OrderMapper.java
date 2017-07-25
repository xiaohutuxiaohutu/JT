package com.jt.order.mapper;

import java.util.Date;

import com.jt.order.pojo.Order;

public interface OrderMapper{
	//根据id查询
	public Order queryByOrderId(String oderId);
	//新增订单
	public void create(Order order);
	//过期订单付款状态查询
	public void paymentOrderScan(Date date);
	//根据用户名查询所有的订单号
	public String[] selectByBuyerNick(String buyerNick);
}
