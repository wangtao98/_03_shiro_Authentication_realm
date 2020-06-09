package com.iwiller.shiro;

import com.iwiller.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class TestAuthorizationApp {
    private static final transient Logger log = LoggerFactory.getLogger(TestAuthorizationApp.class);


    private static void shiro1(){
        String username = "zhangsan";
        String password = "123456";

        log.info("My First Apache Shiro Application");
        //1.创建安全管理器的工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.使用工厂创建安全管理器
        DefaultSecurityManager securityManager = (DefaultSecurityManager) factory.getInstance();
        //3.创建UserRealm
//        UserRealm realm = new UserRealm();
        //4.给securityManager注入UserRealm
//        securityManager.setRealm(realm);
        //5.把当前的安全管理器绑定到当前的线程
        SecurityUtils.setSecurityManager(securityManager);
        //6.使用SecurityUtils.getSubject得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //7.封装用户名和密码
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //8.得到认证
        try {
            subject.login(token);
            System.out.println("认证成功");
        }catch (AuthenticationException e){
            System.out.println("用户名或密码不正确");
        }

        boolean role1 = subject.hasRole("role1");
        System.out.println(role1);

        boolean permitted = subject.isPermitted("user:query");
        System.out.println(permitted);

    }

    public static void main(String[] args) {
        shiro1();
    }
}
