package com.jt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;

@Service
public class OrderService {
	@Autowired
	private HttpClientService httpClientService ;
	
	private static final ObjectMapper MAPPER =new ObjectMapper();
	
	public List<Cart> getCartList(Long userId) throws Exception{
		
		String url = "http://cart.jt.com/cart/query/"+userId;
		String jsonData = httpClientService.doGet(url);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		JsonNode data=jsonNode.get("data");
		Object obj = null;
        if (data.isArray() && data.size() > 0) {
            obj = MAPPER.readValue(data.traverse(),
                    MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
        }
		return (List<Cart>) obj;
	}
	
	//创建订单
	public String createOrder(Order order){
		String url="http://order.jt.com/order/create";
		try {
			String orderId = httpClientService.doPostJson(url, MAPPER.writeValueAsString(order));
			return orderId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//根据订单id获取订单
	public Order getOrderById(String id) throws Exception{
		String url = "http://order.jt.com/order/query/"+id;
		 String orderJson = httpClientService.doGet(url);
		 Order order =  MAPPER.readValue(orderJson, Order.class);
		 return order;
		
	}
}
