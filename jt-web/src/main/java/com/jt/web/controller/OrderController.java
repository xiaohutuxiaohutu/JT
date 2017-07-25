package com.jt.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.service.OrderService;
import com.jt.web.threadlocal.UserThreadLocal;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	//转向订单页面
	@RequestMapping("/create.html")
	public String createOrder(Model model) throws Exception{
		
		Long userId=UserThreadLocal.getUserId();
		//Long userId=1L;
		List<Cart> carts=orderService.getCartList(userId);
		model.addAttribute("carts",carts);
		return "order-cart";
	}
	//提交表单  /order/submit
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult submit(Order order){
		Long userId=UserThreadLocal.getUserId();
		String userName=UserThreadLocal.getUserName();
		//Long userId=1L;
		order.setUserId(userId);
		order.setBuyerNick(userName);
		String orderId=orderService.createOrder(order);
		return SysResult.oK(orderId);
	}
	
	@RequestMapping("/success")
	public String returnSuccess(String id,Model model) throws Exception{
		Order order =orderService.getOrderById(id) ;
		order.setOrderId(id);
		Date date=new Date();
		date.setTime(System.currentTimeMillis()+60*60*24);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String format2 = format.format(date);
		System.out.println("date"+date);
		
		model.addAttribute("order",order);
		model.addAttribute("date",format2);
		return "success";
	}
	//查询我的订单
	@RequestMapping("myOrder")
	public String myOrder(){
		Long userId=UserThreadLocal.getUserId();
		//根据用户Id查询所有订单信息
		return "my-orders";
	}
}
