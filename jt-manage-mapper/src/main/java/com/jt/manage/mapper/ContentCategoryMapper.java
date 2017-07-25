package com.jt.manage.mapper;

import java.util.List;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.ContentCategory;

public interface ContentCategoryMapper extends SysMapper<ContentCategory> {

	List<ContentCategory> findByParentId(Long id);

}
