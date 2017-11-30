package io.communet.demo.quartz.configuration.core;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p>function: 同一任务多个调度时间
 * <p>User: LeeJohn
 * <p>Date: 2016/5/18.
 * <p>Version: 1.0
 */
public abstract class QuartzConfiguration {


    @Bean(name = "schedulerFactoryBean" )
    @Autowired(required = false)
    public SchedulerFactoryBean schedulerFactoryBean(Trigger[] triggersAutowired){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        int otherTriggersLength = 0;
        CronTriggerFactoryBean[] otherTriggers = get3thCronggers();
        if( otherTriggers != null && otherTriggers.length > 0){
            otherTriggersLength = otherTriggers.length;
        }
        Trigger[] triggers = new Trigger[otherTriggersLength + triggersAutowired.length];
        for( int i = 0 ; i < otherTriggersLength ; i++ ){
            triggers[i] = otherTriggers[i].getObject();
        }

        int j = 0;
        for(int i = otherTriggersLength ; i < triggers.length ; i++ ){
            triggers[i] = triggersAutowired[j] ;
            j++;
        }
        bean.setTriggers(triggers);
        return bean;
    }

    protected abstract CronTriggerFactoryBean[] get3thCronggers();

}
