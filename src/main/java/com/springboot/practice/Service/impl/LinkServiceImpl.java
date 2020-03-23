package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogLink;
import com.springboot.practice.Mapper.BlogLinkMapper;
import com.springboot.practice.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLink> linkList = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(linkList)) {
            //根据type进行分组
            Map<Byte, List<BlogLink>> linksMap = linkList.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
            return linksMap;
        }
        return null;
    }
}
