package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.Experience;
import cn.springlogic.vip.jpa.entity.rest.ExperienceProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;

/**
 * Created by admin on 2017/5/3.
 */

@Configuration
@RepositoryRestResource(path="vip:experience")
public interface ExperienceRepository  extends JpaRepository<Experience,Integer>{

    @RestResource(path = "one",rel = "one")
    Experience findByUserId(@Param("user_id")int userId);

    @Query("SELECT e from Experience e where (:phone IS NULL OR e.user.phone LIKE CONCAT('%',:phone,'%')) AND (:nickName IS NULL OR e.user.nickName LIKE CONCAT('%',:nickName,'%')) AND (:email IS NULL OR e.user.email LIKE CONCAT('%',:email,'%'))")
    Page<Experience> findByUserSth(@Param("phone")String phone,@Param(value = "nickName")String nickname,@Param("email")String email,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Experience e set e.ammount = :ammount  where e.id = :id ")
    void setAmmountById(@Param("id")Integer id,@Param("ammount")Integer ammount);
}
