package com.jt.manage.mapper;

import java.util.List;
import java.util.Map;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item> {
	//查询商品，根据修改时间排序
	public List<Item> queryItemList();
	
	//上架下架
	public void updateStatus(Map<String,Object> map);
}
