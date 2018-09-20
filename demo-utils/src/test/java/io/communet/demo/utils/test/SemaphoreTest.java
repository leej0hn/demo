package io.communet.demo.utils.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: LeeJohn
 * @date: 2018/9/20 13:54
 */
public class SemaphoreTest extends Thread {

    private Semaphore semaphore;

    public SemaphoreTest(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getId()+" 正在吃饭");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getId()+" 已经吃完");
            semaphore.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        SemaphoreTest test1 = new SemaphoreTest(semaphore);
        SemaphoreTest test2 = new SemaphoreTest(semaphore);
        test1.start();
        test2.start();
    }

}
