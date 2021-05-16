package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/11 16:17
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
/*表名*/
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)

public class UserInfo {
    @Id
    private String sno;

    @Column(length=20)
    private String name;

    @Column(length=20)
    private String hometown;

    @Column(length=20)
    private String idno;

    @Column(length=20)
    private String college;

    @Column(length=40)
    private String password;

    @Column(length=20)
    private String email;

    @Column
    private int verity;

    @Column
    private int authority;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;


    public UserInfo() {

    }

    public UserInfo(String sno,String name){
        this.sno=sno;
        this.name=name;
    }

}
