package com.demo.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDepartment<M extends BaseDepartment<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}
	
	public void setClassName(java.lang.String className) {
		set("class_name", className);
	}
	
	public java.lang.String getClassName() {
		return getStr("class_name");
	}
	
	public void setCreatedTime(java.util.Date createdTime) {
		set("created_time", createdTime);
	}
	
	public java.util.Date getCreatedTime() {
		return get("created_time");
	}
	
	public void setModifiedTime(java.util.Date modifiedTime) {
		set("modified_time", modifiedTime);
	}
	
	public java.util.Date getModifiedTime() {
		return get("modified_time");
	}
	
	public void setIsDeleted(java.lang.Integer isDeleted) {
		set("is_deleted", isDeleted);
	}
	
	public java.lang.Integer getIsDeleted() {
		return getInt("is_deleted");
	}
	
}
