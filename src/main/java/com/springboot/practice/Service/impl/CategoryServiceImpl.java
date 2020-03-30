package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Mapper.BlogCategoryMapper;
import com.springboot.practice.Mapper.BlogMapper;
import com.springboot.practice.Service.CategoryService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogMapper blogMapper;

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
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategory != null) {
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            //修改分类实体
            blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        return blogCategoryMapper.deleteBatch(ids) > 0;
    }
}
