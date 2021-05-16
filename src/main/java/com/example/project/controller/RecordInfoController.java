package com.example.project.controller;

import com.example.project.bean.FormInfo;
import com.example.project.bean.QuestionInfo;
import com.example.project.bean.RecordInfo;
import com.example.project.service.CollegeInfoService;
import com.example.project.service.FormInfoService;
import com.example.project.service.RecordInfoService;
import com.example.project.service.UserStatusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 15:52
 */

@Controller
public class RecordInfoController {

    @Autowired
    private RecordInfoService recordInfoService;

    @Autowired
    private CollegeInfoService collegeInfoService;

    @Autowired
    private FormInfoService formInfoService;

    @Autowired
    private UserStatusInfoService userStatusInfoService;

    /*教师的每日打卡*/
    @GetMapping("/teacher/daily")
    public String recordTeaAdd(Model model, HttpServletRequest request){
        recordAdd(model,request);
        return "/teacher/dailys/daily";
    }

    @PostMapping("/teacher/daily")
    public String recordTeaAddPost(@ModelAttribute RecordInfo recordInfo){
        recordInfoService.addRecord(recordInfo);
        /*之后，系统自动对打卡结果进行高危判定*/
        userStatusInfoService.judgeRecord(recordInfo);
        return "/teacher/index";
    }

    /*学生的每日打卡*/
    @GetMapping("/student/daily")
    public String recordStuAdd(Model model, HttpServletRequest request){
        recordAdd(model,request);
        return "/student/dailys/daily";
    }

    @PostMapping("/student/daily")
    public String recordStuAddPost(@ModelAttribute RecordInfo recordInfo){
        recordInfoService.addRecord(recordInfo);
        /*之后，系统自动对打卡结果进行高危判定*/
        userStatusInfoService.judgeRecord(recordInfo);
        return "/student/index";
    }

//    /*查看历史打卡信息(需更改)*/
//    @GetMapping("/teacher/history")
//    public String recordHistory(HttpServletRequest request,Model model){
//        HttpSession session=request.getSession();
//        List<RecordInfo> recordInfos=recordInfoService.getHistory(String.valueOf(session.getAttribute("user_id")));
//        model.addAttribute("records",recordInfos);
//        return "/teacher/histories/history";
//    }

    /*处理打卡信息入口，教师、学生共用*/
    private void recordAdd(Model model,HttpServletRequest request){
        /*获取session*/
        HttpSession session=request.getSession();

        /*从session中的学院编号拿到对应的问卷编号*/
        String tmp=String.valueOf(session.getAttribute("user_college"));
        Integer formId=collegeInfoService.findFormIdById(tmp);

        /*查问卷的每一个问题，并进行解析*/
        FormInfo formInfo=formInfoService.getFormById(String.valueOf(formId));
        List<QuestionInfo> questionInfos=formInfoService.parseFormToQuestions(formInfo);

        /*将问卷名，解析后的问卷内容，学工号，问卷id存入模型，跳转前端渲染*/
        model.addAttribute("form_name",formInfo.getName());
        model.addAttribute("questions",questionInfos);
        model.addAttribute("new_record",new RecordInfo(String.valueOf(session.getAttribute("user_id")),formInfo.getId()));
    }




}
