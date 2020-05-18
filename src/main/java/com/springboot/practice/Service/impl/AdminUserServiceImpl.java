package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.AdminUser;
import com.springboot.practice.Mapper.AdminUserMapper;
import com.springboot.practice.Service.AdminUserService;
import com.springboot.practice.Util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String MD5Password = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, MD5Password);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (adminUser != null) {
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (adminUser != null) {
            //判断原密码是否正确
            if (originalPassword.equals(adminUser.getLoginPassword())) {
                adminUser.setLoginPassword(newPassword);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
