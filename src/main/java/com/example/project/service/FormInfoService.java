package com.example.project.service;

import com.example.project.bean.FormInfo;
import com.example.project.bean.QuestionInfo;
import com.example.project.repository.FormInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/13 13:50
 */

@Service
public class FormInfoService {

    @Autowired
    private QuestionInfoService questionInfoService;

    @Autowired
    private FormInfoDao formInfoDao;

    /*保存问卷信息*/
    public void addForm(FormInfo formInfo){
        formInfo.setCreateTime(new Date());
        formInfo.setModifyTime(new Date());
        formInfoDao.save(formInfo);
    }

    /*获取展示的问卷信息（简短）*/
    public List<FormInfo> getAllSimpleForm(){
        return formInfoDao.findIdNameCreateTimeModifyTimeByDeleteTimeIsNull();
    }

    /*获取单个问卷的全部字段信息*/
    public FormInfo getFormById(String id) {
        int idd=Integer.parseInt(id);
        return formInfoDao.getOne(idd);
    }

    /*更新问卷内容*/
    public void updateForm(FormInfo formInfo) {
        FormInfo tmp=formInfoDao.getOne(formInfo.getId());
        tmp.setName(formInfo.getName());
        tmp.setFormSize(formInfo.getFormSize());
        tmp.setQues1(formInfo.getQues1());
        tmp.setQues2(formInfo.getQues2());
        tmp.setQues3(formInfo.getQues3());
        tmp.setQues4(formInfo.getQues4());
        tmp.setQues5(formInfo.getQues5());
        tmp.setQues6(formInfo.getQues6());
        tmp.setQues7(formInfo.getQues7());
        tmp.setQues8(formInfo.getQues8());
        tmp.setQues9(formInfo.getQues9());
        tmp.setQues10(formInfo.getQues10());
        tmp.setQues11(formInfo.getQues11());
        tmp.setQues12(formInfo.getQues12());
        tmp.setQues13(formInfo.getQues13());
        tmp.setQues14(formInfo.getQues14());
        tmp.setQues15(formInfo.getQues15());
        tmp.setQues16(formInfo.getQues16());
        tmp.setQues17(formInfo.getQues17());
        tmp.setQues18(formInfo.getQues18());
        tmp.setQues19(formInfo.getQues19());
        tmp.setQues20(formInfo.getQues20());

        tmp.setModifyTime(new Date());

        formInfoDao.save(tmp);
    }

    /*软删除*/
    public void delForm(String id) {
        int idd=Integer.parseInt(id);
        FormInfo tmp=formInfoDao.getOne(idd);
        tmp.setDeleteTime(new Date());
        formInfoDao.save(tmp);
    }

    /*将问卷解析成问题列表*/
    public List<QuestionInfo> parseFormToQuestions(FormInfo formById) {
        List<QuestionInfo> res= new ArrayList<>();
        List<Integer> index = handleQuestionsId(formById);
        for (Integer integer : index) {
            QuestionInfo tmp=questionInfoService.getQuestionById(String.valueOf(integer));
            tmp.Parse();
            res.add(tmp);
        }
        return res;
    }

    /* 解析出一条问卷中的所有问题编号 */
    private List<Integer> handleQuestionsId(FormInfo formById) {
        List<Integer> res=new ArrayList<Integer>();
        int cnt=0;
        int size=formById.getFormSize();
        Boolean flag=true;
        while(flag){
            if(cnt==size)break;
            res.add(formById.getQues1());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues2());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues3());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues4());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues5());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues6());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues7());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues8());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues9());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues10());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues11());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues12());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues13());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues14());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues15());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues16());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues17());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues18());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues19());
            cnt++;
            if(cnt==size)break;
            res.add(formById.getQues20());
            cnt++;
        }
        return res;
    }
}
