package com.springboot.practice;

import com.springboot.practice.Bean.BlogLink;
import com.springboot.practice.Mapper.BlogLinkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogLinkMapperTest {
    @Autowired
    private BlogLinkMapper linkMapper;

    @Test
    public void test() {

        int totalLinks = linkMapper.getTotalLinks(null);
        System.out.println(totalLinks);
        List<BlogLink> linkList = linkMapper.findLinkList(null);
        System.out.println(linkList);
        System.out.println("selectByPrimaryKeySelective:");
        BlogLink blogLink = linkMapper.selectByPrimaryKey(2);
        System.out.println(blogLink);
//        blogLink.setLinkUrl("https://github.com/f1bu920");
//        int i = linkMapper.updateByPrimaryKeySelective(blogLink);
//        System.out.println(i);
//        System.out.println(linkMapper.selectByPrimaryKey(2));
        System.out.println("测试insert：");
        BlogLink link = new BlogLink();
        link.setLinkUrl("测试url：http://47.97.168.90");
        link.setLinkDescription("测试描述");
        link.setLinkName("测试name");
        link.setLinkRank(77);
        int i = linkMapper.insertSelective(link);
        System.out.println(i);
    }
}
