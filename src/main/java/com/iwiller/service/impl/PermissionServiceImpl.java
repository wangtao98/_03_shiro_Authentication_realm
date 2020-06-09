package com.iwiller.service.impl;

import com.iwiller.service.IPermissionService;

import java.util.Arrays;
import java.util.List;

public class PermissionServiceImpl implements IPermissionService {
    public List<String> queryPermissionByUsername(String username) {
        return Arrays.asList("user:query","user:delete","user:update");
    }
}
