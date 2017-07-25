package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.service.BaseService;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ContentMapper;
import com.jt.manage.pojo.Content;

@Service
public class ContentService extends BaseService<Content> {
	@Autowired
	private ContentMapper contentMapper;


	public EasyUIResult queryContentList(Integer pageNum, Integer pageSize, Integer categoryId) {
		PageHelper.startPage(pageNum, pageSize);
		List<Content> contentList = contentMapper.queryItemList(categoryId);
		PageInfo<Content> info =new PageInfo<Content>(contentList);
		
		return new EasyUIResult(info.getTotal(),info.getList());
	}

}
