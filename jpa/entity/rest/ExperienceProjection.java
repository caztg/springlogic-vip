package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.blog.jpa.entity.rest.UserProjection;
import cn.springlogic.social.jpa.entity.PublicationComment;
import cn.springlogic.vip.jpa.entity.Experience;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/4.
 */
@Projection(name = "experienceinfo", types = {Experience.class})
public interface ExperienceProjection {
    int getId();
    int getAmmount();
    UserProjection getUser();
    ExperienceLevelProjection getExperienceLevel();
}
