package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogComment;

import java.util.List;
import java.util.Map;

public interface BlogCommentMapper {
    int getTotalBlogComments(Map map);

    List<BlogComment> findBlogCommentList(Map map);

    int insertSelective(BlogComment record);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

}
