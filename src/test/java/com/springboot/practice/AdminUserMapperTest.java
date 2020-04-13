package com.springboot.practice;

import com.springboot.practice.Bean.AdminUser;
import com.springboot.practice.Mapper.AdminUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserMapperTest {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Test
    @Transactional
    public void test() {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(1);
        System.out.println(adminUser.toString());
        AdminUser adminUser1 = new AdminUser();
        adminUser1.setAdminUserId(1);
        adminUser1.setLoginPassword("admin");
        adminUser1.setLoginUserName("admin");
        int i = adminUserMapper.updateByPrimaryKeySelective(adminUser1);
        System.out.println(i);
        AdminUser adminUser2 = adminUserMapper.selectByPrimaryKey(1);
        System.out.println(adminUser2.toString());
        AdminUser login = adminUserMapper.login("admin", "admin");
        System.out.println(login);
    }
}
