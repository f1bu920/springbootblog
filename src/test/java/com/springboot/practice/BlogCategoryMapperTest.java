package com.springboot.practice;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Mapper.BlogCategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogCategoryMapperTest {
    @Resource
    private BlogCategoryMapper categoryMapper;

    @Test
//    @Transactional
    public void test() {
//        System.out.println("insertSelective方法");
//        BlogCategory blogCategory = new BlogCategory();
//        blogCategory.setCategoryId(2);
//        blogCategory.setCategoryName("测试分类2");
//        blogCategory.setCategoryRank(77);
//        blogCategory.setCreateTime(new Date());
//        blogCategory.setCategoryIcon("测试图标");
//        blogCategory.setIsDeleted((byte) 0);
//        int i = categoryMapper.insertSelective(blogCategory);
//        System.out.println(i);
//        System.out.println("selectByPrimaryKey方法");
//        BlogCategory blogCategory1 = categoryMapper.selectByPrimaryKey(1);
//        System.out.println(blogCategory1);
//        blogCategory1.setCategoryName("测试分类1");
//        System.out.println("update方法");
//        i = categoryMapper.updateByPrimaryKeySelective(blogCategory1);
//        System.out.println(i);
        System.out.println("findCategoryList方法");
        List<BlogCategory> categoryList = categoryMapper.findCategoryList(null);
        for (int j = 0; j < categoryList.size(); j++) {
            System.out.println(categoryList.get(j));
        }
        System.out.println("getTotalCategories方法");
        int totalCategories = categoryMapper.getTotalCategories(null);
        System.out.println(totalCategories);
        System.out.println("selectByCategoryName方法");
        BlogCategory 测试分类 = categoryMapper.selectByCategoryName("测试分类1");
        System.out.println(测试分类);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println("selectByCategoryIds方法");
        List<BlogCategory> blogCategories = categoryMapper.selectByCategoryIds(list);
        for (int j = 0; j < blogCategories.size(); j++) {
            System.out.println(blogCategories.get(j));
        }
        categoryMapper.deleteBatch(new Integer[]{1, 2});

        System.out.println("getTotalCategories方法");
        totalCategories = categoryMapper.getTotalCategories(null);
        System.out.println(totalCategories);
    }
}
