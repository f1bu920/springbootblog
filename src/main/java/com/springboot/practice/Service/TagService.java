package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogTagCount;

import java.util.List;

public interface TagService {

    List<BlogTagCount> getBlogTagCountForIndex();
}
