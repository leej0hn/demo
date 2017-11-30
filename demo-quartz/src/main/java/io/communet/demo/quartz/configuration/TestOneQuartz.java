package io.communet.demo.quartz.configuration;

import io.communet.demo.quartz.task.TestOneTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * <p>function: 测试调试器单个
 * <p>User: LeeJohn
 * <p>Date: 2017/11/30
 * <p>Version: 1.0
 */
@Configuration
@Slf4j
public class TestOneQuartz {

    //  task
    @Bean(name = "testOneTask")
    public TestOneTask testOneTask() {
        TestOneTask task = new TestOneTask();
        log.info("testOneTask hashcode : " + task.hashCode());
        return  task;
    }

    //JobDetail
    @Bean(name = "testOneTaskJobDetail")
    @Autowired(required = false)
    public MethodInvokingJobDetailFactoryBean testOneTaskJobDetail(TestOneTask testOneTask) {
        log.info("MethodInvokingJobDetailFactoryBean - testOneTask hashcode : " + testOneTask.hashCode());
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(testOneTask);
        bean.setTargetMethod("execute");
        bean.setConcurrent(false);
        return  bean;
    }



    // CronTrigger
    @Bean(name = "testOneTaskCronTrigger" )
    @Autowired
    @Qualifier("testOneTaskJobDetail")
    public CronTriggerFactoryBean testOneTaskCronTrigger(JobDetail testOneTaskJobDetail){
        log.info( "CronTriggerFactoryBean.JobDetail.testOneTaskJobDetail. hashcode : " + testOneTaskJobDetail.hashCode() ) ;
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(testOneTaskJobDetail);
        String cron = "0 * * * * ?";
        log.info("testOneTaskCron : "+cron);
        bean.setCronExpression(cron);
        return bean;
    }
}
