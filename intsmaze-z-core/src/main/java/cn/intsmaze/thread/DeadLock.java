package cn.intsmaze.thread;

/**
 * Created by 刘洋 on 2018/8/22.
 */
public class DeadLock {

    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() throws Exception
    {
        synchronized (left)
        {
            Thread.sleep(2000);
            synchronized (right)
            {
                System.out.println("leftRight end!");
            }
        }
    }

    public void rightLeft() throws Exception
    {
        synchronized (right)
        {
            Thread.sleep(2000);
            synchronized (left)
            {
                System.out.println("rightLeft end!");
            }
        }
    }

    static class Thread0 extends Thread
    {
        private DeadLock dl;

        public Thread0(DeadLock dl)
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
        private DeadLock dl;

        public Thread1(DeadLock dl)
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
        DeadLock dl = new DeadLock();
        Thread0 t0 = new Thread0(dl);
        Thread1 t1 = new Thread1(dl);
        t0.start();
        t1.start();

        while(true) {
            ;
        }
    }
}
