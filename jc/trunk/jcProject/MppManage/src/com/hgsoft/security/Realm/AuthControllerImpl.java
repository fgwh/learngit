package com.hgsoft.security.Realm;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import com.hgsoft.security.entity.Module;
import com.hgsoft.security.entity.Role;
import com.hgsoft.security.service.ModuleService;
import com.hgsoft.security.service.RoleService;
import com.hgsoft.util.Property;
import com.hgsoft.util.SpringInit;

/**
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: AuthControllerImpl.java
 * @Date: 14-6-4
 * @Time: 下午1:01
 */
public class AuthControllerImpl implements IAuthController/*, FactoryBean<Ini.Section>*/ {
    private String filterChainDefinitions;
    public static final String ROLE_OR_STRING="roleOrFilter[\"{0}\"]";
    private final Log logger = LogFactory.getLog(AuthControllerImpl.class);

    @Resource
    private ModuleService moduleService;
    @Resource
    private RoleService roleService;

    @Override
    public synchronized String loadFilterChainDefinitions() {
        logger.info("权限加载");
        Ini.Section section = loadFilterChain();
        StringBuffer stringBuffer = new StringBuffer("");
        for (Map.Entry<String, String> entry : section.entrySet()) {
            stringBuffer.append(entry.getKey() + " = " + entry.getValue() + "\r\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public synchronized void reCreateFilterChains() {
        logger.info("权限重载");
        //ShiroFilterFactoryBean shiroFilterFactoryBean = (ShiroFilterFactoryBean) SpringInit.getApplicationContext().getBean("shiroFilter");
        AbstractShiroFilter shiroFilter = null;
        try{
			shiroFilter = (AbstractShiroFilter) SpringInit
					.getApplicationContext().getBean("shiroFilter");
            //shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
        } catch(Exception e) {
            //log.error("getShiroFilter from shiroFilterFactoryBean error!", e);
            //throw newRuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
        }

		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
				.getFilterChainManager();

        //清空老的权限控制
        manager.getFilterChains().clear();
        Ini.Section section = loadFilterChain();
        Map<String, String> filterListMap = section;
        for (Map.Entry<String, String> entry : filterListMap.entrySet()) {
            manager.createChain(entry.getKey(), entry.getValue());
        }
        /*for (String ) {

        }*/
        //manager.
        //Map<String, NamedFilterList> filterListMap = new HashMap<String, NamedFilterList>();
        //NamedFilterList namedFilterList = new
        //manager.setFilterChains((Map<String, NamedFilterList>) section);
       /* shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
        //重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        for(Map.Entry<String, String> entry :chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition =entry.getValue().trim().replace(" ", "");
            manager.createChain(url,chainDefinition);
        }*/

    }

    private Ini.Section loadFilterChain() {
        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //section.put(module.getUrl() + "*", MessageFormat.format(ROLE_OR_STRING, roleString));
        List<Module> modules = moduleService.obtainModules(Property.ne("url", ""));
        for (Module module : modules) {
            List<Role> roles = roleService.obtainRoles(module.getId());
            String roleString = "";
            for (Role role : roles) {
                roleString += role.getName() + ",";
            }
            if (StringUtils.isNotEmpty(roleString)) {
                roleString = roleString.substring(0, roleString.lastIndexOf(","));
                section.put(module.getUrl() + "*", MessageFormat.format(ROLE_OR_STRING, roleString));
            } else {
                section.put(module.getUrl() + "*", MessageFormat.format(ROLE_OR_STRING, "SUPER"));
            }
        }
        section.put("/**", "roleOrFilter");
        return section;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
}
