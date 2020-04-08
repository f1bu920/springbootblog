package com.springboot.practice.Service;

import com.springboot.practice.Bean.AdminUser;

public interface AdminUserService {
    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(String userName, String password);

    /**
     * 根据id获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 更新用户名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

    /**
     * 修改密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);
}
