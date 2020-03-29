package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Mapper.BlogCategoryMapper;
import com.springboot.practice.Service.CategoryService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageQueryUtil) {
        List<BlogCategory> categoryList = blogCategoryMapper.findCategoryList(pageQueryUtil);
        int total = blogCategoryMapper.getTotalCategories(pageQueryUtil);
        PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), categoryList);
        return pageResult;
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory tempCategory = blogCategoryMapper.selectByCategoryName(categoryName);
        if (tempCategory == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }
}
