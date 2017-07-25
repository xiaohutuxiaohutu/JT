package com.jt.common.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
//代表一条记录，或者是一级分类，或者二级分类
public class ItemCatData {
	//序列化成json数据时为 u
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> items;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
}