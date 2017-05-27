package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.Prize;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by admin on 2017/5/3.
 */
@Configuration
@RepositoryRestResource(path="vip:prize")
public interface PrizeRepository extends JpaRepository<Prize,Integer>{
}
