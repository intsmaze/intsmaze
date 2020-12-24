package cn.intsmaze.reference;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author:YangLiu
 * @date:2018年6月5日 下午2:32:58
 * @describe:
 */
public class TestReference {


    //jvm即使是oom也不会回收拥有强引用的对象
    //想要gc回收这个对象，就需要显式的将object = null ，那么对象就不存在引用关系
    @Test
    public static void StrongReference() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < 9999999; i++) {
            arrayList.add(new BufferedImage(999, 999, BufferedImage.TYPE_INT_RGB));
        }
    }

    //在jvm内存不够的时候就会回收拥有软引用的对象，在jvm内存充足的时候不会回收
    //跟强引用不同的是，每当jvm内存不够的时候，就会回收软引用对象new SoftReference<>(new BufferedImage(999, 999, BufferedImage.TYPE_INT_RGB))，
    //所以并没有抛出oom
    @Test
    public static void SoftReference() throws InterruptedException {

        SoftReference s = new SoftReference("nihao");//弱引用，包装对象，我们new的对象放入该对象中
        System.out.println(s.get());
        System.gc();
        Thread.sleep(10000);
        System.out.println(s.get());
        ArrayList<SoftReference<Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < 9999999; i++) {
            arrayList.add(new SoftReference<>(new BufferedImage(999, 999, BufferedImage.TYPE_INT_RGB)));
            System.out.println(arrayList.size());
        }
    }


    //弱引用对象会在每一次的gc中被回收，不管jvm的内存怎么样，但是gc在jvm中的线程优先级是很低的，执行的次数比较少。
    public static void WeakReference() {
        WeakReference w = new WeakReference("nihao");
        System.out.println(w.get());

        WeakReference<BufferedImage> reference = new WeakReference<BufferedImage>(new BufferedImage(999, 999, BufferedImage.TYPE_INT_RGB));
        System.gc();
        if (reference.get() != null) {
            BufferedImage bufferedImage = reference.get();
            System.out.println("no null");
        }
    }

}
