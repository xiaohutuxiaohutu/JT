package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.ItemParamItem;

public interface ItemParamItemMapper extends SysMapper<ItemParamItem> {

	//根据id查询
	ItemParamItem queryItemParam(Long id);

}
