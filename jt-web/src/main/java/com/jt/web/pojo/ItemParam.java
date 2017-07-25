package com.jt.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemParam extends BasePojo {

	//商品规则参数表
	private Long id;
	private Long itemCatId;
	private String paramData;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	@Override
	public String toString() {
		return "ItemParam [id=" + id + ", itemCatId=" + itemCatId + ", paramData=" + paramData + "]";
	}
	
}
