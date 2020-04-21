package com.springboot.practice;

import com.springboot.practice.Bean.BlogTag;
import com.springboot.practice.Mapper.BlogTagMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogTagMapperTest {
    @Resource
    private BlogTagMapper tagMapper;

    @Test
    public void test() {
//        List<BlogTag> list = new ArrayList<>();
//        BlogTag blogTag = new BlogTag();
//        blogTag.setIsDeleted((byte) 0);
//        blogTag.setTagId(1);
//        blogTag.setTagName("测试标签1");
//        list.add(blogTag);
//        BlogTag blogTag1 = new BlogTag();
//        blogTag1.setIsDeleted((byte) 0);
//        blogTag1.setTagId(1);
//        blogTag1.setTagName("测试标签2");
//        list.add(blogTag1);
//        System.out.println("batchInsertBlogTag方法：");
//        int i = tagMapper.batchInsertBlogTag(list);
//        System.out.println(i);
        System.out.println("findTagList方法：");
        List<BlogTag> tagList = tagMapper.findTagList(null);
        System.out.println(tagList);
        System.out.println("getTotalTags方法：");
        int totalTags = tagMapper.getTotalTags(null);
        System.out.println(totalTags);
        System.out.println("getTotalTags方法：");
        totalTags = tagMapper.getTotalTags(null);
        System.out.println(totalTags);
        Integer[] ids = new Integer[]{135, 136, 137};
        int i = tagMapper.deleteBatch(ids);
    }
}
