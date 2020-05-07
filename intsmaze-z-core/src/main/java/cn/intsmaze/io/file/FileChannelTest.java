package cn.intsmaze.io.file;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author:YangLiu
 * @date:2018年7月12日 下午4:13:52
 * @describe: 普通文件通道IO和内存映射IO的速度
 * 
 * 
 *            1.堆外内存的分配耗时比较大.
 *            2.还是比mmap内存映射来得慢，都不要说通过mmap读取数据的时候还涉及缺页异常、页面调度的系统调用了
 *            ，看来内存映射文件确实NB啊，这还只是 86M的文件，如果上 G 的大小呢？
 */
public class FileChannelTest {
	public static void main(String[] args) throws IOException {
		testFileAllocate();
		testFileAllocateDirect();
		testMappedByteBuffer();
	}

	public static void testFileAllocate() throws IOException {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile("D://迅雷下载/爱剪辑-vip.mp4", "rw");
			// file = new RandomAccessFile("D://tmp/zookeeper/version-2/log.1",
			// "rw");
			FileChannel channel = file.getChannel();

			ByteBuffer buff = ByteBuffer.allocate(1024);
			long timeBegin = System.currentTimeMillis();
			while (channel.read(buff) != -1) {
				buff.flip();
				buff.clear();
			}
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 
	* @author:YangLiu
	* @date:2018年8月1日 下午10:45:54 
	* @describe:DirectMemory的内存只有在 JVM执行 full gc 的时候才会被回收，如果在其上分配过大的内存空间，也将出现 OutofMemoryError，即便 JVM 堆中的很多内存处于空闲状态。
	 */
	public static void testFileAllocateDirect() throws IOException {
		RandomAccessFile file = null;
		try {

			file = new RandomAccessFile("D://迅雷下载/爱剪辑-vip.mp4", "rw");//这个时候报错哦 java.lang.IllegalArgumentException: Negative capacity: -1302015569
			// file = new RandomAccessFile("D://tmp/zookeeper/version-2/log.1",
			// "rw");
			FileChannel channel = file.getChannel();

			// ByteBuffer buff = ByteBuffer.allocateDirect(1024);
			ByteBuffer buff = ByteBuffer.allocateDirect((int) file.length());// 速度提升了
																				// 一次性分配整个文件长度大小的堆外内存
			long timeBegin = System.currentTimeMillis();
			while (channel.read(buff) != -1) {
				buff.flip();
				buff.clear();
			}
			long timeEnd = System.currentTimeMillis();
			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void testMappedByteBuffer() throws IOException {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile("D://迅雷下载/爱剪辑-vip.mp4", "rw");
			// file = new RandomAccessFile("D://tmp/zookeeper/version-2/log.1",
			// "rw");
			FileChannel fc = file.getChannel();
			int len = (int) file.length();
			MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
					len);
			byte[] b = new byte[1024];

			long timeBegin = System.currentTimeMillis();
			for (int offset = 0; offset < len; offset += 1024) {

				if (len - offset > 1024) {
					buffer.get(b);
				} else {
					buffer.get(new byte[len - offset]);
				}
			}
			long timeEnd = System.currentTimeMillis();

			System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
