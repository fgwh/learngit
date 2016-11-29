package com.hgsoft.excel;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * @author
 * @date 2015年5月24日 上午9:34:50
 *
 */
public interface IJxlsResultEvent {

	/**
	 * 
	 */
	void afterJxlsResult(Workbook workbook);

}
