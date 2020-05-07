package cn.intsmaze.thread.ThreadLocal;

/**
 * Created by 刘洋 on 2018/8/22.
 */
public class ThreadLocalTest implements Runnable {

    private String name;

    public ThreadLocalTest(String name) {
        this.name = name;
    }

    static ThreadLocal<String> threadLocalOne = new ThreadLocal<String>();
    static ThreadLocal<String> threadLocalTwo = new ThreadLocal<String>();


    @Override
    public void run()  {

        threadLocalOne.set(name + "----");
        threadLocalTwo.set(name + "||||");
        for (int i=0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  " + threadLocalOne.get()+threadLocalTwo.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest st = new ThreadLocalTest("你好");
        ThreadLocalTest st1 = new ThreadLocalTest("卡路里");
        new Thread(st, "新线程1").start();
        new Thread(st1, "新线程2").start();
    }
}
