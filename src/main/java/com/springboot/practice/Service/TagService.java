package com.springboot.practice.Service;

import com.springboot.practice.Bean.BlogTagCount;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService {

    List<BlogTagCount> getBlogTagCountForIndex();

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean saveTag(String tagName);

    int getTotalTags();

    Boolean deleteBatch(Integer[] ids);
}
