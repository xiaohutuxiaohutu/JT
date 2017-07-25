package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.threadlocal.UserThreadLocal;

@Controller
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartService cartService;
	//我的购物车 cart/show.html
	@RequestMapping("/show")
	public String show(Model model) throws Exception{
		//Long userId=7L;
		Long userId=UserThreadLocal.getUserId();
		List<Cart> cartList = cartService.show(userId);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	//详情页加入购物车 item.jsp  40行连接 http://www.jt.com/cart/add/${item.id}.html
	@RequestMapping("add/{itemId}")
	public String addCart(Cart cart){//用对象接收，不能加,@PathVariable注解
		Long userId=UserThreadLocal.getUserId();
		cart.setUserId(userId);
		cartService.addCart(cart);
		return "redirect:/cart/show.html";
	}
	
	/*购物车页面修改商品数量  cart。js ajax post请求
	 * $.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
	 * 需要返回值，但用不到
	 */
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public String updateNum(@PathVariable Long itemId,@PathVariable Integer num){
		Long userId=UserThreadLocal.getUserId();
		cartService.updateNum(userId,itemId,num);
		return "success";
	}
	//删除购物车信息 /cart/delete/${cart.itemId}.html
	@RequestMapping("delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId){
		Long userId=UserThreadLocal.getUserId();
		cartService.deleteCart(userId,itemId);
		return "redirect:/cart/show.html";
	}
	
	
}
