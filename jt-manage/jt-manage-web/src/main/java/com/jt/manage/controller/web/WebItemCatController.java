package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/web/itemcat")
public class WebItemCatController {
	
	//http://manage.jt.com/web/itemcat/all?callback=category.getDataService   lib-v1.js  1173行
	@Autowired
	private ItemCatService itemCatService;
	//查询正常的状态的商品分裂
	@RequestMapping("/all")
	@ResponseBody
	public ItemCatResult getItemCatList(){
		return itemCatService.getItemCatList();
	}
	
}
