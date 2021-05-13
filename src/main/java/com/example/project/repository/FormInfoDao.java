package com.example.project.repository;

/**
 * @author: Marcel
 * @date: 2021/5/13 13:48
 */


import com.example.project.bean.FormInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*将接口的一个实现类交给spring管理*/
@Repository
public interface FormInfoDao extends JpaRepository<FormInfo, Integer> {

    List<FormInfo> findByDeleteTimeIsNull();

    List<FormInfo> findIdNameCreateTimeModifyTimeByDeleteTimeIsNull();


}

