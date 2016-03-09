package com.demo.common.bean;

public interface BeanMapper {

	/**
	 * 映射属性名
	 * @param destPropName
	 * @param origPropName
	 * @return
	 */
	BeanMapper mapper(String destPropName, String origPropName);
	
	/**
	 * 复制
	 * @param dest
	 * @param orig
	 */
	void copyProperties(Object dest, Object orig);

}
