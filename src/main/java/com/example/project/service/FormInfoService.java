package com.example.project.service;

import com.example.project.bean.FormInfo;
import com.example.project.repository.FormInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/13 13:50
 */

@Service
public class FormInfoService {

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
        formInfo.setCreateTime(new Date());
        formInfo.setModifyTime(new Date());
        formInfoDao.save(formInfo);
    }

    /*软删除*/
    public void delForm(String id) {
        int idd=Integer.parseInt(id);
        FormInfo tmp=formInfoDao.getOne(idd);
        tmp.setDeleteTime(new Date());
        formInfoDao.save(tmp);
    }
}
