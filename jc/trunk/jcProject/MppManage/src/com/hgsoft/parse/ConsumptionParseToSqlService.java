package com.hgsoft.parse;

public class ConsumptionParseToSqlService extends DefaultConsumptionParse{

	public ConsumptionParseToSqlService(String templatePath) {
		super(templatePath);
	}

	@Override
	Object bussiness(String table,String fields,String values) {
		StringBuffer sb = new StringBuffer();

		sb.append("insert into ");
		sb.append(table);
		sb.append("(");
		sb.append(fields);
		sb.append(")");
		sb.append(" values (");
		sb.append(values);
		sb.append(")");

		return sb.toString();
	}

	Object bussiness(String className, String[] heads, String[] values) throws Exception {
		return null;
	}
}
