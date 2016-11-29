package com.hgsoft.excel.action;


import com.hgsoft.excel.ExcelModelUtil;
import com.hgsoft.excel.service.ExcelServcie;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * 通用导入excel action
 * @author 郭志强
 * @time 2016/9/16 13:42
 */
@Controller
@Scope("prototype")
public class ExcelImportAction extends ActionSupport {


    private HashMap message = new HashMap();

    public HashMap getMessage() {
        return message;
    }

    public void setMessage(HashMap message) {
        this.message = message;
    }

    @Resource
    protected ExcelServcie service;

    private String entityName;

    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;

    //提交过来的file的名字
    private String fileFileName;

    //提交过来的file的MIME类型
    private String fileContentType;

    //用来映射传入来的entityname 和全类名
    private static HashMap entityMap = new HashMap();

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    static {
        /**
         * 每次添加一个模块，都要在下面的map类当中，加一个模块名以及类的全名
         */
        entityMap.put("blackMD", "com.hgsoft.main.jcManange.entity.RoBlackList");
        entityMap.put("grayMD","com.hgsoft.main.jcManange.entity.RoGrayList");
    }

    @Override
    public String execute() throws Exception {
        // 判断文件是否为.xls或者.xlsx ,以及是否为空
        boolean nullOrIsExcel = isNullOrIsExcel();
        if (!nullOrIsExcel) {
            System.out.println(entityName);
            String root = ServletActionContext.getServletContext().getRealPath("/upload");
            InputStream is = new FileInputStream(file);
            OutputStream os = null;
            try {
                os = new FileOutputStream(new File(root, fileFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("fileFileName: " + fileFileName);

            // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
            System.out.println("file: " + file.getName());
            System.out.println("file: " + file.getPath());
            List list = null;
            try {
                list = ExcelModelUtil.importExcel(file.getPath(), entityMap.get(entityName).toString());
            } catch (Exception e) {
                e.printStackTrace();

            }
            String result = "";
            message.put("errors", "");
            try {
                result = service.saveBean(list, entityName);
            } catch (Exception e) {
                message.put("errors", "导入失败，请按要求填写格式。");
            }

            if (StringUtils.isNotBlank(result)) {
                message.put("errors", result);
            } else {
                message.put("success", true);
            }

            os.close();
            is.close();
        }
        return SUCCESS;
    }

    private boolean isNullOrIsExcel() {
        boolean flag = false;
        message.put("fileNameError", "");
        if (StringUtils.isBlank(fileFileName)) {
            message.put("fileNameError", "空文件名");
            flag = true;
        } else {
            String fileSuffixName = this.fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length());
            if (!"xls".equals(fileSuffixName) && !"xlsx".equals(fileSuffixName)) {
                message.put("fileNameError", "不是excel文件");
                flag = true;

            }

        }
        return flag;
    }



}
