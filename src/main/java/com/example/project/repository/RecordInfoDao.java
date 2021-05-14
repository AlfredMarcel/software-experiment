package com.example.project.repository;


import com.example.project.bean.RecordInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 15:50
 */
@Repository
public interface RecordInfoDao extends JpaRepository<RecordInfo, Integer> {

    List<RecordInfo> findByDeleteTimeIsNull();

}
