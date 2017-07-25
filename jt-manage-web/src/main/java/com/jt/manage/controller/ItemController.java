package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.pojo.ItemParamItem;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.service.ItemService;


@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCatService;
	//查询商品功能，根据修改时间排序
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult queryItemList(@RequestParam(value="page")Integer pageNum,@RequestParam(value="rows")Integer pageSize){
		return itemService.queryItemList(pageNum,pageSize);
	}
	
	@RequestMapping("/findCidName")
	@ResponseBody
	public ItemCat findCidName(Long cid ){
		ItemCat itemCat = itemCatService.queryById(cid);
		return itemCat;
	}
	
	//新增商品功能
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc,String itemParams){//item.add页面  商品描述隐藏域名称
		try {
			itemService.saveItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "新增失败");
		}
	}
	
	//修改商品功能
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try {
			itemService.updateItem(item,desc);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "修改失败");
		}
	}
	
	//查询商品描述用来回显
	@RequestMapping("/query/item/desc/{id}")
	@ResponseBody
	public SysResult selectItemDesc(@PathVariable Long id){
		try {
			ItemDesc itemDesc = itemService.getItemDesc(id);
			return SysResult.oK(itemDesc);
		} catch (Exception e) {
			return SysResult.build(201, "修改失败");
		}
	}	
	//查询商品规则用来回显
	@RequestMapping("/param/item/query/{id}")
	@ResponseBody
	public SysResult selectItemParam(@PathVariable Long id){
		try {
			ItemParamItem itemParamItem=itemService.queryItemParam(id);
			return SysResult.oK(itemParamItem);
		} catch (Exception e) {
			return SysResult.build(201, "修改失败");
		}
	}
	
	//删除商品功能
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids){
		try {
			itemService.deleteItem(ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "修改失败");
		}
	}
	
	//instock 下架
	@RequestMapping("/instock")
	@ResponseBody
	public SysResult instockItem(Long[] ids){
		try {
			itemService.updateStatus(2, ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "下架失败");
		}
	}
	
	//上架		
	@RequestMapping("/reshelf")
	@ResponseBody
	public SysResult reshelfItem(Long[] ids){
		try {
			itemService.updateStatus(1, ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "上架失败");
		}
	}	
	
}
