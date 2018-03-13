package io.communet.demo.scheduled.component;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>@function:
 * <p>@author: LeeJohn
 * <p>@date: 2018/3/13
 * <p>@version: 1.0
 */
@Component
@Slf4j
public class TestScheduled {

    //第一次延时10秒，完成后5秒再执行
    @Scheduled(initialDelay = 1000 * 10 , fixedRate = 1000 * 5)
    public void pull() {
        try {
            log.info("当前线程名：" + Thread.currentThread().getName());
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }
    }
}
