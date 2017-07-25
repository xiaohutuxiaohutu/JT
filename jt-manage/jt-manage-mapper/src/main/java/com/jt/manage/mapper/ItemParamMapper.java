package com.jt.manage.mapper;

import java.util.List;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.ItemParam;

public interface ItemParamMapper extends SysMapper<ItemParam> {

	/**
	 * 查询ITEMParam列表
	 * @return
	 */
	List<ItemParam> queryItemParamList();

	ItemParam selectItemParamByItemId(Long itemId);

}
