package com.hgsoft.security.Realm;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: IAuthController.java
 * @Date: 14-6-4
 * @Time: 下午12:51
 */
public interface IAuthController {
    /**
     * 加载过滤配置信息
     * @return
     */
    public String loadFilterChainDefinitions();

    /**
     * 重新构建权限过滤器
     * 一般在修改了用户角色、用户等信息时，需要再次调用该方法
     */
    public void reCreateFilterChains();
}
