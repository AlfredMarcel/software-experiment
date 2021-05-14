package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/14 15:43
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
@Table(name="records")
@EntityListeners(AuditingEntityListener.class)

public class RecordInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String userId;

    @Column
    private Integer formId;

    @Column
    private String answer1;

    @Column
    private String answer2;

    @Column
    private String answer3;

    @Column
    private String answer4;

    @Column
    private String answer5;

    @Column
    private String answer6;

    @Column
    private String answer7;

    @Column
    private String answer8;

    @Column
    private String answer9;

    @Column
    private String answer10;

    @Column
    private String answer11;

    @Column
    private String answer12;

    @Column
    private String answer13;

    @Column
    private String answer14;

    @Column
    private String answer15;

    @Column
    private String answer16;

    @Column
    private String answer17;

    @Column
    private String answer18;

    @Column
    private String answer19;

    @Column
    private String answer20;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;

    public RecordInfo(){

    }

    public RecordInfo(String sno,Integer id){
        this.userId=sno;
        this.formId=id;
    }


}
