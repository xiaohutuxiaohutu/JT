package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	//http://cart.jt.com/cart/query/{userId}
	/**
	 * 查询购物车
	 * @param userId
	 * @return
	 */
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult myCart(@PathVariable Long userId){
		List<Cart> cartList=cartService.myCart(userId);
		return SysResult.oK(cartList);
	}
	
	/**
	 * 加入购物车，如果该商品不再购物车，执行新增，如果存在，修改数量
	 * @param cart 从页面获取的cart对象
	 * @return
	 */
	//http://cart.jt.com/cart/save
	@RequestMapping("save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		cartService.saveCart(cart);
		return SysResult.oK();
	}
	
	/**
	 * 更新购买商品数量
	 * @param userId 用户id
	 * @param itemId 商品id
	 * @param num 更新的数量
	 * @return 状态码 200
	 */
	//http://cart.jt.com/cart/update/num/{userId}/{itemId}/{num}
	@RequestMapping("update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(Cart cart){//spring 可以将三个参数直接封装为对象，不能加@pathvariable
		cartService.updateNum(cart);
		return SysResult.oK();
	}
	
	/**
	 * 删除购物车信息
	 * @param userId  用户id
	 * @param itemId  商品id
	 * @return 状态码 200
	 */
	//http://cart.jt.com/cart/delete/{userId}/{itemId}
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(Cart cart){
		cartService.deleteCart(cart);
		return SysResult.oK();
	}
	
	
}
