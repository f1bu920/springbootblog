package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogTagCount;
import com.springboot.practice.Mapper.BlogTagMappler;
import com.springboot.practice.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    BlogTagMappler blogTagMappler;
    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMappler.getTagCount();
    }
}
