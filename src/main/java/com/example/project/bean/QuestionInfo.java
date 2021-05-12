package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/11 20:14
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
@Table(name="questions")
@EntityListeners(AuditingEntityListener.class)

public class QuestionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=40)
    private String name;

    @Column(length=255)
    private String content;

    @Column
    private Integer type;

    @Column(length=255)
    private String emergency;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;

    public QuestionInfo(){

    }

}
