package cn.intsmaze.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 刘洋 on 2018/8/22.
 * ReentrantLock提供的trylock，lockInterruptibly只是提供了避免死锁的等待，置于如何处理还是需要我们编写业务逻辑的，不是说直接替换工程代码中所有的synchronized关键字就可以了。
 * 所以我们不能依赖于jdk提供的功能，真正防止死锁还是靠我们编写的代码。
 */
public class NoDeadLock {

    private Lock lockLeft = new ReentrantLock();
    private Lock lockRight = new ReentrantLock();

    public void leftRight() throws Exception
    {
        boolean flag=true;

        while(true) {
            if(flag) {
                if (lockLeft.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    Thread.sleep(2000);//实际业务中有些复杂的情况要考虑，这一块代码重复执行的幂等性问题。
                    if (lockRight.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        System.out.println("leftRight end!");
                        flag = false;
                        lockRight.unlock();
                    }else
                    {
                        System.out.println("无法获取到锁，释放锁!");
                    }
                    lockLeft.unlock();
                }
            }
        }
    }

    public void rightLeft() throws Exception
    {
        boolean flag=true;

        while(true) {
            if(flag) {
                if (lockRight.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    Thread.sleep(2000);
                    if (lockLeft.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        System.out.println("RighLeftt end!");
                        flag = false;
                        lockLeft.unlock();
                    }else
                    {
                        System.out.println("无法获取到锁，释放锁!");
                    }
                    lockRight.unlock();
                }
            }
        }
    }

    static class Thread0 extends Thread
    {
        private NoDeadLock dl;

        public Thread0(NoDeadLock dl)
        {
            this.dl = dl;
        }

        @Override
        public void run()
        {
            try
            {
                dl.leftRight();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    static class Thread1 extends Thread
    {
        private NoDeadLock dl;

        public Thread1(NoDeadLock dl)
        {
            this.dl = dl;
        }

        @Override
        public void run()
        {
            try
            {
                dl.rightLeft();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        NoDeadLock dl = new NoDeadLock();
        Thread0 t0 = new Thread0(dl);
        Thread1 t1 = new Thread1(dl);
        t0.start();
        t1.start();


        while(true) {
            ;
        }
    }
}
