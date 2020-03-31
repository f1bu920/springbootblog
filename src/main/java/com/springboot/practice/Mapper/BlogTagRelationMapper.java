package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagRelationMapper {
    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

    int deleteByBlogId(Long blogId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);
}
