package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.ExperienceLevel;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/11.
 */
@Projection(name = "experiencelevelprizeloginfo", types = {ExperienceLevel.class})
public interface ExperienceLevelPrizeLogProjection {
    int getId();
    String getName();
    int getExperienceCondition();
}
