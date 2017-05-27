package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.PrizeLog;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/5/11.
 */
@Component
@RepositoryEventHandler(PrizeLog.class)
public class PrizeLogEventHandler {

    @HandleBeforeCreate
    public void beforeCreate(PrizeLog prizeLog){

        prizeLog.setStatus(PrizeLog.PRIZELOG_STATUS_UNDEAL);

    }
}
