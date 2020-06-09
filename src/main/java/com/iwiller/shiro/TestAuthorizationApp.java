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
        //1.������ȫ�������Ĺ�������
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.ʹ�ù���������ȫ������
        DefaultSecurityManager securityManager = (DefaultSecurityManager) factory.getInstance();
        //3.����UserRealm
//        UserRealm realm = new UserRealm();
        //4.��securityManagerע��UserRealm
//        securityManager.setRealm(realm);
        //5.�ѵ�ǰ�İ�ȫ�������󶨵���ǰ���߳�
        SecurityUtils.setSecurityManager(securityManager);
        //6.ʹ��SecurityUtils.getSubject�õ��������
        Subject subject = SecurityUtils.getSubject();
        //7.��װ�û���������
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        //8.�õ���֤
        try {
            subject.login(token);
            System.out.println("��֤�ɹ�");
        }catch (AuthenticationException e){
            System.out.println("�û��������벻��ȷ");
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
