package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.PrizeLog;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by admin on 2017/5/3.
 */
@Configuration
@RepositoryRestResource(path="vip:prizelog")
public interface PrizeLogRepository extends JpaRepository<PrizeLog,Integer>{

    @RestResource(path = "one",rel = "one")
    PrizeLog findByUserId(@Param("userId")int userId);

    @RestResource(path = "all",rel = "all")
    @Query("select distinct pl from PrizeLog pl where (:nickName IS NULL OR pl.user.nickName  LIKE CONCAT('%',:nickName,'%')) AND (:prizeName IS NULL OR pl.prize.title LIKE CONCAT('%',:prizeName,'%')) AND (:status IS NULL OR pl.status=:status) AND (:levelId IS NULL OR pl.prize.experienceLevel.id=:levelId)")
    Page<PrizeLog> findByAll(@Param("nickName")String nickName,@Param("prizeName")String prizeName,@Param("status")Integer status,@Param("levelId")Integer levelId, Pageable pageable);
}
