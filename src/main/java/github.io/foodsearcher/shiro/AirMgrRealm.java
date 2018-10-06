package top.yaoyongdou.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import top.yaoyongdou.service.UserInfoService;
import top.yaoyongdou.shiro.UserInfo;

import javax.annotation.Resource;

/**
 * Created by young on 18-3-11.
 */
public class AirMgrRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param token
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();

        /*
        *通过username从数据库中查找 User对象，如果找到，没找到.
        *实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        */
        UserInfo userInfo = userInfoService.findByUsername(username);
        if(userInfo == null){
            return null;
        }


        //账号判断
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userInfo, //用户名
//                userInfo.getPassword(), //密码
//                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUsername(),
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()
        );

        return authenticationInfo;
    }


    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * :Authorization 是授权访问控制
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();

        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }

        return authorizationInfo;
    }
}
