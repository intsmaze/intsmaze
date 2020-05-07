package cn.intsmaze.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 刘洋 on 2018/9/29.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe= Executors.newFixedThreadPool(3);
        AtomicThread atomicThread=new AtomicThread(System.currentTimeMillis());
        for(int i=0;i<3;i++)
        {
            exe.submit(atomicThread);
        }
        Thread.sleep(10000);

        exe= Executors.newFixedThreadPool(3);
        SyncThread syncThread=new SyncThread(System.currentTimeMillis());
        for(int i=0;i<3;i++)
        {
            exe.submit(syncThread);
        }
    }
}
