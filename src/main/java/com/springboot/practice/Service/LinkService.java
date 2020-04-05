package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogLink;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;

import java.util.List;
import java.util.Map;

public interface LinkService {
    Map<Byte, List<BlogLink>> getLinksForLinkPage();

    /**
     * 查询友链的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Boolean saveLink(BlogLink link);

    BlogLink selectById(int id);

    Boolean updateLink(BlogLink blogLink);

    Boolean deleteBatch(Integer[] ids);
}
