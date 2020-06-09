package com.iwiller.service.impl;

import com.iwiller.service.IRoleSerivce;

import java.util.Arrays;
import java.util.List;

public class RoleServiceImpl implements IRoleSerivce {


    public List<String> queryRoleByUsername(String username) {
        return Arrays.asList("role1","role2","role3");
    }
}
