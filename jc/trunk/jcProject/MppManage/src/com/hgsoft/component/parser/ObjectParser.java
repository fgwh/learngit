package com.hgsoft.component.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hgsoft.component.BaseParser;
import com.hgsoft.component.NameMapper;
import com.hgsoft.component.model.DataHolder;

/**
 * com.hgsoft.component.parser
 *
 * @Project: QuartzTask
 * @Date: 2013/15/27
 * @Author: zhouzhaofeng
 * @Desc: 把所有文件列表都转成Bean对象
 */
public class ObjectParser implements BaseParser<DataHolder> {
    private static final Character DELIMITER = '\t';  //数据分隔符
    private static Log logger = LogFactory.getLog(ObjectParser.class);
    private File[] files;
    private NameMapper mapper;
    private String[] classNames;

    public ObjectParser(File[] files, NameMapper mapper,String suffix) {
        this.files = files;
        this.mapper = mapper;
        initialize(suffix);
    }

    @Override
    public void initialize(String suffix) {
        logger.info("初始化ObjectParser");
        //完成文件名到类名的映射
        if (files != null && files.length > 0 && mapper != null) {
            classNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                classNames[i] = new String(mapper.mapping(files[i].getName(),suffix));
            }
        } else {
            logger.warn("ObjectParser初始参数不正确");
        }
    }

    /**
     * 将一组文件对象转换成DataHolder对象数组
     *
     * @return
     */
 /*   @Override
    public DataHolder[] parse(int fileType) {
        logger.info("================Object parse==========================");
        final String encoding = "GBK";
        boolean flag = true;
        int resultCode = 0;
        if (files != null) {  //文件不能为空
            //DataHolder[] objects = new DataHolder[files.length];  //需要返回的结果集
        	List<DataHolder> lists = new ArrayList<DataHolder>(files.length);
            for (int i = 0; i < files.length; i++) {
            	flag = true; 
            	
                File file = files[i];
                BufferedReader br = null;
                DataHolder dataHolder = null;
                if (file != null && file.exists()) {
                    logger.info(file.getName() + "准备转换");
                    try {
                        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                        br = new BufferedReader(read);
                        logger.info("文件编码:" + encoding);
                        //按照顺序保存属性键值对
                        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
                        String headLine = br.readLine();  //读文件头
                        String[] heads = decodeLine(headLine, fileType);
                        for (int j = 0; j < heads.length; j++) {
                            fieldMap.put(heads[j].toLowerCase(), "");  //初始化属性map,key全部以小写保存
                        }
                        logger.info(file.getName() + "文件头包含的字段个数：" + heads.length);

                        LinkedList<Object> fileRecords = new LinkedList<Object>();  //记录从文本读入的行数据
                        String line;
                        while ((line = br.readLine()) != null) {
                            //logger.info(file.getName() + " readLine:" + line);
                            String[] fields = decodeLine(line, fileType);
                            if (fieldMap.size() == fields.length) {
                                int idx = 0;
                                for (String key : fieldMap.keySet()) {
                                    fieldMap.put(key, fields[idx++]);
                                }
                                fileRecords.add(objectAutowired(classNames[i], fieldMap));
                            } else {
                                logger.error("文件格式错误，文件头字段数量和数据项个数不一致！");
                                logger.error("期望的数据项个数：" + heads.length);
                                logger.error("实际的数据项格式：" + fields.length);
                                //return null;
                                resultCode = 5;
                                flag = false;
                                break;
                            }
                        }
                        if(flag)
                        {
                    	    DataHolder dataHolder = new DataHolder();
                            dataHolder.setClassName(classNames[i]);
	                        dataHolder.setRecords(fileRecords);
	                        lists.add(dataHolder);
	                       
                        }
                        dataHolder = new DataHolder();
                        dataHolder.setClassName(classNames[i]);
                        dataHolder.setRecords(fileRecords);
                    	dataHolder.setResultCode(resultCode);
                        lists.add(dataHolder);
                        
                        
                        
                    } catch (FileNotFoundException e) {
                    	resultCode = 2;
                        logger.error(file.getName() + "文件不存在!");
                    } catch (IOException e) {
                    	resultCode = 3;
                        logger.error("处理" + file.getName() + "时发生I/O错误！");
                    }catch (Exception e) {
                        logger.error("处理" + file.getName() + "时发生异常,错误描述："+e.getMessage());
                        throw new RuntimeException(e);
                    } 
                    
                    finally {
                    	
                        try {
                            if (br != null) {
                                br.close();
                            }
                        } catch (IOException e) {
                            logger.error("流关闭发生异常！");
                        }
                    }
                }
                
                
                
                else {
                    logger.warn("找不到需要转换的文件：" + file.getName());
                }
                
                
            }
            DataHolder[] objects = new DataHolder[]{};
            objects =  lists.toArray(objects);
            return objects;
        } else {  //文件不存在
            logger.error("转换目标为空，请检查Parser初始参数是否有误！");
            return null;
        }
    }*/
    
    @Override
    public DataHolder[] parse(int handleType) {
        logger.info("================Object parse==========================");
        final String encoding = "GBK";
        boolean flag = true;
        int resultCode = 0;
        if (files != null) {  //文件不能为空
            //DataHolder[] objects = new DataHolder[files.length];  //需要返回的结果集
        	List<DataHolder> lists = new ArrayList<DataHolder>(files.length);
            for (int i = 0; i < files.length; i++) {
            	flag = true; 
            	
                File file = files[i];
                BufferedReader br = null;
                DataHolder dataHolder = null;
                if (file != null && file.exists()) {
                	 logger.info(file.getName() + "准备转换");
                	
                	InputStreamReader read = null;
                	LinkedList<Object> fileRecords = new LinkedList<Object>();  //记录从文本读入的行数据
					try {
						read = new InputStreamReader(new FileInputStream(file), encoding);
						br = new BufferedReader(read);
	                    logger.info("文件编码:" + encoding);
	                    
	                  //按照顺序保存属性键值对
                        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
                        String headLine = br.readLine();  //读文件头
                        String[] heads = decodeLine(headLine, handleType);
                        for (int j = 0; j < heads.length; j++) {
                            fieldMap.put(heads[j].toLowerCase(), "");  //初始化属性map,key全部以小写保存
                        }
                        logger.info(file.getName() + "文件头包含的字段个数：" + heads.length);

                        
                        String line;
                        while ((line = br.readLine()) != null) {
                            //logger.info(file.getName() + " readLine:" + line);
                            String[] fields = decodeLine(line, handleType);
                            if (fieldMap.size() == fields.length) {
                                int idx = 0;
                                for (String key : fieldMap.keySet()) {
                                    fieldMap.put(key, fields[idx++]);
                                }
                                fileRecords.add(objectAutowired(classNames[i], fieldMap));
                            } else {
                                logger.error("文件格式错误，文件头字段数量和数据项个数不一致！");
                                logger.error("期望的数据项个数：" + heads.length);
                                logger.error("实际的数据项格式：" + fields.length);
                                //return null;
                                resultCode = 5;
                                flag = false;
                                break;
                            }
                        }
					} catch (UnsupportedEncodingException e1) {
						logger.error(file.getName() + "不支持的文件编码!");
					} catch (FileNotFoundException e1) {
						resultCode = 2;
                        logger.error(file.getName() + "文件不存在!");
					} catch (IOException e) {
						resultCode = 3;
                        logger.error("处理" + file.getName() + "时发生I/O错误！");
					} catch (Exception e) {
                        logger.error("处理" + file.getName() + "时发生异常,错误描述："+e.getMessage());
                        throw new RuntimeException(e);
                    }  finally {
                    	
                        try {
                            if (br != null) {
                                br.close();
                            }
                        } catch (IOException e) {
                            logger.error("流关闭发生异常！");
                        }
                    }
                     
					dataHolder = new DataHolder();
					
                    dataHolder.setClassName(classNames[i]);
                    dataHolder.setRecords(fileRecords);
                	dataHolder.setResultCode(resultCode);
                	dataHolder.setResultCode(resultCode);
                    lists.add(dataHolder);
                   
                } else {
                    logger.warn("找不到需要转换的文件：" + file.getName());
                }
                
                
            }
            DataHolder[] objects = new DataHolder[]{};
            objects =  lists.toArray(objects);
            return objects;
        } else {  //文件不存在
            logger.error("转换目标为空，请检查Parser初始参数是否有误！");
            return null;
        }
    }
    
   /* public DataHolder[] dealParkConsumption() {
        logger.info("================Object parse==========================");
        final String encoding = "GBK";
        boolean flag = true;
        int resultCode = 0;
        if (files != null) {  //文件不能为空
            //DataHolder[] objects = new DataHolder[files.length];  //需要返回的结果集
        	String [] fileNames = {};
        	classNames = new String[] {"ParkConsumptionHeadRecv","","ParkConsumptionDetailRecv"};
        	List<DataHolder> lists = new ArrayList<DataHolder>(files.length);
            for (int i = 0; i < files.length; i++) {
            	flag = true; 
            	
                File file = files[i];
                BufferedReader br = null;
                DataHolder dataHolder = null;
                if (file != null && file.exists()) {
                	 logger.info(file.getName() + "准备转换");
                	
                	InputStreamReader read = null;
                	LinkedList<Object> fileRecords = new LinkedList<Object>();  //记录从文本读入的行数据
					try {
						read = new InputStreamReader(new FileInputStream(file), encoding);
						br = new BufferedReader(read);
	                    logger.info("文件编码:" + encoding);
	                    
	                  //按照顺序保存属性键值对
                        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
                        String headLine = br.readLine();  //读文件头
                        String[] heads = decodeLine(headLine, handleType);
                        for (int j = 0; j < heads.length; j++) {
                            fieldMap.put(heads[j].toLowerCase(), "");  //初始化属性map,key全部以小写保存
                        }
                        logger.info(file.getName() + "文件头包含的字段个数：" + heads.length);

                        
                        String line;
                        while ((line = br.readLine()) != null) {
                            //logger.info(file.getName() + " readLine:" + line);
                            String[] fields = decodeLine(line, handleType);
                            if (fieldMap.size() == fields.length) {
                                int idx = 0;
                                for (String key : fieldMap.keySet()) {
                                    fieldMap.put(key, fields[idx++]);
                                }
                                fileRecords.add(objectAutowired(classNames[i], fieldMap));
                            } else {
                                logger.error("文件格式错误，文件头字段数量和数据项个数不一致！");
                                logger.error("期望的数据项个数：" + heads.length);
                                logger.error("实际的数据项格式：" + fields.length);
                                //return null;
                                resultCode = 5;
                                flag = false;
                                break;
                            }
                        }
					} catch (UnsupportedEncodingException e1) {
						logger.error(file.getName() + "不支持的文件编码!");
					} catch (FileNotFoundException e1) {
						resultCode = 2;
                        logger.error(file.getName() + "文件不存在!");
					} catch (IOException e) {
						resultCode = 3;
                        logger.error("处理" + file.getName() + "时发生I/O错误！");
					} catch (Exception e) {
                        logger.error("处理" + file.getName() + "时发生异常,错误描述："+e.getMessage());
                        throw new RuntimeException(e);
                    }  finally {
                    	
                        try {
                            if (br != null) {
                                br.close();
                            }
                        } catch (IOException e) {
                            logger.error("流关闭发生异常！");
                        }
                    }
                     
					dataHolder = new DataHolder();
					
                    dataHolder.setClassName(classNames[i]);
                    dataHolder.setRecords(fileRecords);
                	dataHolder.setResultCode(resultCode);
                	dataHolder.setResultCode(resultCode);
                    lists.add(dataHolder);
                   
                } else {
                    logger.warn("找不到需要转换的文件：" + file.getName());
                }
                
                
            }
            DataHolder[] objects = new DataHolder[]{};
            objects =  lists.toArray(objects);
            return objects;
        } else {  //文件不存在
            logger.error("转换目标为空，请检查Parser初始参数是否有误！");
            return null;
        }
    }*/

    @Override
    public void destroy() {
        //TODO 释放资源
    }

    /**
     * 对象自动装配
     *
     * @param className 需要生成的类名
     * @param fieldMap  属性map
     * @return 返回生成的类对象
     */
    private Object objectAutowired(String className, LinkedHashMap<String, String> fieldMap) {
        try {
            Class clazz = Class.forName(className);
            Object bean = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();  //获取当前类所有属性
            for (Field field : fields) {
                String name = field.getName(); //属性名称
                String firstLetter = name.substring(0, 1).toUpperCase();
                String setMethodName = "set" + firstLetter + name.substring(1);
                Class paramType = field.getType();  //当前参数类型
                Method setMethod = clazz.getMethod(setMethodName, paramType);
                if (fieldMap.get(name.toLowerCase()) != null &&
                        fieldMap.get(name.toLowerCase()).trim().length() > 0) {
                    setMethod.invoke(bean, typeCast(fieldMap.get(name.toLowerCase()), paramType)); //调用setter方法赋值
                }
            }
            return bean;
        } catch (ClassNotFoundException e) {
            logger.error("自动装配类过程" + "找不到类:" + className);
        } catch (InstantiationException e) {
            logger.error("自动装配类过程" + "InstantiationException!");
        } catch (IllegalAccessException e) {
            logger.error("自动装配类过程" + "非法访问方法！");
        } catch (NoSuchMethodException e) {
            logger.error("自动装配类过程" + "找不到对应的方法！");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            logger.error("自动装配类过程" + "InvocationTargetException!");
        }

        return null;
    }

    /**
     * 类型转换
     *
     * @param value 需要转换类型的字符串
     * @param clazz 类型
     * @return
     */
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
            } catch (ParseException e) {
            	//logger.error(value + " cast to Date error,pattern is yyyy-MM-dd HH:mm:ss!");
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse(value);
                } catch (ParseException e1) {
                    logger.error(value + " cast to Date error,pattern is yyyy-MM-dd HH:mm:ss or yyyy-MM-dd!");
                }
            }
        }
        return null;
    }

    private String[] decodeLine(String line,int fileType) {
        LinkedList<String> arr = new LinkedList<String>();
        //根据分隔符读取每一行的数据，逐个读
        int index = 0;
        int count = 0;
    	 for (int i = 0; i < line.length(); i++) {
             char ch = line.charAt(i);
             if (ch == DELIMITER) {  //读到分隔符
                 arr.add(line.substring(index, i));
                 index = i + 1;
                 count++;
             }
         }
       
        if (index < line.length()) {
            arr.add(line.substring(index, line.length()));
        } else if (index == line.length()) {  //最后一个字符是制表符，加多一个字符
            arr.add("");  //最后一个元素为空字符串
        }
        return arr.toArray(new String[arr.size()]);
    }

}


