package cn.springlogic.vip.jpa.entity;

import cn.springlogic.social.jpa.entity.Follow;
import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by admin on 2017/5/3.
 */
@Data
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*经验值*/
    private int ammount;

    @OneToOne(fetch = FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity = User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name = "user_id", // 外键列的列名
            referencedColumnName = "id") // 指定引用user表的主键列
    private User user;

    @Column(name = "create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    private ExperienceLevel experienceLevel;

    @Transient
    private Follow follow;
}
