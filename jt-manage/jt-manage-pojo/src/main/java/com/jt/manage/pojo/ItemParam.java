package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="tb_item_param")
public class ItemParam extends BasePojo {

	//商品规则参数表
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long itemCatId;
	private String paramData;
	@Transient
	private String itemCatName;
	public String getItemCatName() {
		return itemCatName;
	}
	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	} 
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
