package com.hgsoft.security.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hgsoft.security.entity.Org;
import com.hgsoft.security.hibex.AliasToBeanAutoCastResultTransformer;
import com.hgsoft.security.util.OrgUtils;
import com.hgsoft.util.Order;
import com.hgsoft.util.Pager;
import com.hgsoft.util.Property;
import com.hgsoft.util.StrTool;

@SuppressWarnings("unchecked")
public class BaseDao<T extends Serializable> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private SessionFactory sessionFactory;

	public T newEntityInstance() {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			//e.printStackTrace();
			return null;
		}
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];
	
	// Map<字段名,字段操作>, 譬如"id,=","startTime,>=","endTime,<="
	protected Map<String,String> queryConfig = new HashMap<String,String>();
	
	protected BaseDao(){
		setQueryConfig();
	}

	protected void setQueryConfig(){
		queryConfig.put("id", "=");
		queryConfig.put("startTime", ">=");
		queryConfig.put("endTime", "<=");
	}
	public void save(T entity) {
		getSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(Object entity) {
		getSession().merge(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	public T find(Serializable id) {
		T entity = (T) getSession().get(clazz, id);
		return entity;
	}

	public List<?> findBySql(String sql, Pager pager) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}
	
	public String addSearchCondition(String seachId){
		String hql ="(''";
		List<Org> hasRightOrgList = OrgUtils.getAllSubOrgByParentOrgId(seachId);
		if (hasRightOrgList!=null && !hasRightOrgList.isEmpty()){
			for (Org orgTemp : hasRightOrgList) {
				hql += ",'"+orgTemp.getId()+"'";
			}
		}
		hql += " )"; 
		return hql;
	}
	
	public List<?> findBySql(String sql, Pager pager,Class clazz) {
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(new AliasToBeanAutoCastResultTransformer(clazz));
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}
	public List<?> findByHql(String hql, Pager pager) {
		Query query = getSession().createQuery(hql);
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

    public List<T> findAll(Property... propertys) {
        return findByPager(null, new Order[] {}, propertys);
    }

	public List<T> findAll(Order order, Property... propertys) {
		return findByPager(null, new Order[] { order }, propertys);
	}

	public List<T> findAll(Order[] orders, Property... propertys) {
		return findByPager(null, orders, propertys);
	}

	public List<T> findByPager(Pager pager, Order order, Property... propertys) {
		return findByPager(pager, new Order[] { order }, propertys);
	}

	@SuppressWarnings("rawtypes")
	public List<T> findByPager(Pager pager, Order[] orders,
			Property... propertys) {
		Criteria criteria = getSession().createCriteria(clazz);
		// 查询条件
		for (Property property : propertys) {
			if (property != null)
				criteria.add(property.getCriterion());
		}
		// 分页
		if (pager != null) {
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();

			if (count == null)
				return new ArrayList();
			pager.setTotalSize(count);
			criteria.setProjection(null);

			criteria.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			criteria.setMaxResults(pager.getPageSize());

		}
		// 排序
		if (orders != null && orders.length > 0) {
			for (Order order : orders) {
				if (order != null)
					criteria.addOrder(order.getHibernateOrder());
			}
		}
		List list = criteria.list();
		List list2 = new LinkedList();
		if (list != null && !list.isEmpty()) {
			Object[] objects = null;
			try {
				objects = (Object[]) list.get(0);
			} catch (Exception e) {
				return list;
			}
			int i = objects.length - 1;
			if (i > 0) {
				for (Object object : list) {
					Object[] objects2 = (Object[]) object;
					list2.add(objects2[i]);
				}
			}

		}

		return list2;
	}

	public Long countByProperty(String propertyName, Property... propertys) {
		Criteria criteria = getSession().createCriteria(clazz);
		// 查询条件
		for (Property property : propertys) {
			if (property != null)
				criteria.add(property.getCriterion());
		}
		criteria.setProjection(Projections.count(propertyName));
		return (Long) criteria.uniqueResult();
	}

	public Long count(String hql, Object... param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				if(param[i] instanceof Map){
					Map<String ,Object> m = (Map<String ,Object>)param[i];
					for(String key:m.keySet()){
						q.setParameterList(key, (Object[])m.get(key));
					}
				}else{
					q.setParameter(i, param[i]);
				}
			}
		}
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, List<Object> param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				if(param.get(i) instanceof Map){
					Map<String ,Object> m = (Map<String ,Object>)param.get(i);
					for(String key:m.keySet()){
						q.setParameterList(key, (Object[])m.get(key));
					}
				}else{
					q.setParameter(i, param.get(i));
				}
			}
		}
		return (Long) q.uniqueResult();
	}
	@SuppressWarnings("rawtypes")
	protected List executeQuery(String hql, Object... values) {
		return executeQuery(null, hql, values);
	}

	@SuppressWarnings("rawtypes")
	public List queryByHQL(String hql, Object... values) {
		return executeQuery(hql, values);
	}

	@SuppressWarnings("rawtypes")
	public List queryByHQL(Pager pager, String hql, Object... values) {
		long count = executeCount(hql, values);
		pager.setTotalSize(count);
		return executeQuery(pager, hql, values);
	}

	@SuppressWarnings("rawtypes")
	public List queryBySQL(String sql, Object... values) {
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();
	}
	@SuppressWarnings("rawtypes")
	public List queryBySQL(String sql, Class classes, Object... values) {

		Query query = getSession().createSQLQuery(sql).addEntity(classes);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	protected List executeQuery(Pager pager, String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

	public Long executeCount(String hql, Object... values) {
		Query query = getSession().createQuery("select count(*) " + hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return (Long) query.list().get(0);
	}
	public List<T> query(Pager pager, T entity) {
		String hql = " from "+ clazz.getSimpleName() +" c" + " where 1=1" + getConditions(entity);
		if(pager!=null){
			pager.setTotalSize(count("select count(id) " + hql));
		}
		
		hql += setOrders(entity);
		return (List<T>) findByHql(hql, pager);
	}

	public String setOrders(T entity){
		return "";
	}
	/**
	 * 根据赋给对象的属性值进行查询
	 * 
	 * @param pager
	 * @param object
	 *            要查询的对象
	 * @param dateMap
	 *            不需要查询时间时该属性可设置为空
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(Pager pager, Object object, Map dateMap) {
		StringBuffer sb = new StringBuffer();
		StringBuffer name = new StringBuffer("obj");
		String hql = getQueryString(object, name, sb, 0, dateMap);
		if (hql == null)
			// hql="from "+className.substring(className.lastIndexOf(".")+1);
			hql = "from " + clazz.getName();
		long count = executeCount(hql);
		pager.setTotalSize(count);
		hql = hql.concat(" order by id desc");
		// System.out.println(hql);
		return executeQuery(pager, hql);
	}

	/***
	 *
	 * @author linnt
	 * @date 2015年1月6日
	 * @param pager
	 * @param object
	 * @param dateMap
	 * @param orderProperies
	 * @return
	 */
	public List query(Pager pager, Object object, Map dateMap, String orderName) {
		StringBuffer sb = new StringBuffer();
		StringBuffer name = new StringBuffer("obj");
		String hql = getQueryString(object, name, sb, 0, dateMap);
		if (hql == null)
			// hql="from "+className.substring(className.lastIndexOf(".")+1);
			hql = "from " + clazz.getName() +" obj ";
		long count = executeCount(hql);
		pager.setTotalSize(count);
		if (StrTool.isBlankStr(orderName)){
			orderName = "id";
		}
		hql = hql.concat(" order by obj."+orderName+" desc");
		// System.out.println(hql);
		return executeQuery(pager, hql);
	}
	/**
	 * 当需要对某一时间属性查询时，只要设置一个不为空任意值
	 * 
	 * @param obj
	 *            需要进行查询的对像
	 * @param name
	 *            类名字符串如（student.card.) 给hql的第一层的类任取自己名字，如类Student则将可取为student
	 * @param sb
	 *            传入一个空的StringBuffer
	 * @param control
	 *            控制符,设置为零传入
	 * @param dateMap
	 *            按时间属性名，传入时间范围值 如 birthDay 则
	 *            dateMap.put("birthDay1",date1);dateMap.put("birthDay2",date2);
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getQueryString(Object obj, StringBuffer name,
			StringBuffer sb, int control, Map dateMap) {
		if (obj == null)
			return null;
		List list = new ArrayList();
		list.add("java.lang.Integer");
		list.add("java.lang.Object");
		list.add("java.lang.String");
		list.add("java.lang.Float");
		list.add("java.lang.Double");
		list.add("java.lang.Byte");
		list.add("java.lang.Char");
		list.add("java.lang.Long");
		list.add("java.lang.Boolean");
		list.add("java.lang.Short");
		list.add("java.util.Date");
		list.add("java.sql.Date");
		String str = null;
		Class c = obj.getClass();
		if (control == 0) {// 如果control等于0则执行该属性
			sb.append("from " + c.getName() + " " + name.toString() + " where");
			str = sb.toString();
		}
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			try {
				Object val = f.get(obj);
				if (val != null && !"".equals(val) && f.getGenericType().toString().length()>7 && !"java.util.Date".equals(f.getGenericType()
						.toString().substring(6))) {// 如果值为空则跳过
					if (list.contains(val.getClass().getName())) {// 基本属性则依次加入
						//(除dDate类型外)判断下是否用like，而不是=						
						if (dateMap!=null && dateMap.get(f.getName())!=null ){
							sb.append(" " + name.toString() + "." + f.getName()
									+dateMap.get(f.getName())+ " '%" + val + "%' and");
						} else {
							sb.append(" " + name.toString() + "." + f.getName()
									+ "='" + val + "' and");
						}
					} else {// 如果是对象，则递归调用
						name.append("." + f.getName());
						getQueryString(val, name, sb, 1, dateMap);
						name.replace(
								name.length() - (f.getName().length() + 1),
								name.length(), "");
					}
				} else if (f.getGenericType().toString().length()>6 && ("java.util.Date".equals(f.getGenericType()
						.toString().substring(6))
						|| "java.sql.Date".equals(f.getGenericType().toString()
						.substring(6)))) {// 如果值为空，则判断是否是时间对象
					if (dateMap != null) {
						DateFormat sf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date lDate = (Date) dateMap.get(f.getName() + "1");
						Date hDate = (Date) dateMap.get(f.getName() + "2");
						if (lDate != null) {
							sb.append(" " + name.toString() + "." + f.getName()
									+ ">='" + sf.format(lDate) + "' and");
						}
						if (hDate != null) {
							sb.append(" " + name.toString() + "." + f.getName()
									+ "<='" + sf.format(hDate) + "' and");
						}
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (control == 0) {// 判断是最外层的query函数时，对最终的字符串进行判断处理
			if (sb.toString().equals(str))
				return sb.replace(sb.length() - 6, sb.length(), "").toString();
			sb.replace(sb.length() - 4, sb.length(), "");

		}
		return sb.toString();
	}

	public List<T> query(Pager pager, String tableName, String[] projection,
			String[] selection, String[] selectionArg, String order) {
		String OBJ = "obj.";
		StringBuffer hql = new StringBuffer();

		if (projection != null) {
			hql.append("select ");
			for (int i = 0; i < projection.length; i++) {
				hql.append(OBJ + projection[i] + ",");
			}
			hql.replace(hql.length() - 1, hql.length(), " ");
		}

		hql.append("from " + tableName + " obj");

		if (selection != null) {
			hql.append(" where (1=1 ");
			for (int j = 0; j < selection.length; j++) {
				if (!selectionArg[j].equals("") && selectionArg[j] != null) {
					hql.append("AND " + OBJ + selection[j] + " = '"
							+ selectionArg[j] + "' ");
				}
			}
			hql.append(") ");
		}
		long count = executeCount(hql.toString());
		pager.setTotalSize(count);
		if (order != null) {
			hql.append("order by " + OBJ + order);
		}
		return executeQuery(pager, hql.toString());
	}

	protected T queryFirst(String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setFirstResult(0);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}
	
	public int executeCountQuery(String hql){
		String hqlCount = "select count(*) "+hql;
		System.out.println(hqlCount);
		return Integer.parseInt(getSession().createQuery(hqlCount).uniqueResult().toString());
	}
	
	public Object uniqueResult(String hql){
		return getSession().createQuery(hql).uniqueResult();
	}
	
	public void updateByHql(String hql){
		getSession().createQuery(hql).executeUpdate();
	}
	//wangsu
	public void updateBySql(String sql){
		getSession().createSQLQuery(sql).executeUpdate();
	}
	//wangsu 2014-12-30
	public int executeCountSqlQuery(String sql){
		String sqlCount = "select count(*)"+sql;
		System.out.println(sqlCount);
		return Integer.parseInt(getSession().createSQLQuery(sqlCount).uniqueResult().toString());
	}
	
	public List findByNamedQuery(Pager pager,String queryName, String[] params, Object[] values){
		Query query = findQuery(queryName);
		setQueryParameter(query, params, values);
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

	/**
	 * 重载根据service层拼接sql语句查询结果，放弃通过hibernate的配置文件加载sql语句的方式
	 * @param pager
	 * @param queryName
	 * @param params
	 * @param values
	 * @param clazz
	 * @param type
	 * @return
	 */
	public List findByNamedQuery(Pager pager,String queryName,Class clazz){

		Query query = getSession().createSQLQuery(queryName);

		long b = queryTotalCount(queryName);
		
		query.setResultTransformer(new AliasToBeanAutoCastResultTransformer(clazz));

		if (pager != null) {
			//pager.setTotalSize(query.list().size()); 
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			
			pager.setTotalSize(b);
			query.setMaxResults(pager.getPageSize());
			
		}
		return query.list();
	}
	/**
	 * 重载获取查询记录的总数
	 * @param sql
	 * @param params
	 * @param values
	 * @param type
	 * @return
	 */
	public long queryTotalCount(String sql){
		StringBuffer sb = new StringBuffer(sql);
		sb.replace(sb.lastIndexOf("order"), sb.length(), "");
		sql = sb.toString();
		sql = "select count(*) from ("+sql+")AP";
		System.out.println("查询countSQL："+sql);
		long a = Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
		
		return a;
	}
	/*
		通过类名获取hql 查询列表分页显示
	*/
	public List findByNamedQuery(Pager pager,String queryName, String[] params, Object[] values, Class clazz,String version){
		if (clazz == Map.class){
			return findByNamedQueryForMap(pager,queryName, params, values);
		}
		Query query = findQuery(queryName);		
		setQueryParameter(query, params, values);
		if("new".equals(version)){
			   query.setResultTransformer(new AliasToBeanAutoCastResultTransformer(clazz));
			}
		if (pager != null) {
			//pager.setTotalSize(query.list().size()); 
			StringBuffer stringBf = new StringBuffer(query.getQueryString());
			stringBf.replace(stringBf.lastIndexOf("order"), stringBf.length(), "");
			long b = queryTotalCount(stringBf.toString(), params, values);			
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			
			pager.setTotalSize(b);
			query.setMaxResults(pager.getPageSize());
			
		}
		return query.list();
	}
	
	/*
	通过类名获取hql 查询列表 去count 分页	
	 */
	public List findByNamedQuery2(Pager pager,String queryName, String[] params, Object[] values, Class clazz,String version){
		if (clazz == Map.class){
			return findByNamedQueryForMap(pager,queryName, params, values);
		}
		Query query = findQuery(queryName);
		StringBuffer stringBf = new StringBuffer(query.getQueryString());
		
		if("new".equals(version)){
		   query.setResultTransformer(new AliasToBeanAutoCastResultTransformer(clazz));
		}
		setQueryParameter(query, params, values);
		
		return query.list();
	}
	public long queryTotalCount(String sql,String[] params, Object[] values){
		for (int i = 0; i < params.length; i++) { 
			if(!"".equals(values[i].toString())){//参数不为空
				//sql = sql.replace("''=:"+params[i]+" or", ""); 前后SQL保持一致
				sql = sql.replaceAll(":"+params[i], "'"+values[i].toString()+"'");
			}else{//参数为空
				sql = sql.replaceAll(":"+params[i], "''");
			}  
		}
		sql = "select count(*) from ("+sql+")AP";
		System.out.println("查询countSQL："+sql);
		long a = Integer.parseInt(getSession().createSQLQuery(sql).uniqueResult().toString());
		
		return a;
	}
	
	public List findMapListByNamedQuery(String queryName, String[] params, Object[] values){
		Query query = findQuery(queryName);
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		setQueryParameter(query, params, values);
		return query.list();
	}

	public void setQueryParameter(Query query, String[] params, Object[] values) throws HibernateException{
		setQueryParameter(query, params, values, null);
	}
	/**
	 * 通过queryName在hibernate的配置文件获得查询语句创建hibernate的检索对象
	 * 首先查找带特定数据库后缀的namedQuery，没有带后缀的才用参数值提供的namedQuery
	 * @param queryName hibernate的配置文件中查询语句对应的名字
	 * @return Query 返回Query对象
	 */
	protected Query findQuery(String queryName) {
		try{
			String extQueryName = queryName;
			SessionFactoryImplementor sfi = (SessionFactoryImplementor)getSession().getSessionFactory();
			if(sfi.getNamedQuery(extQueryName) != null) {
				return getSession().getNamedQuery(extQueryName);
			}else if(sfi.getNamedSQLQuery(extQueryName) != null) {
				return getSession().getNamedQuery(extQueryName);
			}
			return getSession().getNamedQuery(queryName);
		}
		catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
	public void setQueryParameter(Query query, String[] params, Object[] values, Class[] types) throws HibernateException{
		String[] nps = query.getNamedParameters();
		if(nps != null && params != null) {
			List namedParams = Arrays.asList(nps);
			for (int i = 0, size = params.length; i < size; i++) {
				if(!namedParams.contains(params[i])) {
					//log.warn("传入了query中没有定义的参数，该参数被忽略：" + params[i]);
					continue;
				}
				if(values[i] instanceof Collection) {
					Collection coll = (Collection)values[i];
					if(coll.isEmpty()) {
						query.setParameter(params[i], null);
					}else {
						query.setParameterList(params[i], coll);
					}
				}else if(values[i] instanceof Object[]) {
					Object[] objs = (Object[])values[i];
					if(objs.length == 0) {
						objs = new Object[]{null};
					}
					query.setParameterList(params[i], objs);
				}else {
					query.setParameter(params[i], values[i]);
				}
			}
		} 
	}

	@SuppressWarnings("rawtypes")
	public List queryBySQLForMap(String sql) {
		Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	public List findByNamedQueryForMap(Pager pager,String queryName, String[] params, Object[] values){
		Query query = findQuery(queryName);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setQueryParameter(query, params, values);
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}
	
	/*
	 * 基类拼接查询条件
	 */
	protected String getConditions(T entity){
		String hql="";
		if(entity==null){
			return hql;
		}
		try {
			// TODO kuangyc 拼HQL,有不少地方需要调整,而且应该参数化,以后有机会再弄
			// 如果要支持日期,最好改成参数形式,先format成字符串也可以
			for(Field field : entity.getClass().getDeclaredFields()){

				field.setAccessible(true);
				String name = field.getName();
				Object value = field.get(entity);
				field.setAccessible(false);

				if("serialVersionUID".equals(name)) continue;
				if(value==null)continue;
				if(value instanceof Boolean) {
					continue;
				}
				boolean isStr = value instanceof String;
				boolean isDate = value instanceof Date;
				if(isDate){
					isStr=true;
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)value);
				}
				if(queryConfig!=null && queryConfig.containsKey(name)){
					if(isStr){
						if(StringUtils.isNotBlank((String) value)){
							if("in".equals(queryConfig.get(name))){
								hql += " and c."+name+" in ('"+((String)value).replace(",", "','")+"')";
							}else{
								//开始工班、结束工班
								if("startTime".equals(name) || "endTime".equals(name)) {
									hql += " and c.squadDate " + queryConfig.get(name) + " '" + (String)value + "'";
								}else {
									hql += " and c."+name+" "+ queryConfig.get(name) + " '"+(String)value+"'";									
								}
							}
						}
					}else{ // 无单引号
						hql += " and c."+ name + " "+ queryConfig.get(name) + " " +value;
					}
				}else if(isStr){
					if(isDate){
						hql += " and c." + name + "='" + value+"'";
					}else if(StringUtils.isNotBlank((String) value)){// 字符串默认是模糊搜索
						hql += " and c."+name+" like '%"+(String)value+"%'";
					}
				}else{
					// 其他类型默认是等于
					hql += " and c." + name + "=" + value+"";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return hql;
	}

	/*
	 * 批量删除
	 */
	public void batchDelete(String ... ids) {
		if(ids==null || ids.length<1){
			return;
		}
	    
	    StringBuffer sql = new StringBuffer();
		if(ids.length > 0) {
			sql.append("('").append(ids[0]).append("'");
		}
		for(int i = 1; i < ids.length; i++) {
			sql.append(",'").append(ids[i]).append("'");
		}
		if(sql.length() > 0) {
			sql.append(")");
			//Query q= this.getSession().createSQLQuery("delete from tb_" + clazz.getSimpleName() + " where id in " + sql.toString());
			Query q= this.getSession().createQuery("delete from " + clazz.getSimpleName() + " t where t.id in " + sql.toString());
	        q.executeUpdate();
	        this.getSession().flush();
		}
	}
	
	//查询excel数量
	public long queryExcelCount(String queryName, String[] params, Object[] values) {
		Query query = findQuery(queryName);
		StringBuffer stringBf = new StringBuffer(query.getQueryString());
		stringBf.replace(stringBf.lastIndexOf("order"), stringBf.length(), "");
		long b = queryTotalCount(stringBf.toString(), params, values);
		return b;
	}
	
}
