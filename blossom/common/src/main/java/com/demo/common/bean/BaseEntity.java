package com.demo.common.bean;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final short STATE_Y = 1;
	public static final short STATE_N = 0;

	protected Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		Field[] fields = getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		String[] excludeFields = new String[0];
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().toString().equals("interface java.util.Set")) {
				excludeFields = ArrayUtils.add(excludeFields, field.getName());
			}
		}
		return HashCodeBuilder.reflectionHashCode(17, 37, this, false, null, excludeFields.length == 0 ? null : excludeFields);
	}
}
