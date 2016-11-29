package com.hgsoft.component.transmission;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * com.hgsoft.util
 *
 * @Project: QuartzTask
 * @Date: 2013/11/27
 * @Author: zhouzhaofeng
 * @Desc: 正则表达式文件过滤器
 */
public class RegexNameFileFilter implements FTPFileFilter {

    private String regexStr;

    public RegexNameFileFilter(String regexStr) {
        this.regexStr = regexStr;
    }

    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     *
     * @param ftpFile The abstract file to be tested
     * @return <code>true</code> if and only if <code>pathname</code>
     *         should be included
     */
    @Override
    public boolean accept(FTPFile ftpFile) {
        boolean result = false;
        try {
            if (regexStr != null) {
                result =  Pattern.matches(regexStr,ftpFile.getName());
            }
        } catch (PatternSyntaxException patternSyntaxException) {
            patternSyntaxException.printStackTrace();
        }
        return result;
    }
}
