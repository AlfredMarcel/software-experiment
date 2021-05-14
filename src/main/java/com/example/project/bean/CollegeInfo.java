package com.example.project.bean;

/**
 * @author: Marcel
 * @date: 2021/5/11 15:59
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
@Table(name="colleges")
@EntityListeners(AuditingEntityListener.class)
public class CollegeInfo {
    /*各键与数据库字段对应*/
    /*不能带下划线，否则JPA解析会出错*/
    @Id
    private String id;
    @Column(length=20)
    private String name;
    @Column
    private int formId;

    /*自动更新时间戳*/
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date modifyTime;
    /*软删除*/
    @Column
    private Date deleteTime;

    public CollegeInfo(String id,String name,int form_id){
        this.id=id;
        this.name=name;
        this.formId=form_id;
    }

    public CollegeInfo() {

    }
}
