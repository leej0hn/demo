package io.communet.demo.quartz.configuration.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * <p>function: 同一任务多个调度时间
 * <p>User: LeeJohn
 * <p>Date: 2016/5/18.
 * <p>Version: 1.0
 */
@Configuration
public class QuartzImplConfiguration extends QuartzConfiguration{

    @Autowired
    @Qualifier("testManyTaskCronTrigger")
    CronTriggerFactoryBean[] testManyTaskCronTrigger;

    @Override
    public CronTriggerFactoryBean[] get3thCronggers() {
        return testManyTaskCronTrigger;
    }

}
