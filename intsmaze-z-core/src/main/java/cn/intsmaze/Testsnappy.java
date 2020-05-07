package cn.intsmaze;

import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘洋 on 2018/10/12.
 */
public class Testsnappy {

    public static void main(String[] args) throws IOException {
        Map<String,byte[]> map=new HashMap<String,byte[]>();

        String str="其实可用的压缩算法很多，而且压缩率与速度比Snappy高的也大有人在，典型的如：lz4，常用的压缩解压缩算法对比参见这儿。\n" +
                "至于我为什么选择了Snappy，就两个字：简单。哈哈！！\n" +
                "相比已经很简单的lz4，Snappy的API更简单，而且关键在解压缩环节，Snappy使用直观，不需要考虑压缩包大小或是压缩前大小，在某些环节更省心一些，当然细处并未评估，所以也就不做过多引导性的描述，还是应该根据具体项目需求选择。\n" +
                "具体使用：\n" +
                "--------------------- \n" +
                "作者：Calvin-Chen \n" +
                "来源：CSDN \n" +
                "原文：https://blog.csdn.net/c446984928/article/details/50816016?utm_source=copy \n" +
                "版权声明：本文为博主原创文章，转载请附上博文链接！";

        byte[] a=str.getBytes();
        System.out.println(a.length);

        for(int i=0;i<100000;i++) {
//            byte a[] = new byte[1024 * 4*8*10];
            byte b[]= Snappy.compress(a);
            map.put(i+"",b);
        }

    }
}
