package com.hgsoft.interceptor;

import org.springframework.stereotype.Component;

import com.hgsoft.license.License;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
public class LicenseInterceptor implements Interceptor {

	private static final long serialVersionUID = -8945250576531966244L;
	
	private String redirectUrl = "../login.do";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext request = ActionContext.getContext();
		
		try {
			if(!License.check()) {
				request.put("message", "系统访问受限，请联系产商");
				request.put("result", "RELOAD");
				request.put("redirectUrl", redirectUrl);
				return "error";
			}
		} catch (Exception e) {
			request.put("message", "系统访问受限，请联系产商");
			request.put("result", "RELOAD");
			request.put("redirectUrl", redirectUrl);
			return "error";
		}
		
		return invocation.invoke();
	}

}
