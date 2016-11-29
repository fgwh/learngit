package com.hgsoft.component.mapper;

import com.hgsoft.component.NameMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * com.hgsoft.component.mapper
 *
 * @Project: QuartzTask
 * @Date: 2013/20/29
 * @Author: zhouzhaofeng
 * @Desc: 文件名到类名的映射
 */
public class FileToClassMapper implements NameMapper {
    private static Log logger = LogFactory.getLog(FileToClassMapper.class);
    private String baseDirectory;  //文件根目录

    public FileToClassMapper() {
    }

    public FileToClassMapper(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    //example:e:/front/313_20131101_TB_User.txt===>User
    @Override
    public String mapping(String value,String suffix) {
        String[] pieces = value.split("_");
        String className = pieces[pieces.length - 1];
        int dotIdx = className.indexOf(".");
        className = className.substring(0, dotIdx);

        if (className.equals("OBU")) {
            className = "Obu";
        }
        logger.info(value + " 映射到类名--> " + className + "Recv");
        return "com.hgsoft.main.entity." + className + "Recv";
    }
}
