package cn.springlogic.vip.web;

import cn.springlogic.vip.jpa.entity.ExperienceTask;
import cn.springlogic.vip.jpa.entity.ExperienceTaskLog;
import cn.springlogic.vip.jpa.entity.Prize;
import cn.springlogic.vip.jpa.entity.PrizeLog;
import cn.springlogic.vip.jpa.repository.ExperienceTaskLogRepository;
import cn.springlogic.vip.jpa.repository.ExperienceTaskRepository;
import cn.springlogic.vip.jpa.repository.PrizeLogRepository;
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

import java.util.List;

/**
 * Created by admin on 2017/5/4.
 */
@RepositoryRestController
@RequestMapping(value = "vip:experiencetask")
public class ExperienceTaskRestController {

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @Autowired
    private ExperienceTaskRepository experienceTaskRepository;

    @Autowired
    private ExperienceTaskLogRepository experienceTaskLogRepository;


    /**
     * 展示 任务列表 (根据传入的用户 判断当前用户完成状态)
     * @param userId
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/all")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchBySubject3(
            @RequestParam(name = "user_id",required = false,defaultValue = "0") int userId,
            Pageable pageable,
            PersistentEntityResourceAssembler resourceAssembler) {

        Page<ExperienceTask> all = experienceTaskRepository.findAll(pageable);
        if(userId!=0) {
            Page<ExperienceTask> tasksPage = all.map(new ExperienceTaskRestController.ExperienceTasksConverter(experienceTaskLogRepository, userId));

            return ResponseEntity.ok(pagedResourcesAssembler.toResource(tasksPage, resourceAssembler));
        }else{
            return ResponseEntity.ok(pagedResourcesAssembler.toResource(all, resourceAssembler));
        }


    }


    /**
     * 转换器类
     */
    private static final class ExperienceTasksConverter implements Converter<ExperienceTask, ExperienceTask> {

        private final Integer currentUserId;

        private final ExperienceTaskLogRepository experienceTaskLogRepository;

        private ExperienceTasksConverter(ExperienceTaskLogRepository experienceTaskLogRepository, Integer currentUserId) {

            this.currentUserId = currentUserId;
            this.experienceTaskLogRepository = experienceTaskLogRepository;

        }

        @Override
        public ExperienceTask convert(ExperienceTask source) {

            if (null != currentUserId) {
                List<ExperienceTaskLog> experienceTaskLogs = experienceTaskLogRepository.findByUserIdAndExperienceTaskId(currentUserId, source.getId());
                 source.setPeriodExperienceTaskLogsTotal(experienceTaskLogs.size());
            }

            return source;
        }
    }


}
