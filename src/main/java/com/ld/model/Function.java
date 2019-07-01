package com.ld.model;

import java.util.Date;

public class Function extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3122841784500582763L;
	private Long id;
	private String name;
	private String url;
	private String iconClass;
	private Long parentId;
	private Integer level;
	private Boolean isFunction;
	private Integer sort;
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Boolean isFunction() {
		return isFunction;
	}
	public void setFunction(Boolean isFunction) {
		this.isFunction = isFunction;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
