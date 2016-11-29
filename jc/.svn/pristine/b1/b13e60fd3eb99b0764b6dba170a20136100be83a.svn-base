package com.hgsoft.excel;

import com.opensymphony.xwork2.ActionInvocation;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Quake Wang
 * @since 2007-3-22
 * @version $Revision: 1.2 $
 */
public class JxlsResult extends StrutsResultSupport {
	private static final String contentType = "application/vnd.ms-excel;charset=GBK";
	private static final String actionKey = "action";

	private boolean multiSheet = false;
	private String listName;
	private String sheetName;
	private String beanName;
	private int repeatSheetNum;

	protected String inputName;
	protected String fileName;

	protected JxlsHelper helper = new JxlsHelper();

	@SuppressWarnings("unchecked")
	public void doExecute(String location, ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType(contentType);

		String filename = getFileName(location, invocation);
		filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		InputStream in = null;
		Workbook workbook;
		try {
			in = getTemplate(location, invocation);
			XLSTransformer transformer = new XLSTransformer();
			Map beans = PropertyUtils.describe(invocation.getAction());
			beans.put(actionKey, invocation.getAction());
			beans.put("helper", helper);
			addRequestAttributes(beans);

			workbook = transformer.transformXLS(in, beans);
			IJxlsResultEvent event = (IJxlsResultEvent) beans.get("IJxlsResultEvent");
			if (event != null) {
				event.afterJxlsResult(workbook);
			}
			IOUtils.closeQuietly(in);

			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 添加请求属性
	 * 
	 * @param beans
	 */
	@SuppressWarnings("unchecked")
	private void addRequestAttributes(Map beans) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Enumeration attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String name = (String) attributeNames.nextElement();
			beans.put(name, request.getAttribute(name));
		}
	}

	protected InputStream getTemplate(String location, ActionInvocation invocation) {

		if (!StringUtils.isEmpty(inputName))
			return (InputStream) invocation.getStack().findValue(conditionalParse(inputName, invocation));

		ServletContext servletContext = ServletActionContext.getServletContext();
		return servletContext.getResourceAsStream(location);
	}

	protected String getFileName(String location, ActionInvocation invocation) {

		if (!StringUtils.isEmpty(fileName)) return fileName;

		ServletContext servletContext = ServletActionContext.getServletContext();
		return new File(servletContext.getRealPath(location)).getName();
	}

	// getters and setters
	public boolean isMultiSheet() {
		return multiSheet;
	}

	public void setMultiSheet(boolean multiSheet) {
		this.multiSheet = multiSheet;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getListName() {

		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public int getRepeatSheetNum() {
		return repeatSheetNum;
	}

	public void setRepeatSheetNum(int repeatSheetNum) {
		this.repeatSheetNum = repeatSheetNum;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}