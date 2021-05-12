package com.example.project.controller;

import com.example.project.bean.QuestionInfo;
import com.example.project.service.QuestionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/12 13:31
 */

@Controller
public class QuestionInfoController {

    @Autowired
    private QuestionInfoService questionInfoService;

    /*添加问题*/
    @GetMapping("/admin/question/add")
    public String addQuestion(Model model){
        model.addAttribute("new_question",new QuestionInfo());
        return "/admin/questions/add_question_form";
    }

    @PostMapping("/admin/question/add")
    public String addQuestionPost(@ModelAttribute QuestionInfo questionInfo){
        questionInfoService.addQuestion(questionInfo);
        return "redirect:/admin/question/index";
    }

    /*查看问题*/
    @GetMapping("/admin/question/index")
    public ModelAndView showQuestion(){
        ModelAndView modelAndView =new ModelAndView("/admin/questions/question_list");
        List<QuestionInfo> question_list=questionInfoService.getAllQuestion();
        modelAndView.addObject("question_list",question_list);
        return modelAndView;
    }

    /*编辑问题*/
    @GetMapping("/admin/question/edit/{id}")
    public String editQuestion(Model model, @PathVariable String id){
        model.addAttribute("question",questionInfoService.getQuestionById(id));
        return "/admin/questions/edit_question_form";
    }
    @PostMapping("/admin/question/edit")
    public String editQuestionPost(@ModelAttribute QuestionInfo questionInfo){
        questionInfoService.updateQuestion(questionInfo);
        return "redirect:/admin/question/index";
    }

    /*删除问题*/
    @GetMapping("/admin/question/del/{id}")
    public String delQuestion(@PathVariable String id){
        questionInfoService.delQuestion(id);
        return "redirect:/admin/question/index";
    }

}
