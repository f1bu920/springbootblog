package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogTag;
import com.springboot.practice.Bean.BlogTagCount;
import com.springboot.practice.Util.PageQueryUtil;

import java.util.List;

public interface BlogTagMapper {
    List<BlogTagCount> getTagCount();

    BlogTag selectByTagName(String tagName);

    int batchInsertBlogTag(List<BlogTag> tagList);

    int getTotalTags(PageQueryUtil pageQueryUtil);

    List<BlogTag> findTagList(PageQueryUtil pageQueryUtil);

    int insertSelective(BlogTag record);

    int deleteBatch(Integer[] ids);
}
