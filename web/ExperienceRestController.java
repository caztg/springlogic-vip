package cn.springlogic.vip.web;

import cn.springlogic.blog.web.BlogRestController;
import cn.springlogic.oauth2.DiyUser;
import cn.springlogic.social.jpa.entity.Follow;
import cn.springlogic.social.jpa.entity.Publication;
import cn.springlogic.social.jpa.repository.FollowRepository;
import cn.springlogic.vip.jpa.entity.Experience;
import cn.springlogic.vip.jpa.entity.ExperienceLevel;
import cn.springlogic.vip.jpa.entity.ExperienceTask;
import cn.springlogic.vip.jpa.entity.ExperienceTaskLog;
import cn.springlogic.vip.jpa.repository.ExperienceLevelRepository;
import cn.springlogic.vip.jpa.repository.ExperienceRepository;
import cn.springlogic.vip.jpa.repository.ExperienceTaskLogRepository;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/5/24.
 */
@RepositoryRestController
@RequestMapping(value = "vip:experience")
public class ExperienceRestController {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @Autowired
    private ExperienceLevelRepository experienceLevelRepository;
    @Autowired
    private FollowRepository followRepository;

    /**
     * 展示 一个用户的信息(包含 等级信息,经验信息)
     * @param userId
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/one")
    public ResponseEntity<PersistentEntityResource> SearchBy(
            @RequestParam(name = "user_id") Integer userId,
            PersistentEntityResourceAssembler resourceAssembler) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DiyUser diyUser=null;
        if(!principal.equals("anonymousUser")) {
            diyUser = (DiyUser) principal;
        }
        Experience experience = experienceRepository.findByUserId(userId);
        List<ExperienceLevel> experienceLevels = experienceLevelRepository.findAll();

        Converter<Experience,Experience> converter = new ExperienceRestController.ExperienceConverter(experienceLevels);
        experience=converter.convert(experience);
        if(diyUser!=null) {
            Follow byuserIdAndFollowUserId = followRepository.findByuserIdAndFollowUserId(diyUser.getUserId(), userId);
            if (byuserIdAndFollowUserId != null) {
                experience.setFollow(byuserIdAndFollowUserId);
            }
        }
            return ResponseEntity.ok(resourceAssembler.toResource( experience));

    }

    /**
     * 展示用户列表信息 (包含经验信息,等级信息)
     * @param nickName
     * @param phone
     * @param email
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/all")
    public ResponseEntity<PagedResources<PersistentEntityResource>> SearchBy(@RequestParam(name = "nickName", required = false) String nickName,
                                                                             @RequestParam(name = "phone", required = false) String phone,
                                                                             @RequestParam(name = "email", required = false) String email,
            Pageable pageable,
            PersistentEntityResourceAssembler resourceAssembler) {

        Page<Experience> all = experienceRepository.findByUserSth(phone,nickName,email,pageable);
        List<ExperienceLevel> experienceLevels = experienceLevelRepository.findAll();

        Page<Experience> tasksPage = all.map(new ExperienceRestController.ExperienceConverter(experienceLevels));

            return ResponseEntity.ok(pagedResourcesAssembler.toResource(tasksPage, resourceAssembler));

    }


    /**
     * 转换器类
     */
    private static final class ExperienceConverter implements Converter<Experience, Experience> {

        private final List<ExperienceLevel> experienceLevels;



        private ExperienceConverter(List<ExperienceLevel> experienceLevels) {

            this.experienceLevels = experienceLevels;

        }

        @Override
        public Experience convert(Experience source) {

            for (ExperienceLevel level: experienceLevels) {
                if(source.getAmmount()>=level.getExperienceCondition()){
                    source.setExperienceLevel(level);
                }
            }

            return source;
        }
    }


}
