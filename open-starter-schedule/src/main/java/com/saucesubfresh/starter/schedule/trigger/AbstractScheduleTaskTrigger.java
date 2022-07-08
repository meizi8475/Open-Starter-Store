package com.saucesubfresh.starter.schedule.trigger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @author lijunping on 2022/3/31
 */
@Slf4j
public abstract class AbstractScheduleTaskTrigger implements ScheduleTaskTrigger, InitializingBean, DisposableBean {

    private Thread thread;
    private volatile boolean threadToStop = false;
    private static final long INTERVAL_TIME = 1000;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        log.info("init schedulerTask success.");
    }

    @Override
    public void destroy() {
        threadToStop = true;
        stopThread(thread);
        log.info("JobSchedule stop success");
    }

    /**
     * 每次轮训会间隔1s
     */
    private void init(){
        thread = new Thread(() -> {
            while (!threadToStop) {
                threadSleep();
                try {
                    trigger();
                } catch (Exception e) {
                    if (!threadToStop) {
                        log.error("JobSchedule#ringThread error:{}", e.getMessage(), e);
                    }
                }
            }
            log.info("JobSchedule#ringThread stop");
        });
        thread.setDaemon(true);
        thread.setName("JobSchedule#ringThread");
        thread.start();
    }

    /**
     * 线程随眠
     */
    private void threadSleep(){
        try {
            TimeUnit.MILLISECONDS.sleep(INTERVAL_TIME - System.currentTimeMillis() % 1000);
        } catch (InterruptedException e) {
            if (!threadToStop) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 停止线程
     * @param thread 要停止的线程
     */
    private void stopThread(Thread thread){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        if (thread.getState() == Thread.State.TERMINATED){
            return;
        }

        // interrupt and wait
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
