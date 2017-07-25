package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Cart;

@Service
public class CartService {

	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 查询购物车信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Cart> show(Long userId) throws Exception{
		String url="http://cart.jt.com/cart/query/"+userId;
		String jsonData = httpClientService.doGet(url);
		JsonNode jsonNode = MAPPER.readTree(jsonData);
		JsonNode cartListNode = jsonNode.get("data");
		 Object obj = null;
         if (cartListNode.isArray() && cartListNode.size() > 0) {
             obj = MAPPER.readValue(cartListNode.traverse(),
                     MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
         }
         return (List<Cart>) obj;		
	}
	/**
	 * 添加商品到购物车
	 * @param cart
	 */
	public void addCart(Cart cart) {
		String url="http://cart.jt.com/cart/save";
		Map<String,String> params = new HashMap<String,String>();
		try {
			params.put("userId", ""+cart.getUserId());
			params.put("itemId", ""+cart.getItemId());
			params.put("itemTitle",cart.getItemTitle());
			params.put("itemImage",cart.getItemImage());
			params.put("num", ""+cart.getNum());
			params.put("itemPrice", ""+cart.getItemPrice());
			httpClientService.doPost(url, params, "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改购物车数量
	 * @param userId
	 * @param itemId
	 * @param num
	 */
	public void updateNum(Long userId,Long itemId, Integer num) {
		String url="http://cart.jt.com/cart/update/num/"+userId+"/"+itemId+"/"+num;
		try {
			httpClientService.doGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除购物车
	 * @param itemId
	 */
	public void deleteCart(Long userId, Long itemId) {
		String url="http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		try {
			httpClientService.doGet(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
