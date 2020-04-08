package com.springboot.practice;

import com.springboot.practice.Service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeApplicationTests {
    @Autowired
    private AdminUserService adminUserService;
    @Test
    void contextLoads() {
    }

}
