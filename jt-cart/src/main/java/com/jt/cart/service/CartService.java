package com.jt.cart.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;
@Service
public class CartService extends BaseService<Cart> {
	@Autowired
	private CartMapper cartMapper;
	
	/**
	 * 查询购物车
	 * @param userId
	 * @return
	 */
	public List<Cart> myCart(Long userId){
		Cart params = new Cart();
		params.setUserId(userId);
		return cartMapper.select(params);
		
	}

	/**
	 * 加入购物车，如果该商品不再购物车，执行新增，如果存在，修改数量
	 * @param cart 购物车对象
	 */
	public void saveCart(Cart cart){
		//判断购物车是否存在该商品
		Cart params = new Cart();
		params.setUserId(cart.getUserId());
		params.setItemId(cart.getItemId());
		Cart curCart = super.queryByWhere(params);//查询到的数据
		//判断是否插到数据
		if(curCart==null){
			//不存在
			cartMapper.select(params);
			cart.setCreated(new Date());
			cartMapper.insertSelective(cart);
		}else{
			//存在，获取查询到的数量
			cart.setId(curCart.getId());
			cart.setNum(curCart.getNum()+cart.getNum());
			cart.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cart);
		}
	}
	
	/**
	 * 更新数量
	 * @param userId
	 * @param itemId
	 * @param num
	 */
	//http://cart.jt.com/cart/update/num/{userId}/{itemId}/{num}
	
	public void updateNum(Cart cart){
	//spring 可以将三个参数直接封装为对象，不能加@pathvariable
		cartMapper.updateNum(cart);
		
	}
	
	/**
	 * 删除订单
	 * @param userId
	 * @param itemId
	 */
	//http://cart.jt.com/cart/delete/{userId}/{itemId}
	public void deleteCart(Cart cart){
		cartMapper.delete(cart);
	}
}
