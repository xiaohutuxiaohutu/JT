package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.pojo.ItemParam;
import com.jt.manage.pojo.ItemParamItem;
import com.jt.manage.service.ItemParamItemService;
import com.jt.manage.service.ItemParamService;
import com.jt.manage.service.ItemService;



@Controller
public class WebItemController {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemParamService itemParamService;
	
	@Autowired 
	ItemParamItemService itemParamItemService;
	/**
	 * 查询商品详情页
	 * 前台ItemService中httpClient发送请求过来 String url="http://manage.jt.com/web/item/"+itemId;
	 * @param itemId
	 * @return
	 */
	/*@RequestMapping("/web/item/{itemId}")
	@ResponseBody
	public Item getItemById(@PathVariable Long itemId){
		return itemService.queryById(itemId);
	}*/
	
	//通过SysResult返回查询状态
	@RequestMapping("/web/item/{itemId}")
    @ResponseBody
    public SysResult queryList(@PathVariable Long itemId) {
        try {
            Item item = itemService.queryById(itemId);
            if (item != null && item.getId() != null) {
                return SysResult.oK(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "查询失败! itemId = " + itemId);
        }
        return SysResult.build(201, "查询不到商品数据 id = " + itemId);
    }

	/**
	 * 查询商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/web/itemdesc/{itemId}")
	@ResponseBody
	public ItemDesc getItemDescById(@PathVariable Long itemId){
		return itemService.getItemDesc(itemId);
	}
	
	/**
	 * 查询商品规格信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/web/itemParam/{itemId}")
	@ResponseBody
	public ItemParamItem getItemParamByItemId(@PathVariable Long itemId){
		ItemParamItem ipi=new ItemParamItem();
		ipi.setItemId(itemId);
		System.out.println("ipi.itemid="+ipi.getItemId());
		return itemParamItemService.queryByWhere(ipi);
	}
}
