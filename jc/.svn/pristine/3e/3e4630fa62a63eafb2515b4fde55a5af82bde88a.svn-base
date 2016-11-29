package com.hgsoft.main.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.hgsoft.util.Pager;

/**
 * @author huang_cx
 * @version 1.0
 * @Description 用于任务调度的数据库操作
 */
@Repository("jobDao")
public class JobDao {
	@Resource
	private SessionFactory sessionFactory; // 在BaseDao注入sessionFactory

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
	 * 获取Session对象
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected List<?> executeQuery(String hql, Object... values) {
		return executeQuery(null, hql, values);
	}

	protected List<?> executeQuery(Pager pager, String hql, Object... values) {
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

	protected List<?> executeSqlQuery(Pager pager, String sql, Class<?> clazz,
			Object... values) {
		Query query = getSession().createSQLQuery(sql);

		if (clazz != null)
			((SQLQuery) query).addEntity(clazz);

		if (values != null && values.length > 0) {
			int len = values.length;
			for (int i = 0; i < len; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

	/**
	 * 根据原生Sql查询(分页)
	 * 
	 * @param pager
	 *            分页公共类
	 * @param sql
	 *            查询Sql串
	 * @param values
	 *            查询参数
	 * @param clazz
	 *            实体的class对象
	 * @return 返回查询结果
	 */
	public Object findWithSql(Pager pager, String sql, Class<?> clazz,
			Object... values) {
		return executeSqlQuery(pager, sql, clazz, values);
	}

	/**
	 * 根据原生Sql查询(不分页)
	 * 
	 * @param sql
	 *            查询Sql串
	 * @param values
	 *            查询参数
	 * @param clazz
	 *            实体的class对象
	 * @return 返回查询结果
	 */
	public Object findWithSql(String sql, Class<?> clazz, Object... values) {
		return executeSqlQuery(null, sql, clazz, values);
	}

	/**
	 * 根据原生Sql查询(不分页,不带查询参数)
	 * 
	 * @param sql
	 *            查询Sql串
	 * @return 查询集合对象
	 */
	public Object findWithSql(String sql) {
		return executeSqlQuery(null, sql, null);
	}

	/**
	 * 根据原生Sql查询(不分页,不带查询参数)
	 * 
	 * @param sql
	 *            查询Sql串
	 * @param clazz
	 *            实体的Class对象
	 * @return 查询集合对象
	 */
	public Object findWithSql(String sql, Class<?> clazz) {
		return executeSqlQuery(null, sql, clazz, null);
	}

	/**
	 * 根据Hql查询(分页)
	 * 
	 * @param pager
	 *            分页公共类
	 * @param hql
	 *            查询hql串
	 * @param values
	 *            查询参数
	 * @return 返回查询结果
	 */
	public Object findWithHql(Pager pager, String hql, Object... values) {
		return executeQuery(pager, hql, values);
	}

	/**
	 * 根据Hql查询(不分页)
	 * 
	 * @param hql
	 *            查询hql串
	 * @param values
	 *            查询参数
	 * @return 返回查询结果
	 */
	public Object findWitHql(String hql, Object... values) {
		return executeQuery(null, hql, values);
	}

	/**
	 * 根据Hql查询(不分页,不带查询参数)
	 * 
	 * @param hql
	 *            查询hql串
	 * @return 查询集合对象
	 */
	public Object findWitHql(String hql) {
		return executeQuery(null, hql, null);
	}

	/**
	 * 通过Sql执行更新/删除操作
	 * 
	 * @param sql
	 *            待处理语句串
	 * @return 影响行数
	 */
	public int updOrDelWithSql(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 通过hql执行更新/删除操作
	 * 
	 * @param hql
	 *            待处理语句串
	 * @return 影响行数
	 */
	public int updOrDelWithHql(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 保存实体
	 * @param entity 游离对象
	 * @return
	 */
	public Object save(Object entity) {
		return getSession().save(entity);
	}

	/**
	 * 更新实体
	 * @param entity 游离对象
	 */
	public void update(Object entity) {
		getSession().update(entity);
	}

	/**
	 * 保存或更新实体
	 * @param entity 游离对象
	 */
	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 合并对象
	 * @param entity 游离对象
	 * @return
	 */
	public Object merge(Object entity) {
		getSession().merge(entity);
		return entity;
	}
	
	/**
	 * 删除对象
	 * @param entity 游离对象
	 */
	public void delete(Object entity) {
        getSession().delete(entity);
    }

}
