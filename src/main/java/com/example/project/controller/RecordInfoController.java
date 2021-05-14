package com.example.project.controller;

import com.example.project.bean.FormInfo;
import com.example.project.bean.QuestionInfo;
import com.example.project.bean.RecordInfo;
import com.example.project.service.CollegeInfoService;
import com.example.project.service.FormInfoService;
import com.example.project.service.RecordInfoService;
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

    /*教师的每日打卡*/
    @GetMapping("/teacher/daily")
    public String recordAdd(Model model, HttpServletRequest request){
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

        return "/teacher/dailys/daily";
    }

    @PostMapping("/teacher/daily")
    public String recordAddPost(@ModelAttribute RecordInfo recordInfo){
        recordInfoService.addRecord(recordInfo);
        return "/teacher/index";
    }

//formInfo.getId()
//    /*查看历史打卡信息*/
//    @GetMapping("/teacher/history")


}
