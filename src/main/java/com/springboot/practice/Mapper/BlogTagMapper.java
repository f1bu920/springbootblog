package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogTag;
import com.springboot.practice.Bean.BlogTagCount;

import java.util.List;

public interface BlogTagMapper {
    List<BlogTagCount> getTagCount();

    BlogTag selectByTagName(String tagName);
}
