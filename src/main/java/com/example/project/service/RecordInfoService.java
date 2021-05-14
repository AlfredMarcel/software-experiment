package com.example.project.service;

import com.example.project.bean.RecordInfo;
import com.example.project.repository.RecordInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 15:51
 */

@Service
public class RecordInfoService {

    @Autowired
    private RecordInfoDao recordInfoDao;

    /*添加填报记录*/
    public void addRecord(RecordInfo recordInfo) {
        recordInfo.setCreateTime(new Date());
        recordInfo.setModifyTime(new Date());
        recordInfoDao.save(recordInfo);
    }

    /*查看历史打卡信息*/
    public List<RecordInfo> getHistory(String user_id) {
        return recordInfoDao.findCreateTimeByUserId(user_id);
    }
}
