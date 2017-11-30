package io.communet.demo.quartz.task;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/30
 * <p>Version: 1.0
 */
@Slf4j
public class TestManyTask {

    public void execute(){
        log.info("TestManyTask : executing ...");
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("TestManyTask : done ");
    }
}
