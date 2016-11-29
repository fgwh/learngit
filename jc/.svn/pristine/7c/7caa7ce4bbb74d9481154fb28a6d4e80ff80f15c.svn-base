package com.hgsoft.security.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: CustomRolesAuthorizationFilter.java
 * @Date: 14-6-4
 * @Time: 上午9:17
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {
    @Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String aRolesArray : rolesArray) {
            if (subject.hasRole(aRolesArray)) {
                return true;
            }
        }
        return false;
    }
}
