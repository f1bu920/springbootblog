package com.springboot.practice;

import com.springboot.practice.Bean.BlogConfig;
import com.springboot.practice.Mapper.BlogConfigMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogConfigMapperTest {
    @Autowired
    private BlogConfigMapper configMapper;

    @Test
    public void test() {
        List<BlogConfig> blogConfigs = configMapper.selectAll();
        System.out.println(blogConfigs);
        System.out.println("selectByPrimaryKey");
        BlogConfig yourName = configMapper.selectByPrimaryKey("yourName");
        System.out.println(yourName);
        System.out.println("update:");
        yourName.setConfigValue("flbu920");
        int i = configMapper.updateByPrimaryKeySelective(yourName);
        System.out.println(i);
    }
}
