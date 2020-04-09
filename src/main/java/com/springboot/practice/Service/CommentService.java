package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogComment;
import com.springboot.practice.Util.PageQueryUtil;
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
     *
     * @param blogComment
     * @return
     */
    Boolean addComment(BlogComment blogComment);

    int getTotalComments();

    /**
     * 后台管理中的评论分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getCommentsPage(PageQueryUtil pageUtil);

    /**
     * 批量审核
     *
     * @param ids
     * @return
     */
    Boolean checkDone(Integer[] ids);

    /**
     * 添加回复
     *
     * @param commentId
     * @param replyBody
     * @return
     */
    Boolean reply(Long commentId, String replyBody);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);
}
