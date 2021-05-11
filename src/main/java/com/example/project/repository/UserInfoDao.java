package com.example.project.repository;

/**
 * @author: Marcel
 * @date: 2021/5/11 16:23
 */
import com.example.project.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*将接口的一个实现类交给spring管理*/
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,String> {


}
