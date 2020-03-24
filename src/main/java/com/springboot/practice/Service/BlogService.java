package com.springboot.practice.Service;

import com.springboot.practice.Bean.Blog;
import com.springboot.practice.Controller.VO.BlogDetailVO;
import com.springboot.practice.Controller.VO.SimpleBlogListVO;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;

import java.util.List;

public interface BlogService {
    /**
     * 获取首页文章列表
     *
     * @param page
     * @return
     */
    PageResult getBlogsForIndexPage(int page);

    /**
     * 首页侧边栏数据列表
     * type=0：点击最多
     * type=1：最新发布
     *
     * @param type
     * @return
     */
    List<SimpleBlogListVO> getBlogListForIndexPage(int type);

    /**
     * 文章详情
     *
     * @param blogId
     * @return
     */
    BlogDetailVO getBlogDetail(Long blogId);

    /**
     * 根据标签获取文章列表
     *
     * @param tagName
     * @param page
     * @return
     */
    PageResult getBlogsByTag(String tagName, int page);

    /**
     * 根据分类获取文章列表
     *
     * @param categoryName
     * @param page
     * @return
     */
    PageResult getBlogsByCategory(String categoryName, int page);

    /**
     * 根据搜索获取文章列表
     *
     * @param keyword
     * @param page
     * @return
     */
    PageResult getBlogsBySearch(String keyword, int page);

    BlogDetailVO getBlogDetailBySubUrl(String subUrl);

    PageResult getBlogsPage(PageQueryUtil pageQueryUtil);

    /**
     * 根据Id获取文章
     *
     * @param blogId
     * @return
     */
    Blog getBlogByBlogId(Long blogId);

    /**
     * 保存文章
     *
     * @param blog
     * @return
     */
    String saveBlog(Blog blog);
}
