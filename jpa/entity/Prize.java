package cn.springlogic.vip.jpa.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.serializer.AppDataPreFixSerializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/5/3.
 */
@Data
@Entity
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String description;
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    private String image;

    private String worth;

    @ManyToOne(fetch=FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=ExperienceLevel.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="experience_level_id", // 外键列的列名
            referencedColumnName="id") // 指定引用user表的主键列
    private ExperienceLevel experienceLevel;

    @Transient
    private PrizeLog prizeLog;

    @Column(name="create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name="update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
