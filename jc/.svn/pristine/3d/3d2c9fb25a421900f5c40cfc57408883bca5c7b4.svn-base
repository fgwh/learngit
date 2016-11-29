package com.hgsoft.parse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsumptionParseToRecvService extends DefaultConsumptionParse{

	private final static Log logger = LogFactory.getLog(DefaultConsumptionParse.class);
	
	public ConsumptionParseToRecvService(String templatePath) {
		super(templatePath);
	}
	
	 

	@Override
	Object bussiness(String className,String heads,String values) {
		return null;
	}



	@Override
	Object bussiness(String className, String[] heads, String[] values) throws Exception {
		
		LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
		
		for (int i=0;i<heads.length;i++) {
			fieldMap.put(heads[i].toLowerCase(), values[i]);
		}

		try {
			Class clazz = Class.forName(className);
			Object bean = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				String name = field.getName();
				if ("id".equals(name.toLowerCase().trim())) {
					continue;
				} else {
					String firstLetter = name.substring(0, 1).toUpperCase();
					String setMethodName = "set"+firstLetter+name.substring(1);
					Class paramType = field.getType();
					Method setMethod= clazz.getMethod(setMethodName, paramType);
					
					if (fieldMap.get(name.toLowerCase()) != null && fieldMap.get(name.toLowerCase()).trim().length()>0) {
						setMethod.invoke(bean, typeCast(fieldMap.get(name.toLowerCase()).trim(), paramType));
					}
				}
				
	        }
	        return bean;
		} catch (ClassNotFoundException e) {
			logger.error("自动装配类过程" + "找不到类:" + className);
			throw e;
		} catch (InstantiationException e) {
			logger.error("自动装配类过程" + "InstantiationException!");
			throw e;
		} catch (IllegalAccessException e) {
			logger.error("自动装配类过程" + "非法访问方法！");
			throw e;
		} catch (NoSuchMethodException e) {
            logger.error("自动装配类过程" + "找不到对应的方法！");
            throw e;
        } catch (InvocationTargetException e) {
            logger.error("自动装配类过程" + "InvocationTargetException!");
            throw e;
        }
		
	}
	
	 private Object typeCast(String value, Class clazz) {
        if (clazz.equals(Integer.class)) {
        	 try {
        		 return Integer.valueOf(value);
        	 }catch (Exception e) {
       		  logger.error(value + " cast to Integer error!");
        	 }
        } else if (clazz.equals(Short.class)) {
        	 try {
        		return Short.valueOf(value);
        	 }catch (Exception e) {
	     		  logger.error(value + " cast to Short error!");
	      	 }
        } else if (clazz.equals(Byte.class)) {
        	 try {
        		 return Byte.valueOf(value);
         	 }catch (Exception e) {
	     		  logger.error(value + " cast to Byte error!");
	      	 }
        } else if (clazz.equals(Long.class)) {
        	  try {
        		  return Long.valueOf(value);
        	  }catch (Exception e) {
        		  logger.error(value + " cast to Long error!");
        	  }
        } else if (clazz.equals(String.class) && !(value.equals("null"))) {
            return value;
        } else if (clazz.equals(Date.class)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
                //return new SimpleDateFormat("yyyyMMddHHmmss").parse(value.replace("T", ""));
            } catch (ParseException e) {
            	//logger.error(value + " cast to Date error,pattern is yyyyMMddTHHmmss!");
                try {
                    return new SimpleDateFormat("yyyyMMdd").parse(value);
                } catch (ParseException e1) {
                    logger.error(value + " cast to Date error,pattern is yyyy-MM-dd HH:mm:ss or yyyy-MM-dd!");
                }
            }
        }
        return null;
    }
	
}
