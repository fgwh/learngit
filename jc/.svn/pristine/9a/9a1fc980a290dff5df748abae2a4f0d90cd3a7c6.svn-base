package com.hgsoft.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import java.util.Date;

/**
 * java.sql.date 转换器，供ConvertUtils注册使用
 * User: zhuzf
 * Date: 2004-9-29
 * Time: 9:57:22
 */
public class UtilDateConverter implements Converter {
    public Object convert(Class type, Object value) {
        //如果value为空的话，则返回空，不抛出异常
        if (value == null || value.toString().length() < 1) {
            return null;
        } else {
            //如果是java.util.Date
            if (value instanceof Date) {
                return (value);
            }

            try {
                Date aDate = null;
                //4位数年份 例如：1980
                if (value.toString().length() <= 10) {
                    aDate = (Date) DateUtil.formatDate(value.toString(), "yyyy-MM-dd");
                    return aDate;
                } else {
                    aDate = (Date) DateUtil.formatDate(value.toString(), "yyyy-MM-dd hh:mm:ss");
                    return aDate;
                }

            } catch (Exception e) {
                throw new ConversionException(e);


            }
        }

    }
}