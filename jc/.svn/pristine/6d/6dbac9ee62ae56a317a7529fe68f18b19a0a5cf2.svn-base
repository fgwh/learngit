package com.hgsoft.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hgsoft.security.dao.ModuleDao;
import com.hgsoft.security.entity.Module;
import com.hgsoft.util.Order;
import com.hgsoft.util.Property;

@Service
public class ModuleService extends BaseService<Module> {

	Order[] orders = new Order[] { Order.asc("level"), Order.asc("priority") };

	public List<Module> findBySubSystem() {
		return getDao().findAll(orders, Property.like("subsystem", "%KF%"));
	}

	public List<Module> findAll(){
		return getDao().findAll(orders);
	}

	public List<Module> findChildren(Integer id) {
		return getDao().findAll(orders, Property.eq("parent.id", id));
	}

	public List<Module> findByLevel(Integer id) {
		return getDao().findAll(orders, Property.gt("level", id));
	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	public List<Module> getMenus(HashSet<Module> set) {
		List list = new ArrayList();
		if (set != null && !set.isEmpty()) {
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Module module = (Module) it.next();
				list.add(module.getId());
			}
		}
		return getDao().findAll(orders, Property.eq("display", 1),
				Property.in("id", list));
	}

	public String getCurrentPosition(String id) {
		String position = "";
        // modify by user Bruce.Zhan 2014-07-14
        String sql = "WITH tree AS (\n" +
                     "\tSELECT\n" +
                     "\t\t*\n" +
                     "\tFROM\n" +
                     "\t\tsys_module t1\n" +
                     "\tWHERE\n" +
                     "\t\tt1.id = ?\n" +
                     "\tAND t1.display = 1\n" +
                     "\tUNION ALL\n" +
                     "\t\tSELECT\n" +
                     "\t\t\tt2.*\n" +
                     "\t\tFROM\n" +
                     "\t\t\tsys_module t2,\n" +
                     "\t\t\ttree\n" +
                     "\t\tWHERE\n" +
                     "\t\t\ttree.parent = t2.id\n" +
                     ") SELECT\n" +
                     "\ttree.name,\n" +
                     "\ttree. LEVEL\n" +
                     "FROM\n" +
                     "\ttree\n" +
                     "ORDER BY\n" +
                     "\ttree. LEVEL ASC";
        List<Object[]> list = this.getDao().queryBySQL(sql, id);
        for (int i = 0; i < list.size() && list.size() != 0; i++) {
            position +=  " > " + list.get(i)[0].toString();
        }
        /*Module module = getDao().find(id);
		if (module != null) {
			position = module.getName();
			while (module.getParent() != null) {
				module = module.getParent();
				if (module != null) {
					position = module.getName() + " > " + position;
				}
			}
		}*/
		return position.substring(2);
	}

    public void deleteModule(String id) {
        /*String hql = "delete from RoleModule where id.module=" + id;
        getDao().updateByHql(hql);*/
        deleteById(id);
        //String hql = "delete from RoleModule where id.module=" + id;
        //getDao().updateByHql(hql);
        String sql = "delete from sys_role_module where module = '"+id+"'";
        getDao().updateBySql(sql);
    }

	@Resource
	public void setDao(ModuleDao dao) {
		super.setDao(dao);
	}

    public List<Module> obtainModules(Property property) {
        List<Module> modules = getDao().findAll(property);
        for (Module module : modules) {
            module.setParent(null);
        }
        return modules;
    }

	public void updateBySql(String sql) {
		getDao().updateBySql(sql);
	}

	public Integer getIdByName(String moduleName) {
		List<?> list = getDao().findBySql("select id from sys_module where name='"+moduleName+"'", null);
		if(list.size()>0){
			return new Integer(list.get(0).toString());
		}
		return null;
	}
}
