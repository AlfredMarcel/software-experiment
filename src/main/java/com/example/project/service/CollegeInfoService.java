package com.example.project.service;

/**
 * @author: Marcel
 * @date: 2021/5/11 16:07
 */

import com.example.project.bean.CollegeInfo;
import com.example.project.repository.CollegeInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollegeInfoService {

    @Autowired
    private CollegeInfoDao collegeInfoDao;

    /*存学院到数据库*/
    /*save 返回值是对象*/
    public void addCollege(CollegeInfo collegeInfo){
        collegeInfoDao.save(collegeInfo);
    }

    /*查询全体学院*/
    public List<CollegeInfo> getAllCollege(){
        return collegeInfoDao.findByDeleteTimeIsNull();
    }

    /*通过id查到学院*/
    public CollegeInfo getCollegeById(String id){
        return collegeInfoDao.getOne(id);
    }

    /*如果主键是自增的ID，可能要在repository里自定义update方法，写sql语句的形式*/
    /*更新，merge的形式，主键不变情况下原地修改*/
    public void updateCollege(CollegeInfo collegeInfo){
        collegeInfo.setModifyTime(new Date());
        collegeInfoDao.save(collegeInfo);
    }

    /*硬删除*/
    /*public void delCollege(String id){
        collegeInfoDao.deleteById(id);
    }*/

    /*软删除*/
    public void delCollege(String id){
        CollegeInfo temp=collegeInfoDao.getOne(id);
        temp.setDeleteTime(new Date());
        collegeInfoDao.save(temp);
    }

}
