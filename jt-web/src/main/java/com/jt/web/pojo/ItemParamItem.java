package com.jt.web.pojo;

import javax.persistence.Table;

import com.jt.common.po.BasePojo;

public class ItemParamItem extends BasePojo {
	//商品规格和商品的关系表
	private Long id;
	private Long itemId;
	private String paramData;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	@Override
	public String toString() {
		return "ItemParamItem [id=" + id + ", itemId=" + itemId + ", paramData=" + paramData + "]";
	}
	
	
	
}
