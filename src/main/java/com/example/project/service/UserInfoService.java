package com.example.project.service;


/**
 * @author: Marcel
 * @date: 2021/5/11 16:25
 */

import com.example.project.bean.UserInfo;
import com.example.project.repository.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /*保存用户信息*/
    public void addUser(UserInfo userInfo){
        /*邮箱验证功能还没写*/
        userInfo.setVerity(0);
        /*只有学生开放注册功能*/
        userInfo.setAuthority(10);
        /*打时间戳*/
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        userInfoDao.save(userInfo);
    }

    /*判断用户名、密码，及用户权限*/
    public int judge(String id,String passwd){
        UserInfo userInfo=userInfoDao.getOne(id);
        if(userInfo.getPassword().equals(passwd)){
            return userInfo.getAuthority();
        }else{
            return -1;
        }
    }



}
