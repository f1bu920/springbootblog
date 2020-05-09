package com.springboot.practice;

import com.springboot.practice.Bean.Blog;
import com.springboot.practice.Mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
    @Resource
    private BlogMapper blogMapper;

    @Test
    public void test() {
        Blog blog = new Blog();
        blog.setBlogCategoryId(1);
        blog.setBlogCategoryName("测试分类1");
        blog.setBlogContent("测试内容1");
        blog.setBlogStatus((byte) 1);
        blog.setBlogTags("测试标签3");
        int i = blogMapper.insertSelective(blog);
        System.out.println(i);
        int totalBlogs = blogMapper.getTotalBlogs(null);
        System.out.println(totalBlogs);
    }
}
