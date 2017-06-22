package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.vip.jpa.entity.PrizeLog;
import cn.springlogic.vip.jpa.repository.PrizeLogRepository;
import com.fitcooker.app.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/5/11.
 */
@Component
@RepositoryEventHandler(PrizeLog.class)
public class PrizeLogEventHandler {

    @Autowired
    private PrizeLogRepository prizeLogRepository;

    @HandleBeforeCreate
    public void beforeCreate(PrizeLog prizeLog) throws BussinessException {

        PrizeLog byUserIdAndPrizeId = prizeLogRepository.findByUserIdAndPrizeId(prizeLog.getUser().getId(), prizeLog.getPrize().getId());

        if(byUserIdAndPrizeId!=null){
            throw new BussinessException("您已经领取过该奖品了");
        }

        prizeLog.setStatus(PrizeLog.PRIZELOG_STATUS_UNDEAL);

    }
}
