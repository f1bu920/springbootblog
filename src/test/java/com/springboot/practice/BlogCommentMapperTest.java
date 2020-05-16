package com.springboot.practice;

import com.springboot.practice.Bean.BlogComment;
import com.springboot.practice.Mapper.BlogCommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCommentMapperTest {
    @Autowired
    private BlogCommentMapper commentMapper;

    @Test
    public void test() {
        int totalBlogComments = commentMapper.getTotalBlogComments(null);
        System.out.println(totalBlogComments);
//        BlogComment blogComment = new BlogComment();
//        blogComment.setBlogId(5L);
//        blogComment.setCommentBody("测试body");
//        blogComment.setCommentator("flbu920");
//        blogComment.setEmail("bufeilong823920@gmail.com");
//        blogComment.setWebsiteUrl("https://f1bu920.github.io");
//        int i = commentMapper.insertSelective(blogComment);
//        System.out.println(i);
        System.out.println(commentMapper.selectByPrimaryKey(27L));
//        System.out.println(commentMapper.getTotalBlogComments(null));
        Integer[] ids = new Integer[]{27};
        System.out.println("checkDone方法:");
        int i = commentMapper.checkDone(ids);
        System.out.println(i);
        Map map = new HashMap();
        map.put("blogId", 5);
        int totalBlogComments1 = commentMapper.getTotalBlogComments(map);
        System.out.println(totalBlogComments1);
        System.out.println("findBlogCommentList");
        List<BlogComment> blogCommentList = commentMapper.findBlogCommentList(null);
        System.out.println(blogCommentList);
        BlogComment blogComment = commentMapper.selectByPrimaryKey(26L);
        blogComment.setCommentator("flbu920");
        int i1 = commentMapper.updateByPrimaryKeySelective(blogComment);
        System.out.println(i1);
        commentMapper.deleteBatch(ids);

    }
}
