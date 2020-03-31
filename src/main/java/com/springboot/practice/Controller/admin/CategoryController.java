package com.springboot.practice.Controller.admin;

import com.springboot.practice.Service.CategoryService;
import com.springboot.practice.Util.PageQueryUtil;
import com.springboot.practice.Util.Result;
import com.springboot.practice.Util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    @GetMapping("/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("limit") == null || params.get("page") == null) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageQueryUtil));
    }

    /**
     * 添加分类
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @PostMapping("/categories/save")
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName, @RequestParam("categoryIcon") String categoryIcon) {
        if (StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryIcon)) {
            return ResultGenerator.genFailResult("请输入分类名称或选择分类图标");
        }
        if (categoryService.saveCategory(categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }


    @PostMapping("/categories/update")
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon) {
        if (StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryIcon)) {
            return ResultGenerator.genFailResult("请输入分类名称或者选择分类图标");
        }
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 删除分类
     *
     * @param ids
     * @return
     */
    @PostMapping("/categories/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (categoryService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
