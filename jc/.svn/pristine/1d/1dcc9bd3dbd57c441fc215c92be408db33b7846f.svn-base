package com.hgsoft.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hgsoft.main.dao.DicItemDao;
import com.hgsoft.main.entity.DicItem;
import com.hgsoft.security.service.BaseService;
import com.hgsoft.util.Pager;
import com.hgsoft.util.StrTool;
import com.hgsoft.util.StringUtil;

/**
 * 字典服务类
 * @author kuangyc
 *
 */

@Service
public class DictionaryService extends BaseService<DicItem>{
	
	/** 内部缓存, Map<字典类型,List<字典项>> */
	private Map<String,List<DicItem>> cache = new HashMap<String,List<DicItem>>();

	/** 内部缓存, Map<字典类型,Map<值,名称>> */
	private Map<String,Map<String,String>> valueNameMap = new HashMap<String,Map<String,String>>();
	/** 内部缓存, Map<字典类型,Map<名称,值>> */
	private Map<String,Map<String,String>> nameValueMap = new HashMap<String,Map<String,String>>();

	/**
	 * 根据字典类型获取字典项
	 * @param type 字典类型
	 * @return 该类型下的所有字典项, 找不到则返回空的List
	 */
	@SuppressWarnings({ "unchecked" })
	public List<DicItem> getItems(String type){
		Assert.notNull(type, "字典类型不能为空");
		List<DicItem> list = cache.get(type);
		if(list==null){
			list = getDao().findByNamedQuery(null,"queryDicItemByType",
					new String[]{"type"},
					new Object[]{type},
					DicItem.class,"new");
			cache.put(type, list);
		}
		return list;
	}
	
	/**
	 * 根据字典类型获取字典项(Map形式), 用于根据值翻译成中文显示
	 * @param type 字典类型
	 * @return Map<字典项的值,字典项的名称>
	 */
	public Map<String,String> getItemsMap(String type){
		Assert.notNull(type, "字典类型不能为空");
		
		Map<String,String> map = valueNameMap.get(type);
		
		if(map==null){
			map = new HashMap<String,String>();
			
			List<DicItem> list = getItems(type);
			
			for (DicItem item : list) {
				map.put(item.getValue(), item.getName());
			}
			
			valueNameMap.put(type, map);
			
		}
		
		return map;
	}
	
	/**
	 * 根据字典类型获取字典项(Map形式), 用于根据值翻译成中文显示
	 * @param type 字典类型
	 * @return Map<字典项的名称,字典项的值>
	 */
	public Map<String,String> getItemsNameMap(String type){
		Assert.notNull(type, "字典类型不能为空");
		
		Map<String,String> map = this.nameValueMap.get(type);
		
		if(map==null){
			map = new HashMap<String,String>();
			
			List<DicItem> list = getItems(type);
			
			for (DicItem item : list) {
				map.put(item.getName(),item.getValue());
			}
			
			this.nameValueMap.put(type, map);
			
		}
		
		return map;
	}
	
	/**
	 * 根据字典类型和字典项名称获取字典项的值, 用于数据导入
	 * @param type 字典类型
	 * @return Map<字典项的值,字典项的名称>
	 */
	public String getValule(String type, String name){
		if (StrTool.isBlankStr(name) || StrTool.isBlankStr(type)){
			return null;
		}
		Assert.notNull(type, "字典类型不能为空");
		Assert.notNull(name, "字典项名称不能为空");
		
		Map<String,String> map = this.getItemsNameMap(type);
		
		if(map==null){
			return null;
		}
		
		return map.get(name);
		
	}
	
	/**
	 * 获取字典项
	 * @param type 字典类型
	 * @param value 字典项的值
	 * @return 找不到则返回NULL
	 */
	public DicItem getItem(String type, String value){
		if (StrTool.isBlankStr(value) || StrTool.isBlankStr(type)){
			return null;
		}
		Assert.notNull(type, "字典类型不能为空");
		Assert.notNull(value, "字典项值不能为空");
		
		List<DicItem> list = getItems(type);
		
		for(DicItem item : list){
			if(value.equals(item.getValue())){
				return item;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取字典项
	 * @param type 字典类型
	 * @param name 字典项的值
	 * @return 找不到则返回NULL
	 */
	public DicItem getItemByTypeAndName(String type, String name){
		if (StrTool.isBlankStr(name) || StrTool.isBlankStr(type)){
			return null;
		}
		Assert.notNull(type, "字典类型不能为空");
		Assert.notNull(name, "字典项名称不能为空");
		
		List<DicItem> list = getItems(type);
		
		for(DicItem item : list){
			if(name.equals(item.getName())){
				return item;
			}
		}
		
		return null;
	}
	
    @Resource
    public void setDao(DicItemDao dao) {
        super.setDao(dao);
    }
    
   
  
}
