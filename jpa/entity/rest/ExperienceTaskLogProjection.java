package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.blog.jpa.entity.rest.UserProjection;
import cn.springlogic.vip.jpa.entity.ExperienceTaskLog;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by admin on 2017/5/26.
 */
@Projection(name = "experiencetaskloginfo", types = {ExperienceTaskLog.class})
public interface ExperienceTaskLogProjection {

    int getId();

    int getExperience();

    UserProjection getUser();

    ExperienceTaskProjection getExperienceTask();

    Date getPeriodEndTime();

    Date getCreateTime();

}
