package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

@Table(name="tb_content_category")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContentCategory extends BasePojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;
	/*public String state;
	public String text;*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getText(){
		return this.name;
	}
	public String getState(){
		return this.isParent?"closed":"open";
	}
	@Override
	public String toString() {
		return "ContenCategory [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status
				+ ", sortOrder=" + sortOrder + ", isParent=" + isParent + "]";
	}
	
	
}
