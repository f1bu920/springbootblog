package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogConfig;

import java.util.List;

public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);
}
