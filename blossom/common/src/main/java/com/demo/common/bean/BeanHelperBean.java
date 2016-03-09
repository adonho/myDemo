package com.demo.common.bean;

import org.apache.commons.beanutils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Niufei on 15-4-7.
 */
public class BeanHelperBean {

	private Logger log = Logger.getLogger(this.getClass());

    private BeanUtilsBean bub = new BeanUtilsBean();

    /**
     * 复制Bean属性
     * @param dest
     * @param orig
     */
    public void copyProperties(Object dest, Object orig) {
        // Validate existence of the specified beans
        if (dest == null || orig == null) {
            return;
        }
        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors =
                    ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                // Need to check isReadable() for WrapDynaBean
                // (see Jira issue# BEANUTILS-61)
                if (bub.getPropertyUtils().isReadable(orig, name) &&
                        bub.getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    setProperty(dest, name, value);
                }
            }
        } else if (orig instanceof Map) {
            Iterator<?> entries = ((Map<?,?>) orig).entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<?,?> entry = (Map.Entry<?,?>) entries.next();
                String name = (String)entry.getKey();
                if (bub.getPropertyUtils().isWriteable(dest, name)) {
                    setProperty(dest, name, entry.getValue());
                }
            }
        } else /* if (orig is a standard JavaBean) */ {
            PropertyDescriptor[] origDescriptors =
                    bub.getPropertyUtils().getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (bub.getPropertyUtils().isReadable(orig, name) &&
                        bub.getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value = bub.getPropertyUtils().getSimpleProperty(orig, name);
                        setProperty(dest, name, value);
                    } catch (Exception e) {
                        // Should not happen
						log.error(e);
                    }
                }
            }
        }
    }

    /**
     * 注册转换器
     *
     * @param converter
     */
    public void registerConverter(Converter converter, Class<?> clazz) {
        bub.getConvertUtils().register(converter, clazz);
    }

    /**
     * 查找转换器
     * @param clazz
     * @return
     */
    public Converter lookupConverter(Class<?> clazz) {
        return bub.getConvertUtils().lookup(clazz);
    }

    /**
     * 使用Mapper来映射Bean的属性
     * @return
     */
    public BeanMapper beanMapper() {
        return new BeanMapper() {
            private Map<String, String> map = new LinkedHashMap<String, String>();
            @Override
            public BeanMapper mapper(String destPropertyName, String origPropertyName) {
                if (StringUtils.isBlank(destPropertyName)) {
                    throw new IllegalArgumentException
                            ("destPropertyName has not bean specified");
                }
                if (StringUtils.isBlank(origPropertyName)) {
                    throw new IllegalArgumentException("origPropertyName has not bean specified");
                }

                map.put(destPropertyName, origPropertyName);
                return this;
            }

            @Override
            public void copyProperties(Object dest, Object orig) {
                BeanHelperBean.this.copyProperties(dest, orig);
                for (Map.Entry<String,String> entry : map.entrySet()) {
                    String destPropName = entry.getKey();
                    String origPropName = entry.getValue();
                    try {
                        Object value = BeanUtils.getProperty(orig, origPropName);
                        setProperty(dest, destPropName, value);
                    } catch (Exception e) {
						log.error(e);
                        throw new IllegalArgumentException("copy property [" + destPropName + "] to [" + origPropName + "] occur error: ", e);
                    }
                }
            }
        };
    }

    /**
     * 复制
     * @param bean
     * @param name
     * @param value
     */
    private void setProperty(Object bean, String name, Object value) {
        try {
            bub.copyProperty(bean, name, value);
        } catch (Exception e) {
            // ignore
			log.error(e);
        }
    }
}
