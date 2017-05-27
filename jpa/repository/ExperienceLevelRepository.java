package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.ExperienceLevel;
import cn.springlogic.vip.jpa.entity.rest.ExperienceLevelProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */
@Configuration
@RepositoryRestResource(path="vip:experiencelevel",excerptProjection = ExperienceLevelProjection.class)
public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevel,Integer>{


    @RestResource(path = "all",rel = "all")
    @Query("select e from ExperienceLevel e where e.local IS NULL order by e.experienceCondition ASC ")
    Page<ExperienceLevel> findAllOrderByExperienceConditionAsc(Pageable pageable);

    @Query("select e from ExperienceLevel e where e.local IS NULL order by e.experienceCondition ASC ")
    List<ExperienceLevel>findAll();
}
