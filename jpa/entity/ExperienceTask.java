package cn.springlogic.vip.jpa.entity;

import cn.springlogic.social.jpa.entity.PublicationComment;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */
@Data
@Entity
@Table(name = "experience_task")
public class ExperienceTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;
    /*完成任务可获得积分(经验)*/
    private int experience;
    /*完成周期，单位：天*/
    @Column(name = "limit_period")
    private int limitPeriod;
    /*周期内最多可完成次数*/
    @Column(name = "limit_times")
    private int limitTimes;

    /*1启用,2不启用*/
    private int status;

    @Column(name="create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name="update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private String language;


    @ManyToOne
    @JoinColumn(name = "local_id")
    private ExperienceTask local;

    @OneToMany(mappedBy = "local")
    private List<ExperienceTask> i18ns = new ArrayList<>();

/*
    @OneToMany(fetch = FetchType.EAGER,
            targetEntity = ExperienceTaskLog.class,
            mappedBy = "experienceTask")
    @Fetch(FetchMode.SUBSELECT)
    private List<ExperienceTaskLog> periodExperienceTaskLogs=new ArrayList<>();
    */

    @Transient
    private int periodExperienceTaskLogsTotal;
}
