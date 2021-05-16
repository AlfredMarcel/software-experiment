package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:22
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data

/*实体注解，表明该类对应一个数据库表*/
@Entity
/*表名*/
@Table(name="user_status")
@EntityListeners(AuditingEntityListener.class)
public class UserStatusInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String userId;

    @Column(length=20)
    private String collegeId;

    @Column
    private Integer recordId;

    @Column
    private Date date;

    @Column
    private Integer conditions;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;

    public UserStatusInfo(String userId,String collegeId,Integer recordId){
        this.userId=userId;
        this.collegeId=collegeId;
        this.recordId=recordId;
    }

    public UserStatusInfo() {

    }
}
