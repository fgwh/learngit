package com.hgsoft.base.interfaces;

import com.hgsoft.base.entity.UploadFile;
import org.apache.poi.ss.usermodel.Sheet;

public interface ImportExcelFileInterface {
	
	/**运行导入动作*/
	public void importExcel();
	/**
	 * 导入的sheet文档
	 * @param sheet
	 */
	public void setSheet(Sheet sheet);
	/***
	 * 上传的文档
	 * @param uploadFile
	 */
	public void setUploadFile(UploadFile uploadFile);
	//public BaseEntity trancCellToObj(Row row) throws BusinessException;
}
