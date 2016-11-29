package com.hgsoft.excel;

import com.hgsoft.util.BusinessException;
import com.hgsoft.util.NumberTool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


public class POIExcel extends BaseExcel {


	/**
	 * 读取Excel文件，并把文件内的数据每行组装成一个Map对象，Key为列号，Value为单元格内容
	 * 
	 * @param excelPath
	 * @return Map集合
	 */
	public List readExcel(String excelPath) {
		InputStream inputStream = null;
		List result = new ArrayList();
		try {
			inputStream = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			int sheetNum = workbook.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				int rowCount = sheet.getPhysicalNumberOfRows();
				for (int j = 0; j < rowCount; j++) {
					Row row = sheet.getRow(j);
					if (row != null) {
						Map propValueMap = new HashMap();
						boolean notEmpty = false;
						int cellCount=row.getPhysicalNumberOfCells();
						for (int k = 0; k < cellCount; k++) {
							Cell cell = row.getCell(k);
							if (cell == null) {
								continue;
							}
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								//判断是不是日期格式
								if(DateUtil.isCellDateFormatted(cell)){
									propValueMap.put(String.valueOf(cell.getColumnIndex()), cell.getDateCellValue());
									notEmpty = true;
								}else{
									//读出数值
									double dValue=cell.getNumericCellValue();
									int iValue=(int)dValue;
									propValueMap.put(String.valueOf(cell.getColumnIndex()), dValue==iValue?String.valueOf(iValue):String.valueOf(dValue));
									notEmpty = true;
								}
							} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
								//读出字符串
								if("".equals(cell.getRichStringCellValue().getString().trim())){
									//......
								}else{
									propValueMap.put(String.valueOf(cell.getColumnIndex()), cell.getRichStringCellValue().getString());
									notEmpty = true;
								}
							}else if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
								//读出公式的结果
								propValueMap.put(String.valueOf(cell.getColumnIndex()), String.valueOf(cell.getNumericCellValue()));
								notEmpty = true;
							}else if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()){
								//读出布尔值
								propValueMap.put(String.valueOf(cell.getColumnIndex()), Boolean.valueOf(cell.getBooleanCellValue()));
								notEmpty = true;
							}else if(Cell.CELL_TYPE_BLANK == cell.getCellType()){
								//读出空值
								propValueMap.put(String.valueOf(cell.getColumnIndex()), "");
								notEmpty = true;
							}else if(Cell.CELL_TYPE_ERROR == cell.getCellType()){
								//读出错误值
								propValueMap.put(String.valueOf(cell.getColumnIndex()), cell.getErrorCellValue()+"");
								notEmpty = true;
							}else{
								propValueMap.put(String.valueOf(cell.getColumnIndex()), cell.getRichStringCellValue().getString());
								notEmpty = true;
							}
						}
						if(notEmpty){
							result.add(propValueMap);
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BusinessException(e.getMessage());
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 读取Excel文件，并把文件内的数据每行组装成一个Map对象，Key为字段名，Value为单元格内容
	 * 
	 * @param excelPath
	 * @param mm
	 * @return Map集合
	 */
	public List readExcel(String excelPath, ModelMapper mm){		
		InputStream inputStream = null;
		List result = new ArrayList();
//		Map typeMap = getTypeMap();
		try {
			inputStream = new FileInputStream(new File(excelPath));
			Workbook workbook = WorkbookFactory.create(inputStream);
//			int sheetNum = workbook.getNumberOfSheets();
			//导入只需读取第一sheet内的数据
			int sheetNum = 1;
			int startRow = mm.getStartRow();
			for (int i = 0; i < sheetNum; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				int rowCount = sheet.getPhysicalNumberOfRows();
				if(rowCount<1){
					continue;
				}
				//检测表头
				checkColHeader(sheet,mm);
				for (int j = 0; j < rowCount - startRow + 1; j++) {
					Row row = sheet.getRow(startRow - 1 + j);
					if (row != null) {
						Map propValueMap = new HashMap();

						boolean notEmpty = false;
						for (Iterator iter = mm.getProperties().entrySet().iterator(); iter.hasNext();) {
							Map.Entry entry = (Map.Entry) iter.next();
							Property pm = (Property) (entry.getValue());
							Cell cell = row.getCell(pm.getColumn());
							if (cell == null) {
								continue;
							}
//							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
							if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								//判断是不是日期格式
								if(DateUtil.isCellDateFormatted(cell)){
									propValueMap.put(pm.getName()+"", cell.getDateCellValue());
									notEmpty = true;
								}else{
									//读出数值
									double dValue=cell.getNumericCellValue();
									int iValue=(int)dValue;
									//log.info(String.valueOf(iValue));
									propValueMap.put(pm.getName(), dValue==iValue?String.valueOf(iValue):String.valueOf(dValue));
									notEmpty = true;
								}
//								else if (Cell.CELL_TYPE_STRING == cell.getCellType())
							} else if (Cell.CELL_TYPE_STRING ==  cell.getCellType()) {
								//读出字符串
								if("".equals(cell.getRichStringCellValue().getString().trim())){
									//......
								}else{
									propValueMap.put(pm.getName(), cell.getRichStringCellValue().getString().trim());
									notEmpty = true;
								}
							}else if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){
								//读出公式的结果
								propValueMap.put(pm.getName(), String.valueOf(cell.getNumericCellValue()));
								notEmpty = true;
							}else if(Cell.CELL_TYPE_BOOLEAN ==cell.getCellType()){
								//读出布尔值
								propValueMap.put(pm.getName(), Boolean.valueOf(cell.getBooleanCellValue()));
								notEmpty = true;
							}else if(Cell.CELL_TYPE_BLANK == cell.getCellType()){
								//空值不读出来
								//propValueMap.put(pm.getName(), "");
								//notEmpty = true;
							}else if(Cell.CELL_TYPE_ERROR == cell.getCellType()){
								//错误值不读出来
								//propValueMap.put(pm.getName(), cell.getErrorCellValue()+"");
								//notEmpty = true;
							}else{
								propValueMap.put(pm.getName(), cell.getRichStringCellValue().getString().trim());
								notEmpty = true;
							}
						}
						if(notEmpty){
							result.add(propValueMap);
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BusinessException(e.getMessage());
		}finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

//	private Map getTypeMap() {
//		Map typeMap = new HashMap();
//		typeMap.put("NUMERIC",0);
//		typeMap.put("STRING",1);
//		typeMap.put("FORMULA",2);
//		typeMap.put("BLANK",3);
//		typeMap.put("BOOLEAN",4);
//		typeMap.put("ERROR",5);
//		return typeMap;
//	}

	/**检查excle表的表头，并回去顺序
	 * @param sheet
	 * @param mm
	 * @return
	 * @throws
	 */
	private void checkColHeader(Sheet sheet, ModelMapper mm){
		//先把属性在Excel中所在的列号设置为-1，在进行检查表头时，如果存在属性对应的列，就把列的列号设置给属性，如果属性的列号为-1，就说明不存在属性对应的列
		for (Iterator iter = mm.getProperties().entrySet()
				.iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			Property pm = (Property) (entry.getValue());
			pm.setColumn(-1);
		}
		Row firstRow = sheet.getRow(1);
	    for (Iterator iter = firstRow.cellIterator(); iter.hasNext();) {
	    	Cell cell = (Cell) iter.next();
	        String cellValue = cell.getRichStringCellValue().getString();
	        for (Iterator iter2 = mm.getProperties().entrySet().iterator(); iter2.hasNext();) {
				Map.Entry entry = (Map.Entry) iter2.next();
				Property pm = (Property) (entry.getValue());
				if(pm.getTitle().equals(trimAllSpace(cellValue))){
					pm.setColumn(cell.getColumnIndex()); 
	            }
			}
        }
    	for (Iterator iter = mm.getProperties().entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			Property pm = (Property) (entry.getValue());
			if(pm.getColumn()==-1){
				//throw new BusinessException("在excel文件的第一行没有发现名为"+pm.getTitle()+"的列");
			}
		}
	}
	
	public void writeExcel(List modelList, ModelMapper mm, File file, String sheetStr) {
		HSSFWorkbook wb = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			HSSFSheet sheet = null;
			if(!file.exists()){
				wb = new HSSFWorkbook();
			}else{
				fis = new FileInputStream(file);		
				POIFSFileSystem fs = new POIFSFileSystem(fis);
		        wb = new HSSFWorkbook(fs);
			}	
			sheet = wb.createSheet(sheetStr);
			int row = writeColumnTitle(sheet, mm); // 写表头
			for (Iterator it = modelList.iterator(); it.hasNext();) {
				Object model = (Object) it.next();
				for (Iterator iter = mm.getProperties().entrySet().iterator(); iter
						.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					Property pm = (Property) (entry.getValue());
					//name为空不写入
					if(pm.getName() != null && pm.getName().trim().length() > 0) {
						String value = getPropertyValue(pm.getName(), model);
						writeCellValue(pm.getColumn(), row - 1, value, sheet, pm.getType());
					}
				}
				row++;
			}
			fos = new FileOutputStream(file);
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("error:" + e);
		} finally {
			try {
				if(fis != null){
					fis.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				//log.error("e:" + e);
			}
		}
	}
	
	/**
	 * 写表头
	 * 
	 * @return
	 */
	private int writeColumnTitle(HSSFSheet hs, ModelMapper mm) {
		int startRow = mm.getStartRow();
		// 如果设置为不写入表头，则不写表头，直接返回。
		if (!mm.isTitle())
			return startRow;
		int titleRow = startRow - 1; // 表头行为数据行的前一行。
		if (startRow == 1) { // 如果配置中数据行是第一行。
			// 设置表头行为第一行，数据行往下移一行。
			titleRow = 1;
			startRow = titleRow + 1;
		}
		for (Iterator iter = mm.getProperties().entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			Property pm = (Property) (entry.getValue());
			String value = pm.getTitle();
			writeCellValue(pm.getColumn(), titleRow - 1, value, hs, pm.getType());
			//设置列宽
			hs.setColumnWidth((pm.getColumn()), pm.getWidth());
		}
		return startRow;
	}

	// 往Excel表格中写入数据。
	private void writeCellValue(int column, int row, String value, HSSFSheet hs, String type) {
		try {
			HSSFCell cell;
			if (hs.getRow(row) == null) {
				cell = hs.createRow(row).createCell(column);

			} else {
				cell = hs.getRow(row).createCell(column);
			}			
			writeCell(cell, value, type);
		} catch (Exception ex) {
			//log.error(ex.getMessage(), ex);
		}
	}
	
	private void writeCell(HSSFCell cell, String value, String type) {
		if("double".equalsIgnoreCase(type)) {
			try {
				double dou = Double.parseDouble(value);
				cell.setCellValue(dou);
			} catch (NumberFormatException e) {
				cell.setCellValue(new HSSFRichTextString(value));
			}					
		}else {			
			cell.setCellValue(new HSSFRichTextString(value));
		}
		
	}
	
    // 去掉所有空格
	private String trimAllSpace(String s) {
        if(s == null) return null;
        return s.replaceAll(" ", "");
    }

	/**
	 * 读取excel中的double类型的数据
	 * @param cell
	 * @return
	 */
	public static Double getDoubleCellValue(Cell cell){
		if (cell==null){
			return null;
		}
		NumberFormat formatter = new DecimalFormat("#.000");
		if (Cell.CELL_TYPE_BLANK==cell.getCellType()){
			return null;
		} else {
			return Double.parseDouble(formatter.format(cell.getNumericCellValue())); 
		}
	}
	
	/***
	 * 读取excel中的Date类型的数据
	 * @param cell
	 * @return
	 */
	public static Date getDateCellValue(Cell cell){
		if (cell==null){
			return null;
		}
		if (Cell.CELL_TYPE_BLANK==cell.getCellType()){
			return null;
		}
		if (cell.getCellType()== Cell.CELL_TYPE_STRING){
			return com.hgsoft.util.DateUtil.parse(cell.getRichStringCellValue().getString(), com.hgsoft.util.DateUtil.PATTERN_STRING_DATE);
		}
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
        	return cell.getDateCellValue();  
        } else {   
        	throw new BusinessException();
        } 
	}
	
	/**
	 * 读取excel中的字符串类型的数据
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell==null){
			return "";
		}
        String ret = null;  
        try{
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_BLANK:
	            ret = "";  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case Cell.CELL_TYPE_ERROR:
	            ret = null;  
	            break;  
	        case Cell.CELL_TYPE_FORMULA:
	            Workbook wb = cell.getSheet().getWorkbook();
	            CreationHelper crateHelper = wb.getCreationHelper();
	            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
	            ret = (String)getCellValue(evaluator.evaluateInCell(cell));  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:
	            if (HSSFDateUtil.isCellDateFormatted(cell)) {
	            	return com.hgsoft.util.DateUtil.format(cell.getDateCellValue(),com.hgsoft.util.DateUtil.PATTERN_STRING_DATE);
	            } else {   
	            	return NumberTool.format(cell.getNumericCellValue(),0);  
	            }    
	           // return NumberTool.format(cell.getNumericCellValue(),0);
	        case Cell.CELL_TYPE_STRING:
	            return cell.getRichStringCellValue().getString();  
	        default:  
	            ret = null;  
	        }  
        }catch(Exception e){
        	e.printStackTrace();
        }
        return ret; //有必要自行trim  
    }

	public static void main(String[] args) {
		BaseExcel baseExcel = new POIExcel();
		baseExcel.importSingleExcel("testExcel/违法案件报表20160727.xls","com.hgsoft.main.illegal.entity.Illegal");

	}
}
