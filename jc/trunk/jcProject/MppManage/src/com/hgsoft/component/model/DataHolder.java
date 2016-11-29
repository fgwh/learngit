package com.hgsoft.component.model;

import java.io.Serializable;
import java.util.List;

/**
 * com.hgsoft.component.parser
 *
 * @Project: QuartzTask
 * @Date: 2013/15/27
 * @Author: zhouzhaofeng
 * @Desc: 自定义封装数据对象，用于文本文件和实体类之间的转换
 */
public class DataHolder implements Serializable {
    private String className;  //保存的实体类类名
    private List<?> records; //实体类列表
    private String[] fieldNames;  //持有字段名数组
    private String customFileName;  //自定义文件名
    private int resultCode;  //错误码

    public String getCustomFileName() {
        return customFileName;
    }

    public void setCustomFileName(String customFileName) {
        this.customFileName = customFileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<?> getRecords() {
        return records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}
