package com.example.project.repository;

/**
 * @author: Marcel
 * @date: 2021/5/12 13:29
 */

import com.example.project.bean.QuestionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*将接口的一个实现类交给spring管理*/
@Repository
public interface QuestionInfoDao extends JpaRepository<QuestionInfo, Integer> {

    List<QuestionInfo> findByDeleteTimeIsNull();

    List<QuestionInfo> findIdNameByDeleteTimeIsNull();

    String findEmergencyById(int id);

}