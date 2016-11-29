package com.hgsoft.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hgsoft.txt.parse.common.XmlParseService;
import com.hgsoft.txt.parse.entity.Pattern;
import com.hgsoft.txt.parse.entity.Template;

public abstract class CommonHandle implements Handle {
	private final static Log logger = LogFactory.getLog(CommonHandle.class);
	private final static String ENCODING = "GBK";
	private String templatePath;
	protected final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	protected final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
			"yyyy-MM-dd");

	static class ErrorMsg {
		private final static Map<String, String> maps = new HashMap<String, String>();
		public final static String r0000000 = "0000000";
		public final static String r0000001 = "0000001";
		public final static String r0000002 = "0000002";
		public final static String r0000003 = "0000003";
		public final static String r0000004 = "0000004";
		public final static String r1000001 = "1000001";
		public final static String r1000002 = "1000002";
		public final static String r1000003 = "1000003";
		public final static String r1000004 = "1000004";
		public final static String r1000005 = "1000005";
		public final static String r1000006 = "1000006";
		static {
			maps.put(r0000000, "成功");
			maps.put(r0000001, "解析文件路径为空");
			maps.put(r0000002, "解析文件不存在");
			maps.put(r0000003, "文件格式有误");
			maps.put(r0000004, "字段缺失");
			maps.put(r1000001, "解析文件模板为空");
			maps.put(r1000002, "解析文件模板获取失败");
			maps.put(r1000003, "模板列分隔符与文件指定的分隔符不一致");
			maps.put(r1000004, "模板不存在指定ID的pattern");
			maps.put(r1000005, "文件内容与模板定义的格式不一致");
			maps.put(r1000006, "文件列数和模板指定的列数不一致");
		}

		public static Map<String, String> getMaps() {
			return maps;
		}
	}

	public CommonHandle(String templatePath) {
		this.templatePath = templatePath;
	}

	protected String checkout(String filePath) {
		if (filePath == null || "".equals(filePath)) {
			return ErrorMsg.r0000001;
		}

		File file = new File(filePath);
		if (!file.exists()) {
			return ErrorMsg.r0000002;
		}

		return ErrorMsg.r0000000;
	}

	@Override
	public LinkedList<ConsumptionEntry> parse(String datafilePath, Template template) {

		final String result = checkout(datafilePath);
		return ErrorMsg.r0000000.equals(result) ? parse(new File(datafilePath),
				template) : new LinkedList<ConsumptionEntry>();
	}

	@Override
	public LinkedList<ConsumptionEntry> parse(String datafilePath, Template template, String encoding) {
		final String result = checkout(datafilePath);
		return ErrorMsg.r0000000.equals(result) ? parse(new File(datafilePath),
				template, encoding) : new LinkedList<ConsumptionEntry>();
	}

	@Override
	public LinkedList<ConsumptionEntry> parse(File datafilePath, Template template) {
		return parse(datafilePath, template, ENCODING);
	}

	protected Template initialize() throws Exception {
		return XmlParseService.getInstance().parse(templatePath);
	}

	@Override
	public LinkedList<ConsumptionEntry> parse(File datafilePath, Template template, String encoding) {
		if (template == null) {
			try {
				template = initialize();
			} catch (Exception e) {
				writeLog(e, logger, CommonHandle.class);
				return null;
			}
		}
		// 获取内容分隔符
		final String separator = template.getSeparator();
		final Integer maxPattern = template.getMaxPattern();
		LinkedList<Pattern> patterns = template.getPatterns();

		Map<String, Pattern> patternMaps = new HashMap<String, Pattern>();
		for (Pattern pattern : patterns) {
			patternMaps.put(pattern.getId() + "", pattern);
		}

		InputStreamReader read = null;
		BufferedReader br = null;
		Integer position = new Integer(1);//解析模板的id
		int index = 0;//处理记录的行号
		String result = null;
		Object bean = null;
		LinkedList<ConsumptionEntry> consumptionEntrys = new LinkedList<ConsumptionEntry>();
		try {
			read = new InputStreamReader(new FileInputStream(datafilePath),
					encoding);
			br = new BufferedReader(read);

			String line = null;
			
			while ((line = br.readLine()) != null) {
				index++;
				if ("".equals(line.trim())) {
					logger.info("第"+index+"行的内容为空!");
				} else {
					Object[] obj = consumptionParse(index,line, patternMaps, separator, position,maxPattern);
					result = obj[0]+"";//返回异常信息编号
					position = (Integer)obj[1];//模板的ID
					bean = obj[2];//
					
					consumptionEntrys.add(new ConsumptionEntry(result,ErrorMsg.getMaps().get(result),bean));
					
				}
			}
			
			
			logger.info("处理文件["+datafilePath.getName()+"],记录共"+index+"条");

		} catch (Exception e) {
			writeLog(e, logger, CommonHandle.class);
		} finally {

			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					writeLog(e, logger, CommonHandle.class);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					writeLog(e, logger, CommonHandle.class);
				}
			}
		}

		return consumptionEntrys;
	}

	/**
	 * 
	 * @param index 行号
	 * @param line 行内容
	 * @param patternMaps 模板中样式的集合
	 * @param separator 列分隔符
	 * @param position 模板中样式的ID
	 * @return
	 * @throws Exception 
	 */
	abstract Object[] consumptionParse(Integer index,String line,
			Map<String, Pattern> patternMaps, String separator, Integer position,Integer maxPattern) throws Exception;

	@Override
	public void writeLog(Exception e, Log logger, Class<? extends Object> clazz) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			final String str = sw.toString();
			logger.error("[" + clazz.getName() + ","
					+ yyyyMMddHHmmss.format(new Date()) + "]: " + str);
		} finally {
			try {
				if (sw != null) {
					sw.close();
					sw = null;
				}
				if (pw != null) {
					pw.close();
					pw = null;
				}
			} catch (IOException io) {
				io.printStackTrace();
			}
		}

	}

}
