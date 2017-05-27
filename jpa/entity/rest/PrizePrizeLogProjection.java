package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.Prize;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/11.
 */
@Projection(name = "prizeprizeloginfo", types = {Prize.class})
public interface PrizePrizeLogProjection {

    int getId();

    String getTitle();

    ExperienceLevelPrizeLogProjection getExperienceLevel();

}
