package com.demo.common.bean;

import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class BeanHelper {

	private Logger log = Logger.getLogger(this.getClass());

    private static BeanHelperBean inst = new BeanHelperBean();


    public static BeanHelperBean createBeanHelper() {
        return new BeanHelperBean();
    }

    /**
     * 复制Bean属性
     * @param dest
     * @param orig
     */
    public static void copyProperties(Object dest, Object orig) {
        inst.copyProperties(dest, orig);
    }

    /**
     * 注册转换器
     *
     * @param converter
     */
    public void registerConverter(Converter converter, Class<?> clazz) {
        inst.registerConverter(converter, clazz);
    }

    /**
     * 使用Mapper来映射Bean的属性
     * @return
     */
    public static BeanMapper beanMapper() {
        return inst.beanMapper();
    }

}
