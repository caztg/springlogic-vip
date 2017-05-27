package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.Experience;
import cn.springlogic.vip.jpa.entity.ExperienceTask;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/30.
 */
@Projection(name = "experiencetaskinfo", types = {ExperienceTask.class})
public interface ExperienceTaskProjection {

   int getId();

   String getName();

   int getExperience();

   String getDescription();

   int getLimitPeriod();

   int getLimitTimes();
}
