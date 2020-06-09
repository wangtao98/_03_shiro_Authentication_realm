package com.iwiller.service;

import com.iwiller.domain.User;


public interface IUserService {

    User queryUserByUsername(String username);

}
