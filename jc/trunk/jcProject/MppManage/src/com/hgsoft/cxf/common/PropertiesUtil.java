package com.hgsoft.cxf.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Created by Bruce.Zhan on 2014/8/11.
 */
public class PropertiesUtil {

    private final static Logger log = Logger.getLogger(PropertiesUtil.class);
    private final static String PROPERTY_FILE = "/class_name.properties";
    /**
     * Created by Bruce.Zhan on 2014/8/11.
     * 获取Properties资源文件内容
     *
     * @param key 要获取的值对应的键
     * @return
     */
    public static String getProperty(String key) {
        return getPropertyByFileNameAndKey(PROPERTY_FILE,key);
        /*Properties prop = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
            prop.load(in);
            in.close();
            return prop.getProperty(key);
        } catch (FileNotFoundException e) {
            log.error(e);
            return null;
        } catch (IOException e) {
            log.error(e);
            return null;
        }*/
    }

    /**
     * 通过文件名,要获取的值对应的键 获取键对应的值
     * @param filePath 如"/class_name.properties"
     * @param key
     * @return
     */
    public static String getPropertyByFileNameAndKey(String filePath,String key){
        Properties prop = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            prop.load(in);
            in.close();
            return prop.getProperty(key);
        } catch (FileNotFoundException e) {
            log.error(e);
            return null;
        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }
    
}
