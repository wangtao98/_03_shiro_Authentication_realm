package com.iwiller.service;

import java.util.List;

public interface IPermissionService {
    List<String> queryPermissionByUsername(String username);

}
