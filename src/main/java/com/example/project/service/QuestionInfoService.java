package com.example.project.service;

/**
 * @author: Marcel
 * @date: 2021/5/12 13:32
 */


import com.example.project.bean.QuestionInfo;
import com.example.project.repository.QuestionInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionInfoService {

    @Autowired
    private QuestionInfoDao questionInfoDao;

    /*保存问题信息*/
    public void addQuestion(QuestionInfo questionInfo){
        questionInfo.setCreateTime(new Date());
        questionInfo.setModifyTime(new Date());
        questionInfoDao.save(questionInfo);
    }

    /*查询所有问题*/
    public List<QuestionInfo> getAllQuestion(){
        return questionInfoDao.findByDeleteTimeIsNull();
    }

    /*查询所有问题的id与名称*/
    public List<QuestionInfo> getAllSimpleQuestion(){
        return questionInfoDao.findIdNameByDeleteTimeIsNull();
    }

    /*根据id查询单个问题*/
    public QuestionInfo getQuestionById(String id){
        int idd=Integer.parseInt(id);
        return questionInfoDao.getOne(idd);
    }

    /*查询单个问题的高危判定条件*/
    public String getEmergencyFormId(Integer id){
        return questionInfoDao.getOne(id).getEmergency();
    }

    /*更新问题*/
    public void updateQuestion(QuestionInfo questionInfo){
        /*原数据传递到视图层，再回来，不在表单里的元素就没了，这个问题尚未解决*/
        /*目前的解决方法是再查一遍*/

        QuestionInfo tmp=questionInfoDao.getOne(questionInfo.getId());
        tmp.setContent(questionInfo.getContent());
        tmp.setName(questionInfo.getName());
        tmp.setType(questionInfo.getType());
        tmp.setOptions(questionInfo.getOptions());
        tmp.setModifyTime(new Date());
        tmp.setEmergency(questionInfo.getEmergency());

        questionInfoDao.save(tmp);
    }

    /*软删除*/
    public void delQuestion(String id){
        int idd=Integer.parseInt(id);
        QuestionInfo tmp=questionInfoDao.getOne(idd);
        tmp.setDeleteTime(new Date());
        questionInfoDao.save(tmp);
    }
}
