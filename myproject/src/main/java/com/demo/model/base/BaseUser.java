package com.demo.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}
	
	public void setStudentAccount(java.lang.String studentAccount) {
		set("student_account", studentAccount);
	}
	
	public java.lang.String getStudentAccount() {
		return getStr("student_account");
	}
	
	public void setStudentName(java.lang.String studentName) {
		set("student_name", studentName);
	}
	
	public java.lang.String getStudentName() {
		return getStr("student_name");
	}
	
	public void setStudentPw(java.lang.String studentPw) {
		set("student_pw", studentPw);
	}
	
	public java.lang.String getStudentPw() {
		return getStr("student_pw");
	}
	
	public void setStudentId(java.lang.String studentId) {
		set("student_id", studentId);
	}
	
	public java.lang.String getStudentId() {
		return getStr("student_id");
	}
	
	public void setStudentTel(java.lang.String studentTel) {
		set("student_tel", studentTel);
	}
	
	public java.lang.String getStudentTel() {
		return getStr("student_tel");
	}
	
	public void setStudentMail(java.lang.String studentMail) {
		set("student_mail", studentMail);
	}
	
	public java.lang.String getStudentMail() {
		return getStr("student_mail");
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
