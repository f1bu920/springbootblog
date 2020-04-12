package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogTag;
import com.springboot.practice.Bean.BlogTagCount;
import com.springboot.practice.Mapper.BlogTagMapper;
import com.springboot.practice.Mapper.BlogTagRelationMapper;
import com.springboot.practice.Service.TagService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        int total = blogTagMapper.getTotalTags(pageUtil);
        List<BlogTag> tagList = blogTagMapper.findTagList(pageUtil);
        PageResult pageResult = new PageResult(total, pageUtil.getLimit(), pageUtil.getPage(), tagList);
        return pageResult;
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTag temp = blogTagMapper.selectByTagName(tagName);
        if (temp == null) {
            BlogTag blogTag = new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public int getTotalTags() {
        return blogTagMapper.getTotalTags(null);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        //已存在关联数据不删除
        List<Long> relations = blogTagRelationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        return blogTagMapper.deleteBatch(ids) > 0;
    }
}
