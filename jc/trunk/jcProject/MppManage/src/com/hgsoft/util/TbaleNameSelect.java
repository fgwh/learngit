package com.hgsoft.util;

public class TbaleNameSelect {

	/**
	 * 
	 * @param beginYearTable
	 *            开始日期
	 * @param endYearTable
	 *            结束日期
	 * @param selectTable
	 *            需要替换的数据表
	 * @param sql
	 *            查询的SQL
	 * @return 返回SQL字符串
	 */
	public static String overYearSqlConvert(String beginYearTable, String endYearTable, String selectTable[],
			String sql) {
		int beginYear = 0;
		int endYear = 0;
		StringBuffer selectSql = new StringBuffer();
		if (!StringUtil.isTrimEmpty(beginYearTable) && !StringUtil.isTrimEmpty(endYearTable)) {
			beginYear = Integer.valueOf(beginYearTable.substring(0, 4));
			endYear = Integer.valueOf(endYearTable.substring(0, 4));

			if (beginYear > endYear) {
				int c = endYear;
				endYear = beginYear;
				beginYear = c;
			}
			String sql2 = "";
			for (int i = beginYear; i <= endYear; i++) {
				 sql2 = sql;
				for (int j = 0; j < selectTable.length; j++) {
					sql2 = sql2.replace(selectTable[j], selectTable[j] + "_" + i);
				}
				selectSql.append(sql2);
				if(i < endYear){
					selectSql.append(" union ");
				} 
			}
		}
		return selectSql.toString();
	} 
	
	public static void main(String[] args) {
		String selectTable[] = new String[] { "tb_GoodsInspection", "tb_Declare" };
		String sql = "select * from tb_GoodsInspection,tb_Declare,tb_GoodsInspection where VehPlate like '%V35312%' and VehColor=1002 and SquadDate >='2014-01-02' and SquadDate <='2014-03-01'";
		String sql2 = TbaleNameSelect.overYearSqlConvert("2014-01-02", "2018-03-01", selectTable, sql);
		System.out.println("sql2："+sql2);
	}
}
