package cn.springlogic.vip.jpa.repository;

import cn.springlogic.vip.jpa.entity.ExperienceTaskLog;
import cn.springlogic.vip.jpa.entity.rest.ExperienceTaskLogProjection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by admin on 2017/5/4.
 */
@Configuration
@RepositoryRestResource(path="vip:experiencetasklog",excerptProjection = ExperienceTaskLogProjection.class)
public interface ExperienceTaskLogRepository extends JpaRepository<ExperienceTaskLog,Integer> {


    /**
     *    //1276650817   是2010年6月16日 9:13:37
            //1276674758   是2010年6月16日 15:52:38
     * 根据用户id 与 任务id 找出    当前时间 小于  截止时间 的任务记录
     * @param userId
     * @param taskId
     * @return
     */

    @Query("select l from ExperienceTaskLog l where l.user.id=:id and l.experienceTask.id=:tid and current_timestamp() < l.periodEndTime ")
    List<ExperienceTaskLog> findByUserIdAndExperienceTaskId(@Param("id")int userId ,@Param("tid")int taskId);
}
