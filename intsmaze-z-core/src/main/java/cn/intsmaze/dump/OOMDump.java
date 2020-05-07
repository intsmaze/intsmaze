package cn.intsmaze.dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘洋 on 2018/8/16.
 *OOM异常 并不一定是因为抛出的对象导致的。
 为什么这么说呢？？？
 加入ListA添加1000个元素后，内存快要不够了。
 这个时候ListA没有添加元素了，创建了一个ListB,ListB添加了20个元素内存溢出。这个时候报出的错误就是ListB了。其实真正的原因是ListA添加完后，没有释放导致的。
 这个时候看日志是没有用的，我们必须去分析OOM。

 方法一：
 命令：jmap -dump:format=b,file=heap.bin
 file：保存路径及文件名
 pid：进程编号（windows通过任务管理器查看，linux通过ps aux查看）
 dump文件可以通过MemoryAnalyzer(MAT)分析查看,可以查看dump时对象数量，内存占用，线程情况等。

 方法二：让JVM在遇到OOM(OutOfMemoryError)时生成Dump文件
 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/path/heap/dump
 */
public class OOMDump {

    static class OOMIntsmaze {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(ArrayList<OOMIntsmaze> list, int num) throws Exception {

        for (int i = 0; i < num; i++) {
            Thread.sleep(1000);
            list.add(new OOMIntsmaze());
            System.out.println(i);
        }
//        System.gc();
    }
    //-Xmx10M  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d://
    //参数-XX：+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析,文件在项目中
    //设置10M我们可以发现list添加132各个元素时会发生OOM，这个时候我们向list添加131个元素，然后执行map添加，发现map添加一个元素就报错。此时的oom异常日志定位的是map添加元素导致的。
    //但是真实情况不是的，因为看代码也会发现map只添加了2个元素，怎么会是他造成的。map的添加只是刚好此时jvm内存达到容量上限了。
    //所以要找到根本问题，是需要通过dump文件分析OOM时，各个对象的容量状态。
    //
    public static void main(String[] args) throws Exception {
        ArrayList<OOMIntsmaze> list = new ArrayList<OOMIntsmaze>();
        fillHeap(list,131);
        Map<String,OOMIntsmaze> map=new HashMap<String,OOMIntsmaze>();
        map.put("LIUYANG",new OOMIntsmaze());
        map.put("intsmaze",new OOMIntsmaze());
        Thread.sleep(20000000);
    }
}
