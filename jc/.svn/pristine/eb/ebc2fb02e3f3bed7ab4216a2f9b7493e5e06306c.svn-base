package com.hgsoft.excel;

import com.hgsoft.util.NumberTool;
import com.hgsoft.util.StrTool;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 读取配置文件。
 * 
 * @author luohc 2006-4-7
 * 
 * ExcelConfigLoader
 * 
 */
public class ExcelConfigLoader {
	
	public static final String CLASS_NAME = "className";
	public static final String START_ROW = "startRow";
	public static final String TITLE = "title";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_COLUMN = "column";
	public static final String PROPERTY_TITLE = "title";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_WIDTH = "width";

	public static final String MODEL_ID = "id";
	public static final String CONFIG_PARAM = "param";
	public static final String CONFIG_PARAM_NAME = "param-name";
	public static final String CONFIG_PARAM_VALUE = "param-value";
	public static final String CONFIG_RESOURCE_LOCATIONS = "resourceLocations";
	public static final String CONFIG_RELOADABLE = "reloadable";
	
	private static final String configFileName = "excel-model-config.xml";
	private static boolean reloadable=false;
	private static Map globalConfigMap;
	private static Map mappings;
	private static Map lastModifiedMap = new HashMap();
	
	static {

	}

	public final static Map getExcelModelConfig(String id) {
		// refresh(id);
		mappings = loadConfig(id);
		return mappings;
	}

	/**
	 * 检查配置文件是否被修改过
	 */
	private static void refresh(String id) {
		boolean needReload = false;
		if (reloadable) {
			for (Iterator iterator = lastModifiedMap.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String filePath = (String) entry.getKey();
				File file = new File(filePath);
				if (file.exists()
						&& file.lastModified() > ((Long) entry.getValue()).longValue()) {
					needReload = true;
					break;
				}
			}
		}
		needReload=true;
		if(needReload) {
			lastModifiedMap.clear();
			loadConfig(id);
		}
	}

	private static Map loadConfig(String classAllName) {
		globalConfigMap = loadGlobalConfig(classAllName);
		String reload = (String) globalConfigMap.get(CONFIG_RELOADABLE);
		if (StrTool.isBlankStr(reload)) {
			reloadable = false;
		} else {
			reloadable = Boolean.valueOf(reload).booleanValue();
		}
		String className = classAllName.substring(classAllName.lastIndexOf(".") + 1, classAllName.length());
		String rl = (String) globalConfigMap.get(CONFIG_RESOURCE_LOCATIONS +"-" + className);
		
		mappings = loadMappings(rl);
		return mappings;
	}


	private static Map loadGlobalConfig(String classAllName) {// id为传过来的类的全名
		Map params = new HashMap();
		try {
			URL xmlFile = Thread.currentThread().getContextClassLoader().getResource(configFileName);
			SAXReader reader = new SAXReader();
			Document document = reader.read(xmlFile);
			Element root = document.getRootElement();
			
			for (Iterator it = root.elementIterator(CONFIG_PARAM); it.hasNext();) {
				Element element = (Element) it.next();
				String name = element.element(CONFIG_PARAM_NAME).getText();
				String value = element.element(CONFIG_PARAM_VALUE).getText();
				params.put(name, value);
			}

			String className = classAllName.substring(classAllName.lastIndexOf(".") + 1, classAllName.length());
			String rl = (String) params.get(CONFIG_RESOURCE_LOCATIONS + "-" + className);
			if (StrTool.isBlankStr(rl)) {
				//throw new BusinessException("没有配置resourceLocations属性，无法加载资源文件。");
			}
			File file = new File(xmlFile.getFile());
			lastModifiedMap.put(file.getAbsolutePath(), new Long(file.lastModified()));
		} catch (DocumentException e) {
			e.printStackTrace();
//			throw new BusinessException("配置文件加载异常，请检查根目录下是否存在[" + configFileName
//					+ "]文件。");
		}
		return params;
	}
	
	private static Map loadMappings(String resourceLocations) {
		Map result = new HashMap();
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		try {
			long start = System.currentTimeMillis();
			System.out.println("开始获取资源 ------- " + new Date(System.currentTimeMillis()));
			System.out.println("正在查找资源： " + resourceLocations);
			Resource[] resources = pathResolver.getResources(resourceLocations);
			System.out.println("获取资源结束 ------- " + new Date(System.currentTimeMillis()));
			if(resources != null){
				System.out.println("开始加载 ------- " + new Date(System.currentTimeMillis()));
				for (int i = 0; i < resources.length; i++) {
					Map map = loadMappingsFromResource(resources[i]);
					result.putAll(map);
				}
				System.out.println("加载结束 ------- " + new Date(System.currentTimeMillis()));				
			}
			long end = System.currentTimeMillis();
			System.out.println("加载资源文件：" + resources.length + "个，耗时：" + (end - start) + "ms。");
		} catch (IOException e) {			
			//throw new BusinessException("资源加载异常：" + e);
		}
		return result;
	}
	
	private static Map loadMappingsFromResource(Resource resource) {
		Map map = new HashMap();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(resource.getURL());
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				ModelMapper mm = parseModel(element);
				map.put(mm.getId(), mm);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File file = resource.getFile();
			lastModifiedMap.put(file.getAbsolutePath(), new Long(file.lastModified()));
		} catch (IOException e) {
			//log.debug("不能获取resource最后修改时间：" + resource.getFilename());
		}
		return map;
	}

	private static ModelMapper parseModel(Element element) {
		ModelMapper mm = new ModelMapper(); // 读model元素定义。
		String id = element.attributeValue(MODEL_ID);
		String className = element.attributeValue(CLASS_NAME);
		String startRow = element.attributeValue(START_ROW);
		int intStartRow = NumberTool.parseInt(startRow);
		mm.setId(id);
		mm.setClassName(className);
		mm.setStartRow(intStartRow);
		String title = element.attributeValue(TITLE);
		if (!StrTool.isBlankStr(title)) {
			mm.setTitle(Boolean.valueOf(title).booleanValue());
		}
		mm.setProperties(parseModelProperties(element));
		return mm;
	}

	private static Map parseModelProperties(Element element) {
		Map propertiesMap = new HashMap();
		Iterator propsList = element.elementIterator();
		while (propsList.hasNext()) { // 读model元素的属性集合。
			Element prop = (Element) propsList.next();
			Property pm = new Property();
			String name = prop.attributeValue(PROPERTY_NAME);
			String s_column = prop.attributeValue(PROPERTY_COLUMN);
			String title = prop.attributeValue(PROPERTY_TITLE);
			String type = prop.attributeValue(PROPERTY_TYPE);

			String s_width = prop.attributeValue(PROPERTY_WIDTH);
			int column = NumberTool.parseInt(s_column);
			short width;
			try {
				width = Short.parseShort(s_width);
			} catch (NumberFormatException e) {
				width = 3000;
			}
			
			pm.setName(name);
			pm.setColumn(column);
			pm.setTitle(title);
			pm.setType(type);
			pm.setWidth(width);

			propertiesMap.put(name, pm);
		}
		return propertiesMap;
	}

//	public static void main(String[] args) {
//		Map map = ExcelConfigLoader.getExcelModelConfig();
//		Iterator iter = map.keySet().iterator();
//		while (iter.hasNext()) {
//			ModelMapper mm = (ModelMapper) map.get(iter.next());
//			System.out.println(mm.getClassName());
//			System.out.println(mm.getProperties());
//		}
//	}
}
