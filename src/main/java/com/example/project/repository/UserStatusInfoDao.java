package com.example.project.repository;


import com.example.project.bean.UserStatusInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:26
 */
@Repository
public interface UserStatusInfoDao extends JpaRepository<UserStatusInfo,String> {


}
