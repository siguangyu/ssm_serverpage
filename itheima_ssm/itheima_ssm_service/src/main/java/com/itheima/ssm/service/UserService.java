package com.itheima.ssm.service;

import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserInfo> findAllUser(int page,int size);
    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);
}
