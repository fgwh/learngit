package com.hgsoft.component;


/**
 * com.hgsoft.component.parser
 *
 * @Project: QuartzTask
 * @Date: 2013/14/27
 * @Author: zhouzhaofeng
 * @Desc: 基础转换器
 */
public interface BaseParser<T> {
    public void initialize(String suffix);
    public T[] parse(int FileType);
    public void destroy();
}
