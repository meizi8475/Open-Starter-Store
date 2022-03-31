package com.lightcode.starter.schedule.config;


import com.lightcode.starter.schedule.core.ScheduleTaskManage;
import com.lightcode.starter.schedule.core.support.RedisScheduleTaskManage;
import com.lightcode.starter.schedule.executor.DefaultScheduleTaskExecutor;
import com.lightcode.starter.schedule.executor.ScheduleTaskExecutor;
import com.lightcode.starter.schedule.trigger.DefaultScheduleTaskTrigger;
import com.lightcode.starter.schedule.trigger.ScheduleTaskTrigger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author : lijunping
 * @weixin : ilwq18242076871
 * Description: 定时任务执行器配置类
 */
@Configuration
public class ScheduleTaskAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(ScheduleTaskManage.class)
  @ConditionalOnBean(RedisTemplate.class)
  public ScheduleTaskManage scheduleTaskManage(RedisTemplate<String, Object> redisTemplate){
    return new RedisScheduleTaskManage(redisTemplate);
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskExecutor scheduleTaskExecutor(){
    return new DefaultScheduleTaskExecutor();
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleTaskTrigger scheduleTaskTrigger(ScheduleTaskManage scheduleTaskManage, ScheduleTaskExecutor scheduleTaskExecutor){
    return new DefaultScheduleTaskTrigger(scheduleTaskManage, scheduleTaskExecutor);
  }
}
