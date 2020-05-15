package com.springboot.practice;

import com.springboot.practice.Bean.Blog;
import com.springboot.practice.Mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
    @Resource
    private BlogMapper blogMapper;

    @Test
    public void test() {
//        Blog blog = new Blog();
//        blog.setBlogCategoryId(1);
//        blog.setBlogCategoryName("测试分类1");
//        blog.setBlogContent("测试内容1");
//        blog.setBlogStatus((byte) 1);
//        blog.setBlogTags("测试标签3");
//        blog.setBlogTitle("博客标题");
//        blog.setBlogSubUrl("测试SubUrl");
//        blog.setBlogCoverImage("测试CoverImage");
//        int i = blogMapper.insertSelective(blog);
//        System.out.println(i);
        int totalBlogs = blogMapper.getTotalBlogs(null);
        System.out.println(totalBlogs);
        List<Blog> blogList = blogMapper.findBlogList(null);
        System.out.println("findBlogList方法：");
        System.out.println(blogList);
        Blog blog = blogMapper.selectByPrimaryKey(1L);
        System.out.println("selectByPrimaryKey方法：");
        System.out.println(blog);
        List<Blog> blogListByType = blogMapper.findBlogListByType(0, 2);
        System.out.println("findBlogListByType方法：");
        System.out.println(blogListByType);
    }
}
