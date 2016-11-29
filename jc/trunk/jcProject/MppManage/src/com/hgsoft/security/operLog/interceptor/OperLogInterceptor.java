package com.hgsoft.security.operLog.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.OperLog;
import com.hgsoft.security.operLog.annotation.Description;
import com.hgsoft.security.operLog.service.OperLogService;
import com.hgsoft.security.service.AdminService;
import com.hgsoft.security.service.ModuleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


@SuppressWarnings("serial")
@Component
public class OperLogInterceptor implements Interceptor {
	
	@Resource
	private ModuleService moduleService;
	
	@Resource
	private AdminService adminService;
	
	@Resource
	private OperLogService operLogService;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Class clazz=invocation.getAction().getClass(); //获取类对象
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> parameters = invocation.getInvocationContext().getParameters();
		String mid = null;
		Object [] obj = (Object[]) parameters.get("mid");
		if(obj!=null && obj.length>0){
			mid = (String) obj[0];
			context.getSession().put("mid", mid);
		}else if(context.getSession().get("mid")!=null){
			mid =  context.getSession().get("mid").toString();
		}
		Admin operator  = (Admin)SecurityUtils.getSubject().getSession().getAttribute("operator");
		OperLog operLog = new OperLog();
		operLog.setAdmin(operator);
		operLog.setDetails(clazz.getName()+"/"+invocation.getProxy().getMethod());
		operLog.setIp(getRemoteHost(ServletActionContext.getRequest()));
		operLog.setModuleId(mid==null?"":mid);
		operLog.setOperTime(new Date());
		operLog.setOperType(getOperType(invocation.getProxy().getMethod()));
		operLog.setRemark(getDetails(clazz,invocation.getProxy().getMethod()));
		operLog.setOrgId(null==operator?"":operator.getOrg().getId().toString());
		operLogService.save(operLog);
		return invocation.invoke();
	}
	
	
	/*
	 * 获得操作类型
	 */
	public String getOperType(String method) throws NoSuchMethodException, SecurityException{
		if(method != null){
			if(method.startsWith("add") || method.startsWith("save") || method.startsWith("insert")){
				return "1";
			}else if(method.startsWith("update") || method.startsWith("edit")){
				return "2";
			}else if(method.startsWith("del")){
				return "3";
			}/*else if(method.startsWith("get") || method.startsWith("list") || method.startsWith("find")){
				return 4;
			}*/else{
				return "4";
			}
		}
		return "4";
	}
	
	/*
	 * 取得详细信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getDetails(Class clazz,String method) throws NoSuchMethodException, SecurityException{
		Method currentMethod = clazz.getMethod(method);
		Description desc = currentMethod.getAnnotation(Description.class);
		if(desc!=null){
			return desc.details();
		}
		return null;
	}
	
	/*
	 * 取得ip
	 */
	public String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
}

	
