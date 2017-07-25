package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.pojo.ItemParam;
import com.jt.web.pojo.ItemParamItem;
import com.jt.web.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//根据商品id获取商品信息
	@RequestMapping("/items/{itemId}")
	public String getItem(@PathVariable Long itemId,Model model){
		System.out.println("iemController:itemID="+itemId);
		Item item = itemService.getItemById(itemId);
		System.out.println("前台ITEMController+item="+item);
		model.addAttribute("item",item);
		
		ItemDesc itemDesc = itemService.getItemDescById(itemId);
		model.addAttribute("itemDesc",itemDesc);
		
		//itemId是商品Id,item表中的cid才是itemCatId
		//查询商品规格信息
		String itemParam =itemService.getItemParamItemByitemId(itemId);
		model.addAttribute("itemParam",itemParam);
		
		//返回的item是item.jsp页面，不是model中的item
		return "item";			
	}
	
}
