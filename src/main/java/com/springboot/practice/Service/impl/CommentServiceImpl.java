package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.BlogComment;
import com.springboot.practice.Mapper.BlogCommentMapper;
import com.springboot.practice.Service.CommentService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
        if (page < 1) {
            return null;
        }
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", 8);
        params.put("blogId", blogId);
        params.put("commentStatus", 1);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        List<BlogComment> comments = blogCommentMapper.findBlogCommentList(pageQueryUtil);
        if (!CollectionUtils.isEmpty(comments)) {
            int total = blogCommentMapper.getTotalBlogComments(pageQueryUtil);
            PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), comments);
            return pageResult;
        }
        return null;
    }

    @Override
    public Boolean addComment(BlogComment blogComment) {
        return blogCommentMapper.insertSelective(blogComment) > 0;
    }

    @Override
    public int getTotalComments() {
        return blogCommentMapper.getTotalBlogComments(null);
    }
}
