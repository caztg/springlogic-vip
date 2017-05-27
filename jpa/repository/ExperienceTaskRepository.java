package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.ExperienceTask;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by admin on 2017/5/3.
 */
@Configuration
@RepositoryRestResource(path="vip:experiencetask")
public interface ExperienceTaskRepository extends JpaRepository<ExperienceTask,Integer>{

    @Query("select t from ExperienceTask t where t.local IS NULL")
    Page<ExperienceTask> findAll(Pageable pageable);
}
