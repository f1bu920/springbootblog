package com.springboot.practice.Service.impl;

import com.springboot.practice.Bean.Blog;
import com.springboot.practice.Bean.BlogCategory;
import com.springboot.practice.Bean.BlogTag;
import com.springboot.practice.Bean.BlogTagRelation;
import com.springboot.practice.Controller.VO.BlogDetailVO;
import com.springboot.practice.Controller.VO.BlogListVO;
import com.springboot.practice.Controller.VO.SimpleBlogListVO;
import com.springboot.practice.Mapper.*;
import com.springboot.practice.Service.BlogService;
import com.springboot.practice.Util.MarkDownUtil;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import com.springboot.practice.Util.PatternUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private BlogCommentMapper commentMapper;
    @Autowired
    private BlogTagMapper tagMapper;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;


    @Override
    public PageResult getBlogsForIndexPage(int page) {
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", 8);
        //过滤发布状态下的博客
        params.put("blogStatus", 1);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
        List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);//转化为视图对象
        int total = blogMapper.getTotalBlogs(pageQueryUtil);
        PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), blogListVOS);
        return pageResult;
    }

    @Override
    public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        List<Blog> blogList = blogMapper.findBlogListByType(type, 9);
        if (!CollectionUtils.isEmpty(blogList)) {
            for (Blog blog : blogList) {
                SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
                BeanUtils.copyProperties(blog, simpleBlogListVO);
                simpleBlogListVOS.add(simpleBlogListVO);
            }
        }
        return simpleBlogListVOS;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        BlogDetailVO blogDetailVO = getBlogDetailVO(blog);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public PageResult getBlogsByTag(String tagName, int page) {
        if (PatternUtil.validKeyword(tagName)) {
            BlogTag tag = tagMapper.selectByTagName(tagName);
            if (tag != null && page > 0) {
                Map params = new HashMap();
                params.put("limit", 9);
                params.put("page", page);
                params.put("tagId", tag.getTagId());
                PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
                List<Blog> blogList = blogMapper.getBlogsPageByTagId(pageQueryUtil);
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
                int total = blogMapper.getTotalBlogsByTagId(pageQueryUtil);
                PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), blogListVOS);
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsByCategory(String categoryName, int page) {
        if (PatternUtil.validKeyword(categoryName)) {
            BlogCategory blogCategory = categoryMapper.selectByCategoryName(categoryName);
            if ("默认分类".equals(categoryName) && blogCategory == null) {
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
            }
            if (blogCategory != null && page > 0) {
                Map params = new HashMap();
                params.put("page", page);
                params.put("limit", 9);
                params.put("blogCategoryId", blogCategory.getCategoryId());
                params.put("blogStatus", 1);//过滤发布状态下的数据
                PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
                List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
                int total = blogMapper.getTotalBlogs(pageQueryUtil);
                PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), blogListVOS);
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsBySearch(String keyword, int page) {
        if (page > 0 && PatternUtil.validKeyword(keyword)) {
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", 9);
            params.put("keyword", keyword);
            params.put("blogStatus", 1);
            PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
            List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
            List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
            int total = blogMapper.getTotalBlogs(pageQueryUtil);
            PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), blogListVOS);
            return pageResult;
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
        Blog blog = blogMapper.selectBySubUrl(subUrl);
        BlogDetailVO blogDetailVO = getBlogDetailVO(blog);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public PageResult getBlogsPage(PageQueryUtil pageQueryUtil) {
        List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
        int total = blogMapper.getTotalBlogs(pageQueryUtil);
        PageResult pageResult = new PageResult(total, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), blogList);
        return pageResult;
    }

    @Override
    public Blog getBlogByBlogId(Long blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    @Override
    @Transactional
    public String saveBlog(Blog blog) {
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //分类Rank值+1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签个数最多为6";
        }
        //保存文章
        if (blogMapper.insertSelective(blog) > 0) {
            //要新增的tags
            List<BlogTag> tagListForInsert = new ArrayList<>();
            //这篇blog所有的tags，用来建立关系数据
            List<BlogTag> allTagsList = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                BlogTag tag = tagMapper.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增tag
                    BlogTag tempTag = new BlogTag();
                    tempTag.setTagName(tags[i]);
                    tagListForInsert.add(tempTag);
                } else {
                    allTagsList.add(tag);
                }
            }
            //新增标签数据
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                tagMapper.batchInsertBlogTag(tagListForInsert);
            }
            //修改分类排序值
            categoryMapper.updateByPrimaryKeySelective(blogCategory);
            //新增关系数据
            List<BlogTagRelation> blogTagRelations = new ArrayList<>();
            for (BlogTag tag : allTagsList) {
                BlogTagRelation blogTagRelation = new BlogTagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "保存成功";
            }
        }
        return "保存失败";
    }

    @Override
    @Transactional
    public String updateBlog(Blog blog) {
        Blog blogForUpdate = blogMapper.selectByPrimaryKey(blog.getBlogId());
        if (blogForUpdate == null) {
            return "数据不存在";
        }
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
        blogForUpdate.setBlogContent(blog.getBlogContent());
        blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
        blogForUpdate.setBlogStatus(blog.getBlogStatus());
        blogForUpdate.setEnableComment(blog.getEnableComment());
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blogForUpdate.setBlogCategoryId(0);
            blogForUpdate.setBlogCategoryName("默认分类");
        } else {
            //设置分类名称
            blogForUpdate.setBlogCategoryId(blogCategory.getCategoryId());
            blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
            //分类排序值+1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        blogForUpdate.setBlogTags(blog.getBlogTags());
        //新增的标签
        List<BlogTag> tagsForInsert = new ArrayList<>();
        //这篇博客所有的标签
        List<BlogTag> allTagsList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BlogTag tag = tagMapper.selectByTagName(tags[i]);
            if (tag == null) {
                BlogTag tempTag = new BlogTag();
                tempTag.setTagName(tags[i]);
                tagsForInsert.add(tempTag);
            } else {
                allTagsList.add(tag);
            }
        }
        //新增标签数据
        if (!CollectionUtils.isEmpty(tagsForInsert)) {
            tagMapper.batchInsertBlogTag(tagsForInsert);
        }
        allTagsList.addAll(tagsForInsert);
        //新增关系数据
        List<BlogTagRelation> blogTagRelations = new ArrayList<>();
        for (BlogTag tag : allTagsList) {
            BlogTagRelation blogTagRelation = new BlogTagRelation();
            blogTagRelation.setBlogId(blog.getBlogId());
            blogTagRelation.setTagId(tag.getTagId());
            blogTagRelations.add(blogTagRelation);
        }
        //修改分类排序信息
        categoryMapper.updateByPrimaryKeySelective(blogCategory);
        //删除原关系数据
        blogTagRelationMapper.deleteByBlogId(blog.getBlogId());
        //新增关系数据
        blogTagRelationMapper.batchInsert(blogTagRelations);
        if (blogMapper.updateByPrimaryKeySelective(blogForUpdate) > 0) {
            return "success";
        }
        return "修改失败";
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogMapper.deleteBatch(ids) > 0;
    }

    @Override
    public int getTotalBlogs() {
        return blogMapper.getTotalBlogs(null);
    }

    private List<BlogListVO> getBlogListVOsByBlogs(List<Blog> blogList) {
        List<BlogListVO> blogListVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blogList)) {
            List<Integer> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
            Map<Integer, String> blogCategoryMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(categoryIds)) {
                List<BlogCategory> blogCategories = categoryMapper.selectByCategoryIds(categoryIds);
                if (!CollectionUtils.isEmpty(blogCategories)) {
                    blogCategoryMap = blogCategories.stream().collect(Collectors.toMap(BlogCategory::getCategoryId, BlogCategory::getCategoryIcon, (key1, key2) -> key2));
                }
            }
            for (Blog blog : blogList) {
                BlogListVO blogListVO = new BlogListVO();
                BeanUtils.copyProperties(blog, blogListVO);
                if (blogCategoryMap.containsKey(blog.getBlogCategoryId())) {
                    blogListVO.setBlogCategoryIcon(blogCategoryMap.get(blog.getBlogCategoryId()));
                } else {
                    blogListVO.setBlogCategoryId(0);
                    blogListVO.setBlogCategoryName("默认分类");
                    blogListVO.setBlogCategoryIcon("/admin/img/logo.png");
                }
                blogListVOS.add(blogListVO);
            }
        }
        return blogListVOS;
    }

    /**
     * 将Blog对象转化为BlogDetailVO对象
     *
     * @param blog
     * @return
     */
    private BlogDetailVO getBlogDetailVO(Blog blog) {
        if (blog != null && blog.getBlogStatus() == 1) {
            //增加浏览量
            blog.setBlogViews(blog.getBlogViews() + 1);
            blogMapper.updateByBlog(blog);
            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(blog, blogDetailVO);
            blogDetailVO.setBlogContent(MarkDownUtil.mdToHtml(blogDetailVO.getBlogContent()));
            BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
            if (blogCategory == null) {
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
                blogCategory.setCategoryName("默认分类");
                blogCategory.setCategoryIcon("/admin/dist/img/category/00.png");
            }
            //分类信息
            blogDetailVO.setBlogCategoryIcon(blogCategory.getCategoryIcon());
            if (!StringUtils.isEmpty(blog.getBlogTags())) {
                List<String> tags = Arrays.asList(blog.getBlogTags().split(","));
                blogDetailVO.setBlogTags(tags);
            }
            //评论数
            Map params = new HashMap();
            params.put("blogId", blog.getBlogId());
            params.put("commentStatus", 1);
            blogDetailVO.setCommentCount(commentMapper.getTotalBlogComments(params));
            return blogDetailVO;
        }
        return null;
    }
}
