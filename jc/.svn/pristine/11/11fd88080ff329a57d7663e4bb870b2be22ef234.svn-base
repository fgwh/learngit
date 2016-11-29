package com.hgsoft.affix.dao;

import com.hgsoft.affix.entity.AffixFile;
import com.hgsoft.security.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AffixFileDao extends BaseDao<AffixFile> {

	public Collection getFilesByBizId(String bizId) {
		String hql = " from AffixFile c where c.bizId='"+bizId+"'";
		return queryByHQL(hql, null);
	}

	public void deleteFileByRefId(String bizId) {
		String hql = " delete from AffixFile c where c.bizId='"+bizId+"'";
		updateByHql(hql);
	}

	public void deleteById(String fileId) {
		String hql = " delete from AffixFile c where c.id='"+fileId+"'";
		updateByHql(hql);
	}
	
}
