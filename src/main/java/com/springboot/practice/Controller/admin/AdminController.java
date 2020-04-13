package com.springboot.practice.Controller.admin;

import com.springboot.practice.Bean.AdminUser;
import com.springboot.practice.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    @Resource
    private LinkService linkService;
    @Resource
    private CommentService commentService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/test")
    public String test() {
        return "admin/test";
    }

    @GetMapping(value = {"", "/index", "/index.html", "/"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        request.setAttribute("blogCount", blogService.getTotalBlogs());
        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("tagCount", tagService.getTotalTags());
        request.setAttribute("linkCount", linkService.getTotalLinks());
        request.setAttribute("commentService", commentService.getTotalComments());
        return "admin/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpSession session,
                        @RequestParam("userName") String userName,
                        @RequestParam("password") String password
//                      @RequestParam("verifyCode") String verifyCode
    ) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
//            设置session过期时间为2小时
//            session.setMaxInactiveInterval(60 * 60 * 2);
//            return "redirect:/admin/index";
            return "success";
        } else {
            session.setAttribute("errorMsg", "登录失败");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    @ResponseBody
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String updateName(HttpServletRequest request,
                             @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String updatePassword(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //成功更改密码后前端跳转至登录页面
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }
    }

    @GetMapping("/layout")
    public String layout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
