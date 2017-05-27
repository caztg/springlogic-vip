package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.address.jpa.entity.Address;
import cn.springlogic.address.jpa.entity.rest.AddressProjection;
import cn.springlogic.user.jpa.entity.ExpressAddress;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/11.
 */
@Projection(name = "expressaddressinfo", types = {ExpressAddress.class})
public interface ExpressAddressPrizeLogProjection {

    int getId();

    AddressProjection getAddress();
}
