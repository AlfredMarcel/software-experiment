package com.example.project.service;

import com.example.project.bean.FormInfo;
import com.example.project.bean.RecordInfo;
import com.example.project.bean.UserStatusInfo;
import com.example.project.repository.UserStatusInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:28
 */

@Service
public class UserStatusInfoService {

    @Autowired
    private UserStatusInfoDao userStatusInfoDao;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FormInfoService formInfoService;

    @Autowired
    private QuestionInfoService questionInfoService;

    /*判定打卡表单，将结果写入status表*/
    public void judgeRecord(RecordInfo recordInfo) {
        String college_id=userInfoService.findCollegeByUser(recordInfo.getUserId());
        UserStatusInfo userStatusInfo=new UserStatusInfo(recordInfo.getUserId(),college_id,recordInfo.getId());
        userStatusInfo.setDate(new Date());
        /*对提交信息进行高危判定*/
        userStatusInfo.setConditions(judge(recordInfo));
        userStatusInfo.setCreateTime(new Date());
        userStatusInfo.setModifyTime(new Date());
        userStatusInfoDao.save(userStatusInfo);
    }

    /*高危判定*/
    /*高危置1，正常置0*/
    private Integer judge(RecordInfo recordInfo) {
        Integer formId=recordInfo.getFormId();
        FormInfo formInfo=formInfoService.getFormById(String.valueOf(formId));
        Integer size=formInfo.getFormSize();

        List<String>answer=new ArrayList<String>();
        List<String>emergency=new ArrayList<String>();

        Boolean flag=true;
        int cnt=0;

        while(flag){
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer1());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues1()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer2());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues2()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer3());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues3()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer4());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues4()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer5());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues5()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer6());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues6()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer7());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues7()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer8());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues8()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer9());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues9()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer10());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues10()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer11());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues11()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer12());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues12()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer13());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues13()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer14());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues14()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer15());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues15()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer16());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues16()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer17());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues17()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer18());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues18()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer19());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues19()));
            cnt++;
            if(cnt==size)break;
            answer.add(recordInfo.getAnswer20());
            emergency.add(questionInfoService.getEmergencyFormId(formInfo.getQues20()));
            cnt++;
        }

        Integer judge_result=0;
        for(int i=0;i<size;i++){
            if(!emergency.get(i).equals("")){
                if(judge_each(answer.get(i),emergency.get(i))){
                    judge_result=1;
                    break;
                }
            }
        }
        return judge_result;
    }

    /*单个元素的高危判定*/
    /*高危置1，正常置0*/
    private boolean judge_each(String answer, String emergency) {
        String sub=emergency.substring(emergency.lastIndexOf("{")+1,emergency.lastIndexOf("}"));
        sub=sub.replaceAll("，",",");
        String[] tmp=sub.split(",");

        /*答案是否在高危判定条件中*/
        for(String s:tmp){
            if(s.equals(answer)){
                return true;
            }
        }
        return false;
    }


}
