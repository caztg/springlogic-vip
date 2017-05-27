package cn.springlogic.vip.jpa.entity;

import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/5/3.
 */
@Data
@Entity
@Table(name = "experience_task_log")
public class ExperienceTaskLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int experience;

    @Column(name="create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne(fetch= FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="user_id", // 外键列的列名
            referencedColumnName="id") // 指定引用user表的主键列
    private User user;

    @ManyToOne(fetch= FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=ExperienceTask.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="experience_task_id", // 外键列的列名
            referencedColumnName="id") // 指定引用user表的主键列
    private ExperienceTask experienceTask;

    @Column(name="period_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodEndTime;


}
