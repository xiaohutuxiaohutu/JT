package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.ContentCategory;
import com.jt.manage.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContenCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	//查询所有内容分类管理模块
	@RequestMapping("/list")
	@ResponseBody
	public List<ContentCategory> toIndex(@RequestParam(defaultValue="0")Long id){
		//System.out.println("Controller:id="+id);
		return contentCategoryService.list(id);
	}
	/**
	 * 增加内容分类管理
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public SysResult createCategory(@RequestParam(value="parentId") Long parentId,@RequestParam(value="name") String name){
		//System.out.println("parentId="+parentId+";name="+name);
		try{
			contentCategoryService.createCategory(parentId,name);
			return SysResult.oK();
		}catch(Exception e){
			return SysResult.build(201, "fali");
		}
	}
	/**
	 * 修改内容分类管理模块
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateCategory(@RequestParam(value="id") Long id,@RequestParam(value="name") String name){
		try {
			contentCategoryService.updateCategory(id,name);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	/**
	 * 删除分类
	 * @param parentId
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteCategory(@RequestParam(value="parentId") Long parentId,@RequestParam(value="id") Long id){
		try {
			contentCategoryService.deleteCategory(parentId,id);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
}
