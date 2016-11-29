package com.hgsoft.security.util;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.apache.struts2.ServletActionContext;


public class PassThruAuthenticationFilter extends AuthenticationFilter {
	private String url ="/admin/mobile_unlogin.do";//Mobil not login will be redirected to here

	@Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else if("Mobile".equals(request.getParameter("deviceType"))){//Mobil UnLogin
			//WebUtils.issueRedirect(request, response, url);
        	 response.setContentType("application/json;charset=UTF-8");
             PrintWriter out = response.getWriter();
             JSONObject result = new JSONObject();
             result.put("map", "unlogin");
             out.print(result);
             out.flush();
             out.close();
			return false;
		}else {
            saveRequestAndRedirectToLogin(request, response);//PC UnLogin
            return false;
        }
    }

}
