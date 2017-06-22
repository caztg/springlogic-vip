package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.Prize;
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
@RepositoryRestResource(path="vip:prize")
public interface PrizeRepository extends JpaRepository<Prize,Integer>{

    @Query("select p  from Prize p order by p.experienceLevel.experienceCondition asc ")
    Page<Prize> findAll(Pageable pageable);
}
