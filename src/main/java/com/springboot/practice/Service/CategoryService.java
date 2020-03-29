package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;

import java.util.List;

public interface CategoryService {
    List<BlogCategory> getAllCategories();

    /**
     * 查询分类的分页数据
     *
     * @param pageQueryUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageQueryUtil);

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName, String categoryIcon);

    int getTotalCategories();
}
