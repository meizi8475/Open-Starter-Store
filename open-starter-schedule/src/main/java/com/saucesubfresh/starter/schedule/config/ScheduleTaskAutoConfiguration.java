package com.saucesubfresh.starter.schedule.config;


import com.saucesubfresh.starter.schedule.DefaultTaskScheduler;
import com.saucesubfresh.starter.schedule.TaskScheduler;
import com.saucesubfresh.starter.schedule.executor.DefaultScheduleTaskExecutor;
import com.saucesubfresh.starter.schedule.executor.ScheduleTaskExecutor;
import com.saucesubfresh.starter.schedule.initializer.DefaultScheduleTaskInitializer;
import com.saucesubfresh.starter.schedule.initializer.ScheduleTaskInitializer;
import com.saucesubfresh.starter.schedule.loader.DefaultScheduleTaskLoader;
import com.saucesubfresh.starter.schedule.loader.ScheduleTaskLoader;
import com.saucesubfresh.starter.schedule.manager.HashedWheelScheduleTaskQueueManager;
import com.saucesubfresh.starter.schedule.manager.LocalScheduleTaskPoolManager;
import com.saucesubfresh.starter.schedule.manager.ScheduleTaskPoolManager;
import com.saucesubfresh.starter.schedule.manager.ScheduleTaskQueueManager;
import com.saucesubfresh.starter.schedule.properties.ScheduleProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 定时任务执行器配置类
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ScheduleProperties.class)
public class ScheduleTaskAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskPoolManager scheduleTaskManager(){
    return new LocalScheduleTaskPoolManager();
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskQueueManager scheduleTaskQueueManager(){
    return new HashedWheelScheduleTaskQueueManager();
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskLoader scheduleTaskLoader(){
    return new DefaultScheduleTaskLoader();
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskExecutor scheduleTaskExecutor(){
    return new DefaultScheduleTaskExecutor();
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskScheduler taskScheduler(ScheduleTaskExecutor scheduleTaskExecutor,
                                     ScheduleTaskPoolManager scheduleTaskPoolManager,
                                     ScheduleTaskQueueManager scheduleTaskQueueManager){
    return new DefaultTaskScheduler(scheduleTaskExecutor, scheduleTaskPoolManager, scheduleTaskQueueManager);
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskInitializer taskInitializer(TaskScheduler taskScheduler,
                                                 ScheduleTaskLoader scheduleTaskLoader,
                                                 ScheduleTaskPoolManager scheduleTaskPoolManager,
                                                 ScheduleTaskQueueManager scheduleTaskQueueManager){
    return new DefaultScheduleTaskInitializer(taskScheduler, scheduleTaskLoader, scheduleTaskPoolManager, scheduleTaskQueueManager);
  }
}
