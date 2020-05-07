package cn.intsmaze.dump;

import java.util.Vector;

/**
 * Created by 刘洋 on 2018/8/17.
 */
public class WatchFullGc {

    //[GC 2776K->2560K(11264K), 0.0028600 secs]   这是minor GC
    //-Xmx11M -Xms4M -verbose:gc   会执行full GC
    //-Xmx11M -Xms11M -verbose:gc  不会执行full GC
    //-Xmx11M -Xms11M -Xmn2M -XX:+PrintGCDetails
    public static void main(String[] args) {

        Vector v=new Vector();
        for(int i=1;i<=10;i++)
        {
            byte[] b=new byte[1024*1024];
            v.add(b);
            if(v.size()==3)
            {
                v.clear();//清空内存  注释掉该行，会报java.lang.OutOfMemoryError: Java heap space
            }

        }
        System.out.println(Runtime.getRuntime().maxMemory());//获取系统可用最大堆内存
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
    }
}
