package com.hgsoft.security.hibex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 对原始类型的属性进行自动转换
 * 字段别名大小写无关
 * @author huj
 *
 */
public class AliasToBeanAutoCastResultTransformer implements ResultTransformer {
	private static Log log = LogFactory.getLog(AliasToBeanAutoCastResultTransformer.class);

	private final Class resultClass;
	private Setter[] setters;
	private PropertyAccessor propertyAccessor;
	

	public AliasToBeanAutoCastResultTransformer(Class resultClass) {
		if (resultClass == null)
			throw new IllegalArgumentException("resultClass cannot be null");
		this.resultClass = resultClass;
		propertyAccessor = new ChainedPropertyAccessor(new PropertyAccessor[] {
				PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
				PropertyAccessorFactory.getPropertyAccessor("field") });
	}
	
	/**
	 * 获取所有类的成员(包括祖先类的)
	 * @param list
	 * @param clazz
	 */
	private List getClassAndAncestorFieldNames(Class clazz) {
		ArrayList fieldNames = new ArrayList();
		if(clazz == null || clazz == Object.class) {
			return fieldNames;
		}
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		
		List superFieldNames = getClassAndAncestorFieldNames(clazz.getSuperclass());
		fieldNames.addAll(superFieldNames);
		return fieldNames;
	}
	
	/**
	 * 在类中查找名称与字段名匹配的Field，不区分大小写
	 * @param alias
	 * @param fieldNames
	 * @return
	 */
	private String getMatchClassFieldName(String alias, List fieldNames) {
		for (int i = 0; i < fieldNames.size(); i++) {
			String ret = (String)fieldNames.get(i);
			if(alias.equalsIgnoreCase(ret)) {
				log.debug("field match:" +  alias + "--" + ret);
				return ret;
			}
		}
		return null;
	}
	
	private void ensureSettersAvailable(String[] aliases) {
		if (setters != null) {
			return;
		}
		setters = new Setter[aliases.length];
		// 类的所有属性名
		List classFieldNames =	getClassAndAncestorFieldNames(resultClass);
		for (int i = 0; i < aliases.length; i++) {
			if (aliases[i] == null) {
				continue;
			}
			String fieldName = getMatchClassFieldName(aliases[i], classFieldNames);
			if (fieldName != null) {
				setters[i] = propertyAccessor.getSetter(resultClass, fieldName);
			}else {
				log.warn("字段名[" + aliases[i]  + "]在Class中没有对应的Field，因此不会被填充到Bean中。");
			}
		}
	}
	
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object bean;

		try {
			ensureSettersAvailable(aliases);
			bean = resultClass.newInstance();

			for (int i = 0; i < setters.length; i++) {
				if(setters[i] == null) {
					continue;
				}
				if (setters[i].getMethod() == null) {
					throw new RuntimeException("属性没有对应的set方法:" + setters[i]);
				}				
				setBeanValue(setters[i], bean, tuple[i]);
			}
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		}

		return bean;
	}

	private void setBeanValue(Setter setter, Object bean, Object value) {
		// pojo都只有一个参数，所以直接取第一个参数
		Class paramClass = setter.getMethod().getParameterTypes()[0];
		if (paramClass.isPrimitive()) {
			setPrimitiveValue(setter, bean, paramClass, value);
		} else {
			//特殊处理下BigDecimal和Double属性的问题
			if (value instanceof Number) {
				Number newValue = (Number) value;
				if (paramClass.equals(Double.class)){
					//如果是Double类型
					setter.set(bean, newValue.doubleValue(), null);
				} else if (paramClass.equals(Float.class)){
					setter.set(bean, newValue.floatValue(), null);
				} else {
					setter.set(bean, value, null);
				}
			} else {
				setter.set(bean, value, null);
			}
		}
	}

	public List transformList(List collection) {
		return collection;
	}

	private void setPrimitiveValue(Setter setter, Object instance,
			Class paramClass, Object value) {
		if(value == null) {
			//setter.set(instance, value, null);
			return;
		}
		
		log.debug("set method name:" + setter.getMethod().getName()
				+ ",param class:" + paramClass.getName()
				+ ",value class:" + value.getClass().getName());
		if (paramClass == Integer.TYPE) {
			setter.set(instance, new Integer(((Number) value).intValue()), null);
		} else if (paramClass == Double.TYPE) {
			setter.set(instance, new Double(((Number) value).doubleValue()), null);
		} else if (paramClass == Long.TYPE) {
			setter.set(instance, new Long(((Number) value).longValue()), null);
		} else if (paramClass == Boolean.TYPE) {
			if(value instanceof Number) {
				//大于0的数值都认为是true
				boolean b = ((Number) value).intValue() > 0 ? true : false;
				setter.set(instance, new Boolean(b), null);
			}else {
				setter.set(instance, value, null);
			}
		} else {
			setter.set(instance, value, null);
		}
	}
}
