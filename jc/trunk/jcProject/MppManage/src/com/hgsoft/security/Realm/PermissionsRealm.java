package com.hgsoft.security.Realm;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hgsoft.security.entity.Admin;
import com.hgsoft.security.entity.Module;
import com.hgsoft.security.entity.Role;
import com.hgsoft.security.service.AdminService;


/** 
  * @ClassName: PermissionsRealm
  * @Description: 实际执行认证的类
  * @author WangMing wang1988ming@qq.com
  * @date 2011-10-5 上午10:58:26 
  *  
  */

public class PermissionsRealm extends AuthorizingRealm {
	
    private final Log logger = LogFactory.getLog(PermissionsRealm.class);
    public static String authorizationCacheName = "authorizationCache";
    @Resource
    private AdminService adminService;

    /*@Resource
        private RoleAuthService roleAuthService;
        Users user;
        Set<Role> userRoles;
        Set<Resources> resources;
        @Autowired
        private IUserService userService;*/
	public PermissionsRealm(){
		setName("PermissionsRealm");
	}

	/* (non-Javadoc)
	 * <p>Title: doGetAuthorizationInfo</p> 
	 * <p>Description: 授权</p> 
	 * @param principals
	 * @return 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String  func = "";//用于存放权限点字符串
		Set<Role> roles;//用于存放用户角色
        logger.info(principals.getRealmNames() + "授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Admin admin = (Admin) SecurityUtils.getSubject().getSession()
				.getAttribute("operator");
        roles = admin.getRoles();
        for (Role role : roles) {
            info.addRole(role.getName());
            for(Module mo : role.getModules()){//存放权限点
				if (mo.getFunctions() != "" && mo.getFunctions() != null && mo.getFunctions().length() >0 ) {
					func = mo.getFunctions();
					func = func.replace("；", ";");
					for(String permission : func.split(";")){
						info.addStringPermission(permission);
					}
					
				}
            }
        }
        /*info.addRole("admin");
        //info.addRole("管理员");
        info.addRole("TEST");*/
        //info.addStringPermission("管理员");
		/*Set<Groups> userGroups = user.getGroups();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(user!=null){
			for(Groups group: userGroups){
				userRoles = group.getRoles();
				for(Role role : userRoles){
					info.addRole(role.getName());
					resources = role.getResources();
					for(Resources resource : resources){
						RoleAuth roleAuth = this.roleAuthService.find("from RoleAuth where roleId ="+role.getId()+" and resourceId ="+resource.getId());
						if(roleAuth!=null && roleAuth.getActions()!=null &&!"".equals(roleAuth.getActions())){
							String[] actionString = roleAuth.getActions().split(",");
							for(String action : actionString){
								//shiro权限字符串为：“当前资源英文名称:操作名英文名称”
								info.addStringPermission(action);
							}
						}
						
						//info.addStringPermission(roleAuth.getActions());
					}
				}
			}
			return info;
		}*/
		return info;
	}


	/* (non-Javadoc)
	 * <p>Title: doGetAuthenticationInfo</p> 
	 * <p>Description: 认证</p> 
	 * @param authtoken
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authtoken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authtoken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        logger.info("用户"+username+"尝试登陆");
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)) {
            throw new AuthenticationException("用户名或密码为空，请重新输入");
        }

        // 设置加密后的密码
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin = adminService.check(admin);

        if (admin != null) {
            if (!"1".equals(admin.getValid())) {
                throw new AuthenticationException("您已被禁止登录，请联系管理员");
            }
            
            SecurityUtils.getSubject().getSession()
                    .setAttribute("operator", admin);
            SecurityUtils.getSubject().getSession()
                    .setAttribute("functions",
                            adminService.getFunctions(admin));
			return new SimpleAuthenticationInfo(token.getPrincipal(),
					token.getCredentials(), token.getUsername());
		}
        throw new AuthenticationException("用户名或密码错误");
	}

}
