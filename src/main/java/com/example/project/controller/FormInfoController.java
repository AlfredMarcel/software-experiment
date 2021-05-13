package com.example.project.controller;

import com.example.project.bean.FormInfo;
import com.example.project.service.FormInfoService;
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
 * @date: 2021/5/13 13:51
 */

@Controller
public class FormInfoController {

    @Autowired
    private FormInfoService formInfoService;

    @Autowired
    private QuestionInfoService questionInfoService;

    /*添加问卷*/
    @GetMapping("/admin/form/add")
    public String addForm(Model model){
        model.addAttribute("new_form",new FormInfo());
        model.addAttribute("questions",questionInfoService.getAllSimpleQuestion());
        return "/admin/forms/add_form_form";
    }
    @PostMapping("/admin/form/add")
    public String addFormPost(@ModelAttribute FormInfo formInfo){
        formInfoService.addForm(formInfo);
        return "redirect:/admin/form/index";
    }

    /*查看问卷列表*/
    @GetMapping("/admin/form/index")
    public ModelAndView showForm(){
        ModelAndView modelAndView=new ModelAndView("/admin/forms/form_list");
        List<FormInfo> form_list=formInfoService.getAllSimpleForm();
        modelAndView.addObject("form_list",form_list);
        return modelAndView;
    }

    /*预览问卷*/
    @GetMapping("/admin/form/preview/{id}")
    public String previewForm(Model model, @PathVariable String id){
        model.addAttribute("form",formInfoService.getFormById(id));
        return "/admin/forms/preview_form";
    }

    /*编辑问卷*/
    @GetMapping("admin/form/edit/{id}")
    public String editForm(Model model,@PathVariable String id){
        model.addAttribute("form",formInfoService.getFormById(id));
        model.addAttribute("questions",questionInfoService.getAllSimpleQuestion());
        return "/admin/forms/edit_form_form";
    }
    @PostMapping("admin/form/edit")
    public String editFormPost(@ModelAttribute FormInfo formInfo){
        formInfoService.updateForm(formInfo);
        return "redirect:/admin/form/index";
    }

    /*删除问卷*/
    @GetMapping("/admin/form/del/{id}")
    public String delForm(@PathVariable String id){
        formInfoService.delForm(id);
        return "redirect:/admin/form/index";
    }

}
