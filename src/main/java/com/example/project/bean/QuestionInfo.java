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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data

@Entity
/*表名*/
@Table(name="questions")
@EntityListeners(AuditingEntityListener.class)

public class QuestionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(length=40)
    protected String name;

    @Column(length=255)
    protected String content;

    @Column
    protected Integer type;

    @Column(length=255)
    protected String emergency;

    /*自动更新时间戳*/
    @CreatedDate
    protected Date createTime;
    @LastModifiedDate
    protected Date modifyTime;
    /*软删除*/
    @Column
    protected Date deleteTime;

    @Transient
    /*可能存在的问题的多个选项*/
    List<String> options;

    public QuestionInfo(Integer id,String name,String content,Integer type,String emergency,Date createTime,Date modifyTime){
        this.id=id;
        this.name=name;
        this.content=content;
        this.type=type;
        this.emergency=emergency;
        this.createTime=createTime;
        this.modifyTime=modifyTime;
    }

    public QuestionInfo(){

    }

    public void Parse(){
        options=new ArrayList<>();
        if(this.getType()==1){
            String content=this.getContent();
            String sub=content.substring(content.lastIndexOf("{")+1,content.lastIndexOf("}"));
            sub=sub.replaceAll("，",",");
            String[] tmp=sub.split(",");
            options.addAll(Arrays.asList(tmp));
        }
    }

}
