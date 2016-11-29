package com.hgsoft.interceptor;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
@SuppressWarnings("rawtypes")
public class PermissionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	@Resource
    AdminService adminService;
	private String redirectUrl = "../login.do";
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext request = ActionContext.getContext();
		String basePath = ServletActionContext.getRequest().getContextPath();
		String uri = ServletActionContext.getRequest().getRequestURI().replaceFirst(basePath, "");
		
		Map session = request.getSession();
		Admin operator = (Admin)session.get("operator");
		

		if(!uri.equals("/login.do") && !uri.equals("/randomCode.do")){
			if(operator != null){
				operator = adminService.find(operator.getId());
				session.put("operator", operator);
			}
			if(operator == null){
				request.put("message", "您还未登录或者登录已超时，请重新登陆");
				request.put("result", "RELOAD");
				request.put("redirectUrl", redirectUrl);
				return "logout";
			}
			
			/*Map sessionMap = invocation.getInvocationContext().getSession();  
	        if (sessionMap.get("operator") != null) {  
	        	Admin userInfo = (Admin) sessionMap.get("operator");  
	   
	            if (OnlineStatistics.isOld(userInfo)) {  
	                sessionMap.remove("operator");// 清除session  
	                request.put("message", "对不起，您的帐号[" + userInfo.getName() + "]已在别的地方登录，您已被迫退出。若有疑问请联系管理员，谢谢！");
	                request.put("result", "RELOAD");
					request.put("redirectUrl", redirectUrl);
	        
	                return "logout";  
	            } 
	        }*/

//			String functions = ";" + adminService.getFunctions(operator) + ";";
//			session.put("functions", functions);
//			
//			if(functions == null || !functions.contains(";" + uri + ";")){
//				request.put("message", "您的操作权限不足，请联系管理员");
//				return "error";
//			}
		}
        
		return invocation.invoke();
	}

	public void init() {
	}
	
	public void destroy() {
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}
