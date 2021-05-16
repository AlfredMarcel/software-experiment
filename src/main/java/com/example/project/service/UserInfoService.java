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

    /*通过注册保存用户信息*/
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

    /*查找用户*/
    public UserInfo findUser(String id){
        return userInfoDao.getOne(id);
    }

    /*查找所有用户 工号、姓名、学院、身份编号 */
    public List<UserInfo> getAllUser(){
        return userInfoDao.findSnoNameCollegeAuthorityByDeleteTimeIsNull();
    }

    /*查找用户的学院id*/
    public String findCollegeByUser(String id){
        UserInfo userInfo=userInfoDao.getOne(id);
        return userInfo.getCollege();
    }

    /*管理员在后台添加学生*/
    public void addStu(UserInfo userInfo){
        /*默认密码：123456*/
        userInfo.setPassword("123456");
        userInfo.setVerity(0);
        /*身份为学生*/
        userInfo.setAuthority(10);
        /*打时间戳*/
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        userInfoDao.save(userInfo);
    }

    /*管理员在后台添加老师*/
    public void addTea(UserInfo userInfo) {
        /*默认密码：123456*/
        userInfo.setPassword("123456");
        userInfo.setVerity(0);
        /*身份为教师*/
        userInfo.setAuthority(20);
        /*打时间戳*/
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        userInfoDao.save(userInfo);
    }

    /*更新用户信息*/
    public void updateUser(UserInfo userInfo) {
        UserInfo tmp=userInfoDao.getOne(userInfo.getSno());
        tmp.setName(userInfo.getName());
        tmp.setCollege(userInfo.getCollege());
        tmp.setModifyTime(new Date());
        userInfoDao.save(tmp);
    }

    /*软删除*/
    public void delUser(String id){
        UserInfo tmp=userInfoDao.getOne(id);
        tmp.setDeleteTime(new Date());
        userInfoDao.save(tmp);
    }

    /*重置密码*/
    public void resetPasswd(String id) {
        UserInfo tmp=userInfoDao.getOne(id);
        tmp.setPassword("123456");
        tmp.setModifyTime(new Date());
        userInfoDao.save(tmp);
    }
}
