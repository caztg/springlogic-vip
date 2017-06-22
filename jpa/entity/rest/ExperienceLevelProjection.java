package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.Experience;
import cn.springlogic.vip.jpa.entity.ExperienceLevel;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by admin on 2017/5/4.
 */
@Projection(name = "experiencelevelinfo", types = {ExperienceLevel.class})
public interface ExperienceLevelProjection {

   int getId();
   String getName();
   String getLanguage();
   int getExperienceEndCondition();
   int getExperienceCondition();
   List<ExperienceLevelI18nProjection> getI18ns();

}
