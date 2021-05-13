package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/13 13:44
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
@Table(name="forms")
@EntityListeners(AuditingEntityListener.class)

public class FormInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    private String name;

    @Column
    private Integer formSize;

    @Column
    private Integer ques1;

    @Column
    private Integer ques2;

    @Column
    private Integer ques3;

    @Column
    private Integer ques4;

    @Column
    private Integer ques5;

    @Column
    private Integer ques6;

    @Column
    private Integer ques7;

    @Column
    private Integer ques8;

    @Column
    private Integer ques9;

    @Column
    private Integer ques10;

    @Column
    private Integer ques11;

    @Column
    private Integer ques12;

    @Column
    private Integer ques13;

    @Column
    private Integer ques14;

    @Column
    private Integer ques15;

    @Column
    private Integer ques16;

    @Column
    private Integer ques17;

    @Column
    private Integer ques18;

    @Column
    private Integer ques19;

    @Column
    private Integer ques20;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;

    public FormInfo(){

    }
}
