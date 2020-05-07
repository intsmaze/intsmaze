package cn.intsmaze.io.file;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

import sun.nio.ch.DirectBuffer;

/**
 * @author:YangLiu
 * @date:2018年7月13日 上午11:07:27
 * @describe: 
 *            测试一:设置JVM参数-Xmx100m，运行异常，因为如果没设置-XX:MaxDirectMemorySize，则默认与-Xmx参数值相同
 *            ，分配128M直接内存超出限制范围   java.lang.OutOfMemoryError: Direct buffer memory 。
 *            测试用例2：设置JVM参数-Xmx256m，运行正常，因为128M小于256M，属于范围内分配。
 *            测试用例3：设置JVM参数-Xmx256m -XX:MaxDirectMemorySize=100M，运行异常，分配的直接内存128M超过限定的100M。
 *            测试用例4：设置JVM参数
 *            -Xmx768m，运行程序观察内存使用变化，会发现clean()后内存马上下降，说明使用clean()方法能有效及时回收直接缓存 。
 */
public class DirectByteBufferTest {
	public static void main(String[] args) throws InterruptedException {

		// 分配128MB直接内存
		ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 128);

		TimeUnit.SECONDS.sleep(20);
		System.out.println("ok");
		
		// 清除直接缓存
		((DirectBuffer) bb).cleaner().clean();
		System.out.println("ok");

	}
}
