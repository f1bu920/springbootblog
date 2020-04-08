package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogComment;
import com.springboot.practice.Util.PageResult;

public interface CommentService {
    /**
     * 根据文章id与分页参数获取评论列表
     * @param blogId
     * @param page
     * @return
     */
    PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page);

    /**
     * 添加评论
     * @param blogComment
     * @return
     */
    Boolean addComment(BlogComment blogComment);

    int getTotalComments();
}
