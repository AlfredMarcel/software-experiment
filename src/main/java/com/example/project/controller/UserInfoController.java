package com.example.project.controller;

/**
 * @author: Marcel
 * @date: 2021/5/11 16:24
 */

import com.example.project.bean.UserInfo;
import com.example.project.service.CollegeInfoService;
import com.example.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CollegeInfoService collegeInfoService;

    /*注册*/
    @GetMapping("/logup")
    public String addUser(Model model){
        model.addAttribute("new_user",new UserInfo());
        model.addAttribute("colleges",collegeInfoService.getAllCollege());
        return "/loginfo/add_user_form";
    }
    @PostMapping("/logup")
    public String addUserPost(@ModelAttribute UserInfo userInfo){
        /*邮箱验证 没写*/
        /*添加注册用户*/
        userInfoService.addUser(userInfo);
        return "redirect:/index";
    }

    /*登录*/
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new UserInfo());
        return "/loginfo/login_form";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, @ModelAttribute UserInfo userInfo){
        int author=userInfoService.judge(userInfo.getSno(),userInfo.getPassword());
        if(author!=-1){
            UserInfo new_userInfo=userInfoService.findUser(userInfo.getSno());

            /*个人信息，登录后存进session*/
            HttpSession session = request.getSession();
            session.setAttribute("user_id", new_userInfo.getSno());
            session.setAttribute("user_college", new_userInfo.getCollege());
            session.setAttribute("user_authority", new_userInfo.getAuthority());

            if (author == 10) {
                return "redirect:/student/index";
            } else if (author == 20) {
                return "redirect:/teacher/index";
            } else if (author == 30) {
                return "redirect:/admin/index";
            }
        }
        return "redirect:/index";
    }

    /*登出功能，我还没写*/

    /*跳转登录页面*/
    @GetMapping("/student/index")
    public String stuLogin(){
        return "/student/index";
    }

    @GetMapping("/teacher/index")
    public String teaLogin(){
        return "/teacher/index";
    }

    @GetMapping("/admin/index")
    public String adminLogin(){
        return "/admin/index";
    }


}
