package com.jt.manage.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Content;
import com.jt.manage.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	//查询
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResult querycontentList(@RequestParam(value="page")Integer pageNum,@RequestParam(value="rows")Integer pageSize,@RequestParam(value="categoryId")Integer categoryId){
		System.out.println("pageNum="+pageNum+";pageSize="+pageSize+";categoryId="+categoryId);
		return contentService.queryContentList(pageNum,pageSize,categoryId);
	}
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveContent(Content content){
		try {
			content.setCreated(new Date());
			contentService.saveSelective(content);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public SysResult editContent(Content content){
		try {
			content.setUpdated(new Date());
			contentService.update(content);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteContent(@RequestParam(value="ids") Long[] ids){
		System.out.println("ids="+ids);
		try {
			contentService.deleteByIds(ids);
			return SysResult.oK();
		} catch (Exception e) {
			return SysResult.build(201, "");
		}
	}
}
