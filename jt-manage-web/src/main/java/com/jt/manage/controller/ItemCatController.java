package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	// http://localhost:8079/item/cat/list
	@Autowired
	private ItemCatService itemCatService;
	//查询所有商品列表
	@RequestMapping("/list")
	@ResponseBody
	public List<ItemCat> toIndex(@RequestParam(defaultValue="0")Long id){
		return itemCatService.list(id);
	}

}
