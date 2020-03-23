package com.springboot.practice.Util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *分页查询参数
 */
public class PageQueryUtil extends LinkedHashMap<String,Object> {
    //当前页码
    private Integer page;
    //每页条数
    private Integer limit;

    public PageQueryUtil(Map<String,Object> params){
        this.putAll(params);
        //分页参数
        this.page=Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("start",(page-1)*limit);
        this.put("page",page);
        this.put("limit",limit);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageQueryUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
