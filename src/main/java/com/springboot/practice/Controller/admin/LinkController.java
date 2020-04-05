package com.springboot.practice.Controller.admin;

import com.springboot.practice.Bean.BlogLink;
import com.springboot.practice.Service.LinkService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.Result;
import com.springboot.practice.Util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LinkController {
    @Resource
    private LinkService linkService;

    @GetMapping("/links")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "links");
        return "admin/links";
    }

    @GetMapping("/links/list")
    public Result list(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(linkService.getBlogLinkPage(pageQueryUtil));
    }

    /**
     * 保存链接
     *
     * @param linkType
     * @param linkName
     * @param linkUrl
     * @param linkRank
     * @param linkDescription
     * @return
     */
    @PostMapping("/links/save")
    @ResponseBody
    public Result save(@RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription) {
        if (linkType == null || linkType < 0 || linkRank == null || linkRank < 0 || StringUtils.isEmpty(linkName) || StringUtils.isEmpty(linkUrl) || StringUtils.isEmpty(linkDescription)) {
            return ResultGenerator.genFailResult("参数异常");
        }
        BlogLink blogLink = new BlogLink();
        blogLink.setLinkName(linkName);
        blogLink.setLinkDescription(linkDescription);
        blogLink.setLinkRank(linkRank);
        blogLink.setLinkType(linkType.byteValue());
        blogLink.setLinkUrl(linkUrl);
        return ResultGenerator.genSuccessResult(linkService.saveLink(blogLink));
    }

    /**
     * 链接详情
     *
     * @param id
     * @return
     */
    @GetMapping("/links/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        return ResultGenerator.genSuccessResult(linkService.selectById(id));
    }

    @PostMapping("/links/update")
    @ResponseBody
    public Result update(@RequestParam("linkId") Integer linkId,
                         @RequestParam("linkType") Integer linkType,
                         @RequestParam("linkName") String linkName,
                         @RequestParam("linkUrl") String linkUrl,
                         @RequestParam("linkRank") Integer linkRank,
                         @RequestParam("linkDescription") String linkDescription) {
        BlogLink blogLink = linkService.selectById(linkId);
        if (blogLink == null) {
            return ResultGenerator.genFailResult("不存在");
        }
        if (linkType == null || linkType < 0 || linkRank == null || linkRank < 0 || StringUtils.isEmpty(linkDescription) || StringUtils.isEmpty(linkName) || StringUtils.isEmpty(linkUrl)) {
            return ResultGenerator.genFailResult("参数异常");
        }
        blogLink.setLinkUrl(linkUrl);
        blogLink.setLinkType(linkType.byteValue());
        blogLink.setLinkRank(linkRank);
        blogLink.setLinkDescription(linkDescription);
        blogLink.setLinkName(linkName);
        return ResultGenerator.genSuccessResult(linkService.updateLink(blogLink));
    }

    @PostMapping("/links/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (linkService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
