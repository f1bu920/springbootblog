package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCategoryMapper {

    List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    List<BlogCategory> findCategoryList(PageQueryUtil pageQueryUtil);

    BlogCategory selectByPrimaryKey(Integer categoryId);

    BlogCategory selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(BlogCategory record);

    int getTotalCategories(PageQueryUtil pageUtil);

    int insertSelective(BlogCategory record);

    int deleteBatch(Integer[] ids);
}
