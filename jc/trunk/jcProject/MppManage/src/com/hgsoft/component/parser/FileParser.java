package com.hgsoft.component.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hgsoft.component.BaseParser;
import com.hgsoft.component.MyDelimitedLineTokenizer;
import com.hgsoft.component.NameMapper;
import com.hgsoft.component.model.DataHolder;
import com.hgsoft.job.Common;

/**
 * com.hgsoft.component.parser
 *
 * @Project: QuartzTask
 * @Date: 2013/15/28
 * @Author: zhouzhaofeng
 * @Desc: 把DataHolder持有的所有对象，转换成File
 */
public class FileParser implements BaseParser<File> {

    private static final Character DELIMITER = '\t';  //数据分隔符
    private static Log logger = LogFactory.getLog(FileParser.class);
    private DataHolder[] holders;  //需要转换的对象集合
    private NameMapper mapper;      //对象到File文字的映射器
    private File[] files;           //生成的文件组
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    private final SimpleDateFormat sdfyMdHms = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    private final static String DATE_CLASS = "java.util.Date";

    public FileParser(DataHolder[] holders, NameMapper mapper,String suffix) {
        this.holders = holders;
        this.mapper = mapper;
        initialize(suffix);
    }

    @Override
    public void initialize(String suffix) {
        if (holders != null && mapper != null) {
            files = new File[holders.length];
            for (int i = 0; i < holders.length; i++) {
                //使用mapper映射文件名
                files[i] = new File(mapper.mapping(holders[i].getCustomFileName(),suffix));  //初始化文件组
            }
        }
    }

    @Override
    public File[] parse(int fileType) {
        logger.info("=============File Parser================");
        BufferedWriter bw = null;
        String encoding = "GBK";
        try {
            for (int i = 0; i < holders.length; i++) {
                File file = files[i];  //需要写的文件
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        logger.info("文件不存在，新建文件");
                    }
                } else {
                    file.delete();
                    if (file.createNewFile()) {
                        logger.info("文件已经存在，覆盖原文件");
                    }
                }
               
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));

                DataHolder holder = holders[i];
                String className = holder.getClassName();  //需要保存为文件的名字
                String[] needFields = holder.getFieldNames();  //需要写的字段
                Object[] beans = holder.getRecords().toArray();  //记录

                Class clazz = Class.forName(className);

                //写文件头
                Field[] fields = clazz.getDeclaredFields();
                //在写第一行属性头的时候获取需要写的属性
                if (needFields != null && needFields.length > 0) {
                    fields = fieldFilter(fields, needFields);
                }
                /*if (Common.FILETYPE_PARKCONSUMPTION != fileType) {
                	
                	StringBuffer head = new StringBuffer();

	            	 for (Field field : fields) {
	                     head.append(field.getName());
	                     head.append(DELIMITER);
	                 }
                     bw.write(head.substring(0, head.length() - 1) + "\n");  //忽略最后一个分隔符
                }*/

                logger.debug("需要写"+beans.length +"条记录");
                Object result = null;
                Date date = null;
                for (int j = 0; j < beans.length; j++) {
                    Object bean = beans[j];
                    StringBuffer line = new StringBuffer();
                    //========================================
                    for (Field field : fields) {
                        String name = field.getName(); //属性名称
                        String firstLetter = name.substring(0, 1).toUpperCase();
                        String getMethodName = "get" + firstLetter + name.substring(1);
                        Method getMethod = clazz.getMethod(getMethodName);
                        Class returnType = getMethod.getReturnType();
                        //通过反射获取日期类型
                        if(DATE_CLASS.equals(returnType.getName()))
                        {
                        	date = (Date)getMethod.invoke(bean); //Get方法拿到的值
                        	if (fileType == 6) {
                        		result = sdfyMdHms.format(date);
                        	} else {
                            	result = sdfyyyyMMdd.format(date);
                        	}
                        	line.append(result);
                        	
                        }
                        else
                        {
                        	 result = getMethod.invoke(bean); //Get方法拿到的值
                        	 line.append(String.valueOf(result));
                        }
                       
                        line.append(DELIMITER);
                    }
                    bw.write(line.substring(0, line.length() - 1) + "\n");
                }
                bw.flush();
                logger.debug("Flush stream.");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    logger.error("文件流关闭失败");
                }
            }
        }

        return files;  //返回已经写完的文件
    }

    @Override
    public void destroy() {
    }

    /**
     * 过滤出需要的属性
     *
     * @param fields     所有的属性
     * @param needFields 需要的属性
     * @return 新的属性数组
     */
    private Field[] fieldFilter(Field[] fields, String[] needFields) {
        Field[] newFields = new Field[needFields.length];
        int idx = 0;
        for (int i = 0; i < needFields.length; i++) {  //遍历所有需要字段
            String need = needFields[i].toLowerCase();
            boolean isFound = false;
            for (Field field : fields) {
                if (need.equals(field.getName().toLowerCase())) {  //属性存在
                    logger.info("Bean提取属性：" + field.getName());
                    newFields[idx++] = field;
                    isFound = !isFound;
                    break;
                }
            }
            if (!isFound) {
                logger.warn("实体类找不到需要过滤的属性：" + need);
            }
        }
        if (idx < needFields.length) {  //有属性找不到
            newFields = Arrays.copyOf(newFields, idx);
        }
        logger.info("需要写的属性个数：" + newFields.length);
        return newFields;
    }
    
    public File[] parseParkBlackData(int fileType) {
        logger.info("=============File Parser================");
        BufferedWriter bw = null;
        String encoding = "GBK";
        try {
            for (int i = 0; i < holders.length; i++) {
                File file = files[i];  //需要写的文件
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        logger.info("文件不存在，新建文件");
                    }
                } else {
                    file.delete();
                    if (file.createNewFile()) {
                        logger.info("文件已经存在，覆盖原文件");
                    }
                }
               
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));

                DataHolder holder = holders[i];
                Object[] beans = holder.getRecords().toArray();  //记录

                logger.debug("需要写"+beans.length +"条记录");
                
                for (int j = 0; j < beans.length; j++) {
                    Object[] objects = (Object[])beans[j];
                    StringBuffer line = new StringBuffer();
                    //========================================
                    for (Object bean : objects) {
                    	line.append(bean==null?"":bean);
                    	line.append(DELIMITER);
                    }
                    bw.write(line.substring(0, line.length() - 1) + "\n");
                }
                bw.flush();
                logger.debug("Flush stream.");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    logger.error("文件流关闭失败");
                }
            }
        }

        return files;  //返回已经写完的文件
    }
}
