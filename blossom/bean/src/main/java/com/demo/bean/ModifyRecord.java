package com.demo.bean;

import com.demo.common.bean.BaseEntity;

import java.util.Date;

/**
 * Created by adon on 2016/3/3 0003.
 */
public class ModifyRecord extends BaseEntity {

	public ModifyRecord() {}

	public ModifyRecord(Long targetId, String classFullName,
						String className, String dataType,
						String filed, String srcValue,
						String newValue, Date createDate) {
		this.targetId = targetId;
		this.classFullName = classFullName;
		this.className = className;
		this.dataType = dataType;
		this.filed = filed;
		this.srcValue = srcValue;
		this.newValue = newValue;
		this.createDate = createDate;
	}

	/**
	 * ID
	 */
	private Long id;
	/**
	 * 修改ID
	 */
	private Long targetId;
	/**
	 * 类名
	 */
	private String classFullName;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 字段
	 */
	private String filed;
	/**
	 * 原值
	 */
	private String srcValue;
	/**
	 * 修改值
	 */
	private String newValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getSrcValue() {
		return srcValue;
	}

	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}
}
