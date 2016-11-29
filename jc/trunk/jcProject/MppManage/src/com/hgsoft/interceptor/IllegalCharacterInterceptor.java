package com.hgsoft.interceptor;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings({ "serial", "rawtypes" })
@Component("illegalCharacterInterceptor")
public class IllegalCharacterInterceptor extends AbstractInterceptor {
	
	@SuppressWarnings("deprecation")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Enumeration enumeration = request.getParameterNames();
		List<CharSequence> charSequenceList = new ArrayList<CharSequence>();
		charSequenceList.add("exec");
		charSequenceList.add("xp_cmdshell");
		charSequenceList.add("select");
		charSequenceList.add("insert");
		charSequenceList.add("update");
		charSequenceList.add("delete");
		charSequenceList.add("drop");
		charSequenceList.add("create");
		charSequenceList.add("rename");
		charSequenceList.add("truncate");
		charSequenceList.add("alter");
		charSequenceList.add("exists");
		charSequenceList.add("master");
		charSequenceList.add("restore");
		charSequenceList.add("script");
		charSequenceList.add("<script");
		charSequenceList.add("src");
		charSequenceList.add("behaviour");
		charSequenceList.add("'");
		//charSequenceList.add(";");
		charSequenceList.add("%");
		
		while(enumeration.hasMoreElements()){
			String fieldName = (String)enumeration.nextElement();
			Object object = request.getParameter(fieldName);
			
			if(object instanceof String){
				String fieldValue = (String)object;
				for(CharSequence charSequence : charSequenceList){
					if(fieldValue.toLowerCase().contains(charSequence)){
						request.setAttribute("dangerousInputValues", charSequence);
						return "dangerousInputValues";
					}
					
					if(fieldValue.toLowerCase().contains("%")){
						String fieldValueTemp = fieldValue.replace("%", "_");
						if(URLDecoder.decode(fieldValueTemp.toLowerCase()).contains(charSequence)){
							request.setAttribute("dangerousInputValues", charSequence);
							return "dangerousInputValues";
						}
					}
				}
				
			}
			
		}
		return invocation.invoke();
	}

}
