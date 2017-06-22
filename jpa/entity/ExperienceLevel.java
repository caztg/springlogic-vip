package cn.springlogic.vip.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */

@Data
@Entity
@Table(name = "experience_level")
public class ExperienceLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "experience_end_condition")
    private int experienceEndCondition;

    /*等级条件*/
    @Column(name = "experience_condition")
    private int experienceCondition;

    private String name;

    private String language;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private ExperienceLevel local;

    @OneToMany(mappedBy = "local")
    private List<ExperienceLevel> i18ns = new ArrayList<>();
}
