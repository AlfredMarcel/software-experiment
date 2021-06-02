package com.example.project.controller;

import com.example.project.bean.UserInfo;
import com.example.project.bean.UserStatusInfo;
import com.example.project.service.UserInfoService;
import com.example.project.service.UserStatusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:29
 */

@Controller
public class UserStatusInfoController {

    @Autowired
    private UserStatusInfoService userStatusInfoService;

    @Autowired
    private UserInfoService userInfoService;

    /*查看历史打卡信息*/
    @GetMapping("/teacher/history")
    public String recordTeaHistory(HttpServletRequest request, Model model){
        getHistory(request,model);
        return "/teacher/histories/history";
    }

    @GetMapping("/student/history")
    public String recordStuHistory(HttpServletRequest request, Model model){
        getHistory(request,model);
        return "/student/histories/history";
    }


    /*教师：查看同学院学生的当日打卡信息,并分类展示*/
    @GetMapping("/teacher/result")
    public String getResult(HttpServletRequest request,Model model){

        List<UserInfo>healthy_user=new ArrayList<>();
        List<UserInfo>emergency_user=new ArrayList<>();
        List<UserInfo>absent_user=new ArrayList<>();

        Calendar now=Calendar.getInstance();
        Calendar prev=Calendar.getInstance();
        now.setTime(new Date());
        HttpSession session=request.getSession();
        /*从session里拿到学院号*/
        String college_id= String.valueOf(session.getAttribute("user_college"));
        /*属于该学院的所有用户*/
        List<UserInfo> user_ids=userInfoService.findAllUserByCollegeId(college_id);

        for(UserInfo userInfo:user_ids){
            List<UserStatusInfo> userStatusInfo=userStatusInfoService.findDataByUserId(userInfo.getSno());
            //没拿到打卡数据，说明一直没打卡
            if(userStatusInfo.size()==0){
                absent_user.add(userInfo);
            }else{
                UserStatusInfo tmp=userStatusInfo.get(userStatusInfo.size()-1);
                prev.setTime(tmp.getDate());
                boolean sameDay=now.get(Calendar.YEAR)==prev.get(Calendar.YEAR)&&now.get(Calendar.DAY_OF_YEAR)==prev.get(Calendar.DAY_OF_YEAR);
                //有数据但不是今天的，没打卡
                if(!sameDay){
                    absent_user.add(userInfo);
                }else{
                    //今天有打卡数据，那看看是否高危
                    //提交时间暂存到user对象里，一起写到前端展示
                    userInfo.setCreateTime(tmp.getDate());
                    if(tmp.getConditions()==0){
                        healthy_user.add(userInfo);
                    }else{
                        emergency_user.add(userInfo);
                    }
                }
            }

        }

        model.addAttribute("healthy_user",healthy_user);
        model.addAttribute("emergency_user",emergency_user);
        model.addAttribute("absent_user",absent_user);

        return "/teacher/dailys/student_results";

    }

    private void getHistory(HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        List<UserStatusInfo> userStatusInfoList=userStatusInfoService.findDataByUserId(String.valueOf(session.getAttribute("user_id")));
        model.addAttribute("results",userStatusInfoList);
    }
}
