package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.PrizeLog;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by admin on 2017/5/11.
 */
@Projection(name = "prizeloginfo", types = {PrizeLog.class})
public interface PrizeLogInfoProjection {

    int getId();

    Date getCreateTime();

    UserPrizeLogProjection getUser();

    PrizePrizeLogProjection getPrize();

    ExpressAddressPrizeLogProjection getExpressAddress();

    String getStatus();

}
