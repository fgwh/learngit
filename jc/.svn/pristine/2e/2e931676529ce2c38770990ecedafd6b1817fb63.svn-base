package com.hgsoft.main.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hgsoft.main.dao.GoodDao;
import com.hgsoft.main.entity.Good;
import com.hgsoft.security.service.BaseService;

@Service
@Transactional
public class GoodService extends BaseService<Good>{
	
	@Resource
    public void setDao(GoodDao dao) {
        super.setDao(dao);
    }

	@SuppressWarnings("unchecked")
	public String exist(String name) {
		List<Good> goods = (List<Good>) getDao().findByHql("from Good g where g.name='"+name+"'", null);
		if(goods.size()>0){
			return "yes";
		}
		return "no";
	}

	public Good findByName(String name) {
		@SuppressWarnings("unchecked")
		List<Good> goods = (List<Good>) getDao().findByHql("from Good g where g.name='"+name+"'", null);
		if(goods.size()>0){
			return goods.get(0);
		}
		return null;
	}

	public void deleteAllSub(Good good) {
		getDao().updateBySql("delete from tb_good where pid in (select id from tb_good where pid="+good.getId()+")");
		getDao().updateBySql("delete from tb_good where pid="+good.getId());
		getDao().delete(good);
		
	}
	/**
	 * 根据id数组批量删除
	 * @param ids
	 * @return
	 */
	public boolean deleteAllSubs(String ids){
		String[] idList = ids.split(",");
		for(int i=0;i<idList.length;i++){
		 Good good = getDao().find(idList[i]);
		 getDao().delete(good);
		}
		return true;
	}
}
