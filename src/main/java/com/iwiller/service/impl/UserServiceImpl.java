package com.iwiller.service.impl;

import com.iwiller.domain.User;
import com.iwiller.service.IUserService;

import java.util.Date;

public class UserServiceImpl implements IUserService {


    public User queryUserByUsername(String username) {
        User user = null;
        if (username.equals("zhangsan")){
            user=new User(1, "zhangsan", "123456", new Date());
        }else if (username.equals("lisi")){
            user=new User(2, "lisi", "123456", new Date());
        }else if (username.equals("wangwu")){
            user=new User(3, "wangwu", "123456", new Date());
        }

        return user;
    }
}
