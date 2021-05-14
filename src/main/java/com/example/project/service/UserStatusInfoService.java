package com.example.project.service;

import com.example.project.bean.RecordInfo;
import com.example.project.repository.UserStatusInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:28
 */

@Service
public class UserStatusInfoService {

    @Autowired
    private UserStatusInfoDao userStatusInfoDao;

    /*判定打卡表单，将结果写入status表*/
    public void judgeRecord(RecordInfo recordInfo) {

    }
}
