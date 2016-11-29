package com.hgsoft.excel;



/**
 * 方便在EXCEL模板中调用的工具方法
 * 
 */
public class JxlsHelper {

	/**
	 * 获取代码表字段名称
	 * 
	 * @param code
	 * @return
	 */
	public String getDMCodeName(String code) {
		// TODO:
		return (String) null;
	}

	/**
	 * 根据机构ID获取机构名称
	 * 
	 * @param orgId
	 * @return
	 */
	public String getOrgName(String orgId) {
		// TODO:
		try {
			return null;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据用户ID获取用户名称
	 * 
	 * @param orgId
	 * @return
	 */
	public String getUserName(String userId) {
		try {
			//return SystemUtil.getUserName(userId);
			return userId;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public String substring(String str, int beginIndex, int endIndex) {
		try {
			return str.substring(beginIndex, endIndex);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化字符串
	 * 
	 * @param fmt
	 * @param args
	 * @return
	 */
	public String format(String fmt, double value) {
		return value == 0 ? "" : String.format(fmt, value);
	}
}
