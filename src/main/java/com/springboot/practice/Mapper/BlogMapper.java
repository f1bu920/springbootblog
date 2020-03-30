package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.Blog;
import com.springboot.practice.Util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    List<Blog> findBlogList(PageQueryUtil pageQueryUtil);

    int getTotalBlogs(PageQueryUtil pageQueryUtil);

    List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

    Blog selectByPrimaryKey(Long blogId);

    int updateByBlog(Blog record);

    List<Blog> getBlogsPageByTagId(PageQueryUtil pageQueryUtil);

    int getTotalBlogsByTagId(PageQueryUtil pageUtil);

    Blog selectBySubUrl(String subUrl);

    int insertSelective(Blog record);

    int updateByPrimaryKeySelective(Blog record);

    int deleteBatch(Integer[] ids);

    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids") Integer[] ids);
}
