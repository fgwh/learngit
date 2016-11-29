package com.hgsoft.component.mapper;

import com.hgsoft.component.NameMapper;

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
 * @Desc: 类名映射文件名规则
 */
public class CustomFileNameMapper implements NameMapper {

    private String baseDirectory;  //文件根目录

    public CustomFileNameMapper(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * 
     * @param name 文件名常规部分
     * @param fileCode 文件名机构部分
     * @return
     */
    @Override
    public String mapping(String name,String suffix) {
        StringBuffer fileName = new StringBuffer();
        if (name == null || name.length() < 1) {
            return baseDirectory + File.separator + "tmp."+suffix;
        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.DATE, -1);
//            Date date = calendar.getTime();
//            fileName.append(dateFormat.format(date) + "_" + name + "."+suffix);
            
            fileName.append(name).append(".").append(suffix);
        }

        return baseDirectory + File.separator + fileName.toString();
    }
}
