package com.jt.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.ItemParam;
import com.jt.manage.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	//查询itemparam列表
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult selectItemParamList(Integer page,Integer rows){ 
		return itemParamService.selectItemParamList(page,rows);
		
	}
	//根据id查询itemparam中的数据
	@RequestMapping("/query/{id}")
	@ResponseBody
	public String queryItemParam(@PathVariable("id") Long id){ 
		ItemParam itemParam = new ItemParam();
		itemParam.setId(id);
		ItemParam itemParam1 = itemParamService.queryByWhere(itemParam);
		return itemParam1.getParamData();
		
		/*try {
			ItemParam itemParam = new ItemParam();
			itemParam.setId(id);
			ItemParam itemParam1 = itemParamService.queryByWhere(itemParam);
			String paramData=itemParam1.getParamData();
			return SysResult.oK(paramData);
			
		} catch (Exception e) {
			return SysResult.build(201, "");
		}*/
	}
	//根据itemcatcid查询itemparam表中是否已存在
	@RequestMapping("/query/itemcatid/{id}")
	@ResponseBody
	public SysResult queryItemParamCid(@PathVariable("id") Long cid){ 
		try {
			ItemParam itemParam = new ItemParam();
			itemParam.setItemCatId(cid);
			if(itemParamService.queryByWhere(itemParam) != null){
				String jsonData = MAPPER.writeValueAsString(itemParamService.queryByWhere(itemParam));
				return SysResult.oK(jsonData);
			}else{
				return SysResult.build(201, "");
			}
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	//新增
	@RequestMapping("/save/{id}")
	@ResponseBody
	public SysResult saveItemParam(@PathVariable("id") Long cid,String paramData){ 
		System.out.println("controller:cid="+cid);
		System.out.println("controller:paranData="+paramData);
		try {
			ItemParam itemParam = new ItemParam();
			itemParam.setItemCatId(cid);
			itemParam.setParamData(paramData);
			itemParam.setCreated(new Date());
			itemParam.setUpdated(new Date());
			itemParamService.saveItemParam(itemParam);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	
	//删除
	@RequestMapping("delete")
	@ResponseBody
	public SysResult deleteItemParam(Long[] ids){
		return itemParamService.deleteItemParamByIds(ids);
	}
	//更新
	@RequestMapping("update/{id}")
	@ResponseBody
	public SysResult updateItemParam(@PathVariable Long id,@RequestParam Long cid,@RequestParam String paramData){ 
		System.out.println("id="+id);
		System.out.println("controller:cid="+cid);
		System.out.println("controller:paranData="+paramData);
		try {
			ItemParam itemParam = new ItemParam();
			itemParam.setId(id);
			itemParam.setItemCatId(cid);
			itemParam.setParamData(paramData);
			itemParam.setUpdated(new Date());
			itemParamService.updateSelective(itemParam);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	
}
