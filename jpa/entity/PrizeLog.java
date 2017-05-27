package cn.springlogic.vip.jpa.entity;

import cn.springlogic.user.jpa.entity.ExpressAddress;
import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by admin on 2017/5/3.
 */
@Data
@Entity
public class PrizeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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
            targetEntity=Prize.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="prize_id", // 外键列的列名
            referencedColumnName="id") // 指定引用user表的主键列
    private Prize prize;


    @ManyToOne(fetch= FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity=ExpressAddress.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name="express_address_id", // 外键列的列名
           referencedColumnName="id") // 指定引用user表的主键列
    @NotNull
    private ExpressAddress expressAddress;

    //处理状态 1 待处理 ,  2  已发货
    private int status;

    public static final int PRIZELOG_STATUS_UNDEAL=1;
    public static final int PRIZELOG_STATUS_DEAL=2;

}
