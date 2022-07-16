package com.saucesubfresh.starter.schedule.initializer;

import com.saucesubfresh.starter.schedule.cron.CronHelper;
import com.saucesubfresh.starter.schedule.domain.ScheduleTask;
import com.saucesubfresh.starter.schedule.loader.ScheduleTaskLoader;
import com.saucesubfresh.starter.schedule.manager.ScheduleTaskPoolManager;
import com.saucesubfresh.starter.schedule.manager.ScheduleTaskQueueManager;
import com.saucesubfresh.starter.schedule.TaskScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * @author: 李俊平
 * @Date: 2022-07-08 23:17
 */
@Slf4j
public class DefaultScheduleTaskInitializer implements ScheduleTaskInitializer, InitializingBean, DisposableBean {

    private final TaskScheduler taskScheduler;
    private final ScheduleTaskLoader scheduleTaskLoader;
    private final ScheduleTaskPoolManager scheduleTaskPoolManager;
    private final ScheduleTaskQueueManager scheduleTaskQueueManager;

    public DefaultScheduleTaskInitializer(TaskScheduler taskScheduler,
                                          ScheduleTaskLoader scheduleTaskLoader,
                                          ScheduleTaskPoolManager scheduleTaskPoolManager,
                                          ScheduleTaskQueueManager scheduleTaskQueueManager) {
        this.taskScheduler = taskScheduler;
        this.scheduleTaskLoader = scheduleTaskLoader;
        this.scheduleTaskPoolManager = scheduleTaskPoolManager;
        this.scheduleTaskQueueManager = scheduleTaskQueueManager;
    }

    @Override
    public void initialize() {
        List<ScheduleTask> scheduleTasks = scheduleTaskLoader.loadScheduleTask();
        scheduleTaskPoolManager.addAll(scheduleTasks);
        for (ScheduleTask task : scheduleTasks) {
            long nextTime = CronHelper.getNextTime(task.getCronExpression());
            scheduleTaskQueueManager.put(task.getTaskId(), nextTime);
        }
        taskScheduler.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            initialize();
            log.info("Schedule task initialize successed");
        }catch (Exception e){
            log.error("Schedule task initialize failed, {}", e.getMessage());
        }
    }

    @Override
    public void destroy() throws Exception {
        taskScheduler.stop();
    }
}
