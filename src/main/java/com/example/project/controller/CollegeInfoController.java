package com.example.project.controller;

/**
 * @author: Marcel
 * @date: 2021/5/11 16:04
 */

import com.example.project.bean.CollegeInfo;
import com.example.project.service.CollegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class CollegeInfoController {

    @Autowired
    private CollegeInfoService collegeInfoService;

    /*添加学院*/
    @GetMapping("/admin/college/add")
    public String addCollege(Model model){
        model.addAttribute("new_college",new CollegeInfo());
        return "/admin/colleges/add_college_form";
    }

    /*@ModelAttribute 从request里拿值实例化对象*/
    @PostMapping("/admin/college/add")
    public String addCollegePost(@ModelAttribute CollegeInfo collegeInfo){
        collegeInfoService.addCollege(collegeInfo);
        return "redirect:/admin/college/index";
    }

    /*学院列表*/
    @GetMapping("/admin/college/index")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("/admin/colleges/college_list");
        List<CollegeInfo> college_list=collegeInfoService.getAllCollege();
        modelAndView.addObject("college_list",college_list);
        return modelAndView;
    }
    /*修改学院*/
    @GetMapping("/admin/college/edit/{id}")
    public String editCollege(Model model, @PathVariable String id){
        model.addAttribute("college",collegeInfoService.getCollegeById(id));
        return "/admin/colleges/edit_college_form";
    }
    @PostMapping("/admin/college/edit")
    public String editCollegePost(@ModelAttribute CollegeInfo collegeInfo){
        collegeInfoService.updateCollege(collegeInfo);
        return "redirect:/admin/college/index";
    }

    @GetMapping("/admin/college/del/{id}")
    public String delCollege(@PathVariable String id){
        collegeInfoService.delCollege(id);
        return "redirect:/admin/college/index";
    }


}
