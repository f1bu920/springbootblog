package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Mapper.BlogCategoryMapper;
import com.springboot.practice.Service.CategoryService;
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
}
