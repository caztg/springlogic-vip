package cn.springlogic.vip.jpa.entity.rest;

import cn.springlogic.user.jpa.entity.User;
import cn.springlogic.vip.jpa.entity.Experience;
import cn.springlogic.vip.jpa.entity.ExperienceTaskLog;
import cn.springlogic.vip.jpa.repository.ExperienceRepository;
import cn.springlogic.vip.jpa.repository.ExperienceTaskLogRepository;
import com.fitcooker.app.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/5/4.
 */


@Component
@RepositoryEventHandler(ExperienceTaskLog.class)
public class ExperienceTaskLogEventHandler {

    @Autowired
    private ExperienceTaskLogRepository experienceTaskLogRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @HandleBeforeCreate
    public void beforeCreate(ExperienceTaskLog experienceTaskLog) throws BussinessException {
        Experience exp =null;
         exp = experienceRepository.findByUserId(experienceTaskLog.getUser().getId());
        if (exp == null) {
            //用户经验表不存在.
            Experience e = new Experience();
            e.setUser(experienceTaskLog.getUser());
            e.setAmmount(0);
            exp = experienceRepository.save(e);//创建该经验表
        }

        //根据用户id 以及 任务id \当前时间 查找 experienceTasklog记录
        //1276650817   是2010年6月16日 9:13:37
        //1276674758   是2010年6月16日 15:52:38

        List<ExperienceTaskLog> tempList = experienceTaskLogRepository.findByUserIdAndExperienceTaskId(experienceTaskLog.getUser().getId(), experienceTaskLog.getExperienceTask().getId());

        if (tempList.size() > 0) {
            //说明 在该周期内找到了该 task
            experienceTaskLog.setPeriodEndTime(tempList.get(0).getPeriodEndTime());


            //判断在该周期内完成次数   limitPeriod 完成周期，单位：天  周期内最多可完成次数 limitTimes

            ExperienceTaskLog tempetl = tempList.get(0);
            //int limitPeriodDay = tempetl.getExperienceTask().getLimitPeriod();
            int limitTimes = tempetl.getExperienceTask().getLimitTimes();

            if (tempList.size() >= limitTimes) {
                //查找出来的list.size()代表该周期内的完成次数,如果完成次数大于等于 limitTimes 则不用记录
                //experienceTaskLogRepository.delete(experienceTaskLog.getId());
                throw new BussinessException("你已达到当前任务完成次数");
            }
            if (exp != null) {
                //修改经验值
                experienceRepository.setAmmountById(exp.getId(), exp.getAmmount() + experienceTaskLog.getExperienceTask().getExperience());
            }
        } else {
            Calendar c = Calendar.getInstance();            //得到当前日期和时间

            c.add(Calendar.DAY_OF_MONTH, experienceTaskLog.getExperienceTask().getLimitPeriod()); //设置日期为 ?(任务周期) 天后
            c.set(Calendar.HOUR, 00);                        //把当前时间小时变成０
            c.set(Calendar.MINUTE, 00);                      //把当前时间分钟变成０
            c.set(Calendar.SECOND, 00);                      //把当前时间秒数变成０
            c.set(Calendar.MILLISECOND, 00);                 //把当前时间毫秒变成０
            Date date1 = c.getTime();                       //创建当天的0时0分0秒一个date对象
            //没有该任务对象


            experienceTaskLog.setPeriodEndTime(date1);
            if (exp != null) {
                experienceRepository.setAmmountById(exp.getId(), exp.getAmmount() + experienceTaskLog.getExperienceTask().getExperience());
            }
        }

        experienceTaskLog.setExperience(experienceTaskLog.getExperienceTask().getExperience());
    }


}
