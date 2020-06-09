package com.iwiller.realm;

import com.iwiller.domain.ActiverUser;
import com.iwiller.domain.User;
import com.iwiller.service.IPermissionService;
import com.iwiller.service.IRoleSerivce;
import com.iwiller.service.IUserService;
import com.iwiller.service.impl.PermissionServiceImpl;
import com.iwiller.service.impl.RoleServiceImpl;
import com.iwiller.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    private IUserService userService = new UserServiceImpl();

    private IRoleSerivce roleSerivce = new RoleServiceImpl();

    private IPermissionService permissionService = new PermissionServiceImpl();

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        token.getCredentials();
        System.out.println(username);
        /**
         * 以前登录的逻辑是 把用户和密码全部发到数据库 去匹配
         * 在shiro里面是根据用户名把用户对象查询出来，再做密码匹配
         */
        User user = userService.queryUserByUsername(username);
        if(null!=user){
            List<String> roles = roleSerivce.queryRoleByUsername(username);
            List<String> permission = permissionService.queryPermissionByUsername(username);

            ActiverUser activerUser = new ActiverUser(user, roles, permission);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), this.getName());
            return info;
        }else{
            //用户不存在 shiro会抛UnknowAccountException
            return null;

        }

    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("UserRealm.doGetAuthorizationInfo");
        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        Collection<String> roles=activerUser.getRoles();
        if(null!=roles&&roles.size()>0) {
            info.addRoles(roles);
        }

        //添加权限
        Collection<String> permissions=activerUser.getPermissions();
        if(null!=permissions&&permissions.size()>0) {
            info.addStringPermissions(permissions);
        }
//		if(activerUser.getUser().getType()==0) {
//			info.addStringPermission("*:*");
//		}
        return info;
    }
}
