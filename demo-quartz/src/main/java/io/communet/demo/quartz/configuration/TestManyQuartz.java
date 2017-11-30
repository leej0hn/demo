package io.communet.demo.quartz.configuration;

import io.communet.demo.quartz.task.TestManyTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>function: 测试调试器多个
 * <p>User: LeeJohn
 * <p>Date: 2017/11/30
 * <p>Version: 1.0
 */
@Configuration
@Slf4j
public class TestManyQuartz {

    //  task
    @Bean(name = "testManyTask")
    public TestManyTask testManyTask() {
        TestManyTask task = new TestManyTask();
        log.info("TestManyTask hashcode : " + task.hashCode());
        return  task;
    }

    //JobDetail
    @Bean(name = "testManyTaskJobDetail")
    @Autowired(required = false)
    public MethodInvokingJobDetailFactoryBean testOneTaskJobDetail(TestManyTask testManyTask) {
        log.info("MethodInvokingJobDetailFactoryBean - testManyTask hashcode : " + testManyTask.hashCode());
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(testManyTask);
        bean.setTargetMethod("execute");
        bean.setConcurrent(false);
        return  bean;
    }

    // CronTrigger
    @Bean(name = "testManyTaskCronTrigger" )
    @Resource(name = "testManyTaskJobDetail")
    public CronTriggerFactoryBean[] testOneTaskCronTrigger(JobDetail testManyTaskJobDetail) throws ParseException {
        log.info( "CronTriggerFactoryBean.JobDetail.testManyTaskJobDetail. hashcode : " + testManyTaskJobDetail.hashCode() ) ;
        List<CronTriggerFactoryBean> triggers = new ArrayList<CronTriggerFactoryBean>();
        List<String> crons = new ArrayList<>();
        crons.add("15 * * * * ?");
        crons.add("30 * * * * ?");
        crons.add("45 * * * * ?");
        String name = "testManyTaskCronTrigger_";
        int i = 0 ;
        for (String cron : crons) {
            CronTriggerFactoryBean triggerBean = new CronTriggerFactoryBean();
            log.info("testManyTaskCron : "+cron);
            triggerBean.setJobDetail(testManyTaskJobDetail);
            triggerBean.setCronExpression(cron);
            triggerBean.setName(name+i);
            triggerBean.afterPropertiesSet();
            triggers.add(triggerBean);
            i++;
        }
        return triggers.toArray(new CronTriggerFactoryBean[crons.size()]);
    }

}
