package com.hgsoft.parse;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hgsoft.txt.parse.entity.Pattern;

public abstract class DefaultConsumptionParse extends CommonHandle {
	private final static Log logger = LogFactory.getLog(DefaultConsumptionParse.class);
	
	public DefaultConsumptionParse(String templatePath) {
		super(templatePath);
	}

	@Override
	Object[] consumptionParse(Integer index,String line, Map<String, Pattern> patternMaps,
			String separator, Integer position,Integer maxPattern) throws Exception {
		Object[] obj = new Object[3];
		String result = ErrorMsg.r0000000;
		String[] contents = line.split(separator);
		if (contents == null || contents.length == 0) {
			logger.warn("模板列分隔符与文件指定的分隔符不一致");
			
			obj[0] = ErrorMsg.r1000003;
			obj[1] = position;
			return obj;
		}

		String values = "";
		int len = contents.length;
		for (int i = 0; i < len; i++) {
			if (i == (len - 1))
				values += contents[i].trim();
			else
				values += contents[i].trim() + ",";
		}

		Object bussiness = null;
		Pattern pattern = patternMaps.get(position + "");
		if(pattern == null)
		{
			logger.warn("不存在ID为"+position+"模板");
			obj[0] = ErrorMsg.r1000004;
			obj[1] = position;
			obj[2] = bussiness;
			return obj;
		}

		int columns = pattern.getColumns();
		
		if (len != columns) {
			position++;// ID递增1
			
			if((int)maxPattern < (int)position)
			{
				logger.warn("文件内容与模板定义的格式不一致,获取的模板ID为"+position+",处理的行号为"+index);
				obj[0] = ErrorMsg.r1000005;
				obj[1] = position;
				obj[2] = bussiness;
				return obj;
			}
			
			Pattern nextPattern = patternMaps.get(position + "");
			
			if(nextPattern == null)
			{
				logger.warn("不存在ID为"+position+"模板");
				obj[0] = ErrorMsg.r1000004;
				obj[1] = position;
				obj[2] = bussiness;
				return obj;
			}
			
			int nextColumns = nextPattern.getColumns();

			if (len != nextColumns) {
				logger.warn("第"+index+"行,文件列数和模板指定的列数不一致,模板ID为"+position+",文件列数为："+len+",模板列数为："+nextColumns);
				
				obj[0] = ErrorMsg.r1000006;
				obj[1] = position;
				obj[2] = bussiness;
				return obj;
			}

		//	bussiness = bussiness(nextPattern.getEntityName(),nextPattern.getFields(),values);
			bussiness = bussiness(nextPattern.getEntityName(),nextPattern.getFields().split(","),contents);

		} else {
			bussiness = bussiness(pattern.getEntityName(),pattern.getFields().split(","),contents);
		//	bussiness = bussiness( pattern.getEntityName(),pattern.getFields(),values);
		}

		
		obj[0] = result;
		obj[1] = position;
		obj[2] = bussiness;
		return obj;
	}

	abstract Object bussiness(String table,String fields,String values);
	abstract Object bussiness(String className,String[] heads,String[] values) throws Exception;

}
