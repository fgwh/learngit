package com.hgsoft.component.mapper;

import com.hgsoft.component.NameMapper;
import com.hgsoft.listener.QuartzShutDownListener;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * com.hgsoft.component.parser
 *
 * @Project: QuartzTask
 * @Date: 2013/10/28
 * @Author: zhouzhaofeng
 * @Desc: 类名映射文件名规则，即是从类名到文件名的映射
 */
public class ClassToFileMapper implements NameMapper {

    private String baseDirectory;  //文件根目录

    public ClassToFileMapper(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public String mapping(String className,String suffix) {
        StringBuffer fileName = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, -1);
        Date date = calendar.getTime();
        fileName.append(dateFormat.format(date) + "_");

        if (fileName == null || fileName.length() < 1) {
            SessionFactory sessionFactory = QuartzShutDownListener.getSessionFactory();
            ClassMetadata cm = sessionFactory.getClassMetadata(className);
            AbstractEntityPersister persister = (AbstractEntityPersister) cm;
            String tableName = persister.getTableName();
            if (tableName.indexOf(".") != -1) {  //判断是否有schema或者catalog等信息
                tableName = tableName.substring(tableName.lastIndexOf(".") + 1, tableName.length());
            }
            //自定义表规则
            if (tableName.split("_").length == 3) {  //去掉Recv
                tableName = tableName.substring(0, tableName.lastIndexOf("_"));
            }
            fileName.append(tableName + "."+suffix);
        } else {
            fileName.append(fileName + "."+suffix);
        }
        return baseDirectory + File.separator + fileName.toString();
    }
}
