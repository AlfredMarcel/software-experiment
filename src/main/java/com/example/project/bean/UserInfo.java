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
    private String Sno;

    @Column(length=20)
    private String Name;

    @Column(length=20)
    private String Hometown;

    @Column(length=20)
    private String Idno;

    @Column(length=20)
    private String College;

    @Column(length=40)
    private String Password;

    @Column(length=20)
    private String Email;

    @Column
    private int Verity;

    @Column
    private int Authority;

    /*自动更新时间戳*/
    @CreatedDate
    private Date CreateTime;
    @LastModifiedDate
    private Date ModifyTime;
    /*软删除*/
    @Column
    private Date DeleteTime;


    public UserInfo() {

    }
}
