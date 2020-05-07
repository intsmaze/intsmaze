package cn.intsmaze.thread.atomic;

/**
 * Created by 刘洋 on 2018/9/29.
 */
public class SyncThread implements Runnable{

    private static int count=0;

    protected long startTime;

    public SyncThread(long startTime)
    {
        this.startTime=startTime;
    }

    protected synchronized int inc()
    {
       return ++count;
    }

    @Override
    public void run() {
        int v=inc();
        while (v<1000000)
        {
            v=inc();
        }
        System.out.println("synchronized use time:"+(System.currentTimeMillis()-startTime)+" ms"+"v="+v);
    }
}
