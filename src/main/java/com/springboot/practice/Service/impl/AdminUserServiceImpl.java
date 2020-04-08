package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.AdminUser;
import com.springboot.practice.Mapper.AdminUserMapper;
import com.springboot.practice.Service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName, password);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return null;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        return null;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        return null;
    }
}
