package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.user.jpa.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.AppDataPreFixSerializer;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/11.
 */
@Projection(name = "userprizeloginfo", types = {User.class})
public interface UserPrizeLogProjection {

    int getId();

    int getStatus();

    String getNickName();
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    String getAvatar();


}
