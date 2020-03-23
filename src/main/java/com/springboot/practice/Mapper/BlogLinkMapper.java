package com.springboot.practice.Mapper;

import com.springboot.practice.Bean.BlogLink;
import com.springboot.practice.Util.PageQueryUtil;

import java.util.List;

public interface BlogLinkMapper {
    List<BlogLink> findLinkList(PageQueryUtil pageUtil);
}
