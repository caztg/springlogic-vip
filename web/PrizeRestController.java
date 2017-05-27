package cn.springlogic.vip.web;

import cn.springlogic.vip.jpa.entity.Prize;
import cn.springlogic.vip.jpa.entity.PrizeLog;
import cn.springlogic.vip.jpa.repository.PrizeLogRepository;
import cn.springlogic.vip.jpa.repository.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/5/4.
 */
@RepositoryRestController
@RequestMapping(value = "vip:prize")
public class PrizeRestController {

    @Autowired
    private PrizeRepository prizeRepository;

    @Autowired
    private PrizeLogRepository prizeLogRepository;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;


   /*
    展示奖品列表(根据当前用户id设置领取状态)
    */

    @ResponseBody
    @GetMapping(value = "/search/all")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchBySubject3(
                                                                                           @RequestParam(name = "user_id",required = false,defaultValue = "0") int userId,
                                                                                           Pageable pageable,
                                                                                           PersistentEntityResourceAssembler resourceAssembler) {

        Page<Prize> all = prizeRepository.findAll(pageable);
        if(userId!=0) {
            Page<Prize> prizesPage = all.map(new PrizesConverter(prizeLogRepository, userId));

            return ResponseEntity.ok(pagedResourcesAssembler.toResource(prizesPage, resourceAssembler));
        }else{
            return ResponseEntity.ok(pagedResourcesAssembler.toResource(all, resourceAssembler));
        }


    }


    /**
     * 转换器类
     */
    private static final class PrizesConverter implements Converter<Prize, Prize> {

        private final Integer currentUserId;

        private final PrizeLogRepository prizeLogRepository;

        private PrizesConverter(PrizeLogRepository prizeLogRepository, Integer currentUserId) {

            this.currentUserId = currentUserId;
            this.prizeLogRepository = prizeLogRepository;

        }

        @Override
        public Prize convert(Prize source) {



            if (null != currentUserId) {
                //处理领取状态
                PrizeLog tempPrizeLog = prizeLogRepository.findByUserId(currentUserId);
                if(tempPrizeLog!=null){
                    if (tempPrizeLog.getPrize().getId()==source.getId()){
                        //说明该奖品 当前用户已经领取了,设置领取对象
                        source.setPrizeLog(tempPrizeLog);
                    }
                }


            }


            return source;
        }
    }

}
