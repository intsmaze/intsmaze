package cn.intsmaze.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 刘洋 on 2018/9/29.
 */
public class AtomicThread implements Runnable{

    private static AtomicInteger acount=new AtomicInteger(0);

    protected long startTime;

    public AtomicThread(long startTime)
    {
        this.startTime=startTime;
    }

    @Override
    public void run() {
        int v=acount.incrementAndGet();
        while (v<1000000)
        {
            v=acount.incrementAndGet();;
        }
       System.out.println("AtomicThread use time:"+(System.currentTimeMillis()-startTime)+" ms"+"v="+v);
    }
}
