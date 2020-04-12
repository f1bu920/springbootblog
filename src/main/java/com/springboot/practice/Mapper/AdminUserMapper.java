package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer loginUserId);

    int updateByPrimaryKeySelective(AdminUser record);
}
