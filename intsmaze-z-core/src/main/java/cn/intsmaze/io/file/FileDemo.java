package cn.intsmaze.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;


/**
 * @author:YangLiu
 * @date:2018年7月12日 下午4:58:17
 * @describe:
 */
public class FileDemo {

	/**
	 * File类用来将文件或者文件夹封装成对象，以方便对文件与文件夹的属性进行操作。
	 * 流不能对文件（夹）进行操作，只能操作数据。同时流不能对隐藏等具有限制的文件进行操作。
	 * */
	void testFile() {
		// 这里要注意，它只能将已存在的文件或文件夹封装成对象，如果文件或文件夹不存在，不会创建，
		// 和流不一样，流对象一建立就创建了文件。
		// 但是可以调用File的方法创建文件或文件夹对象。
		File f = new File("1.txt");// 相对路径，创建文件
		File f1 = new File("文件夹");// 相对路径，创建文件夹

		File f2 = new File("d:\\2.txt");// 绝对路径
		File f3 = new File("d:" + File.separator + "文件夹" + File.separator
				+ "文件夹");// 绝对路径
		// File.separator可以有助于实现跨平台

		File f4 = new File("d:\\文件夹", "3.txt");// 绝对路径
		File f5 = new File(f3, "4.txt");// 绝对路径
		// 注意路径名中想1.txt带后缀的不代表就是文件，是文件还是文件夹取决于File类的创建文件夹和文件方法
		/**
		 * 注意文件删除中deleteOnExit()和delete()的区别。 deleteOnExit()是程序运行结束后删除文件。
		 * delete()删除文件。 当在文件创建后下面代码发生异常时，就不会执行delete()，就是把delete()放到finally中，也
		 * 不会执行成功，因为文件在运行时是无法删除的，这时可以在createNewFile()后面接着
		 * deleteOnExit()，这样就算发生异常，只要程序运行结束，就会删除文件。
		 * */
		f1.mkdir();// 创建文件夹，只能创建一级，就是一次只能创建一个文件。
		f3.mkdirs();// 创建多级目录的文件夹
	}

	void testFole() {
		// 列出系统所有盘符。
		File[] files = File.listRoots();
		for (File F : files) {
			System.out.println(F);
		}
		// 列出文件夹下所有文件和文件夹，包括隐藏文件等。具体看API。
		File f = new File("d:\\");
		String[] names = f.list();
		for (String n : names) {
			System.out.println(n);
		}

		/**
		 * 过滤满足条件的文件。因为要实现接口，使用内部类。 可以发现dir就是指的的文件夹的路径名，name就是文件夹中的所有文件和文件夹。
		 * 这里不需要传参数，只需要程序员自己写函数方法，进行条件判断。
		 * */
		String[] namefile = f.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// System.out.println(dir+"--"+name);
				return name.endsWith(".txt");
			}

		});
		for (String n : namefile) {
			System.out.println(n);
		}
	}

	
	
	
	public static void testFileInputStream() throws IOException {
		FileInputStream fr = new FileInputStream("D://develop/CodeWorkSpace/12.txt");
		byte[] buf = new byte[24];
		int hasRead=0;
		while((hasRead=fr.read(buf))>0) {
            System.out.println(new String(buf, 0, hasRead));
        }
		fr.close();
		
	}
	
	
	 /* FileChannel.MapMode三个值：
	    MapMode.READ_ONLY：只读，若FileChannel不可读，抛出NonReadableChannelException
	    MapMode.READ_WRITE：可读写，若FileChannel不可写，抛出NonWritableChannelException
	    MapMode.PRIVATE：创建一个写时拷贝的映射，修改映射内存页的数据只对MappedByteBuffer可视
	*/
	public static void testNIOFileInputStream() throws IOException {
		File file=new File("D://develop/CodeWorkSpace/1.txt");
		FileInputStream fr = new FileInputStream(file);
		FileChannel fileChannel=fr.getChannel();
		
		//map()将channel对应的全部或部分数据映射成MappedByteBuffer，第一个参数是映射模式，第二，第三参数就是用于将fileChannel哪些数据映射到buffer中。
		
		//------------map()将fileChannel全部数据映射到buffer
//		MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		
		//-------------map()分批映射
		MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 20);
		CharBuffer cb=Charset.forName("UTF-8").newDecoder().decode(mappedByteBuffer);
		System.out.println("------"+cb);
		mappedByteBuffer.clear();
	    mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY, 21, 20);
		cb=Charset.forName("UTF-8").newDecoder().decode(mappedByteBuffer);
		System.out.println("-------------------------");
		System.out.println(cb);
		mappedByteBuffer.clear();
		
		
		//如果担心fileChannel对应的文件太大，使用map一次将所有文件映射到内存中引起性能下降(可以每次map映射部分数据)，也可以使用channel和buffer的多次取水模式。
		ByteBuffer byteBuffer=ByteBuffer.allocate(24);
//		ByteBuffer byteBuffer=ByteBuffer.allocateDirect(24);
		/**
		 * ByteBuffer.allocate(capability)  分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢
		 * ByteBuffer.allocteDirect(capability)分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快。但是一样会内存溢出，见DirectByteBufferTest
		 */
		if(fileChannel.read(byteBuffer)!=-1)
		{
			byteBuffer.flip();
			cb=Charset.forName("GBK").newDecoder().decode(byteBuffer);
			System.out.println(cb);
			byteBuffer.clear();
		}
		fr.close();
	}
	
	
	
	/**
	* @author:YangLiu
	* @date:2018年7月13日 下午2:57:50 
	* @describe:
	 */
	public static void testFileReader() throws IOException {
		FileReader fr = new FileReader("D://develop/CodeWorkSpace/12.txt");
		char c = (char) fr.read();
		/**
		 * 读取字符流可以发现，read()一次读一个字符，且会自动读取下一个字符。读取时如果已到达流的末尾，则返回 -1
		 * */
		System.out.println(c);
		fr.skip(2);
		c = (char) fr.read();
		System.out.println(c);

		//一般来说一个文件的字符数很大，所有数组的大小一般定义1024的倍数。2kb大小
		char[] buf = new char[2];// 定义一个数组，用于存储读取的字符。
		int num = fr.read(buf);// 将字符读入数组，返回的是读取的字符数,如果已到达流的末尾，则返回 -1
		System.out.println("num=" + num + "...." + new String(buf));

		/**
		 * 注意，如果用同一个数组存储读取的字符，那么，如果read已经读取到流末尾了，再读取时，
		 * 数组不会存取新的内容，存储的还是上次的内容，如果只读取了一个字符进入数组，那么只会覆盖掉数组中第一个字符，后面的字符不变。运行程序查看。
		 * */
		fr.close();
	}

	
	
	/**
	* @author:YangLiu
	* @date:2018年7月13日 下午2:58:27 
	* @describe:通过观察缓冲区readLine()等方法源码发现，内部其实也是调用的FileReader的read()方法。
	* private Reader in; in.read(cb, dst, cb.length - dst);
	* cb数组的大小为8192，使得一次read拿去很多数据，减少IO次数来提升的效率
	 */
	/**
	 *  为什么使用缓冲区读取文件会比不使用快:（不要缓冲区也可以，自己使用FileReader的时候，使用数组来读数据）
		原因是每次进行IO操作,都要从用户态陷入内核态,由内核把数据从磁盘中读到内核缓冲区,再由内核缓冲区到用户缓冲区,如果没有buffer，
		读取都需要从用户态到内核态切换，而这种切换很耗时，采用预读，减少IO次数.
	 */
	public static void testBufferReader() throws IOException {
		FileReader fr = new FileReader("D://develop/CodeWorkSpace/12.txt");
		BufferedReader br = new BufferedReader(fr);
		// 一次读取一行文本的方法。不包含任何行终止符。它如何知道文件中数据一行是根据行分割符来知道的。
		// 只返回回车符之前的数据内容，并不返回回车符。
		String s1 = br.readLine();

		System.out.println(s1);
		System.out.println(br.read());
		br.close();
		fr.close();
	}

	
	
	
	
	
	
	public static void testFileOutputStream() throws IOException {
		FileOutputStream fos = new FileOutputStream("fos1.txt");
		fos.write(129);
		fos.write(243);
		fos.write(128);// 2-128中的数转换为一字节的字母。
		// 129以上要和另一个129以上字节组合在一起转换为汉字，
		// 如果单独输入一个大于129的数，那么无法转换，打印为？号

		fos.write("aasdasd".getBytes());// 直接写入到文件中
		/**
		 * 注意字节流不需要刷新。 字符流其实也是走的字节，但是他需要把字节临时存起来，一个中文两个字节，
		 * 读字符流读一个字节不会立刻操作。字符流底层也是用的字节流缓冲区，
		 * */

		fos.close();
	}

	public static void testFileWriter() throws IOException {
		FileWriter fw = new FileWriter(
				"D://develop/CodeWorkSpace/FileWriter.txt");
		/**
		 * 创建一个新的*写入*字符流 writer,FileWriter继承与writer.
		 * 这里会在初始化时创建指定的文件，该文件会被创建在*指定的目录下*。 如果该目录已有同名文件，将--覆盖--已有的文件。每运行一次new
		 * FileWriter("demo.txt")， 就会覆盖上次产生的demo.txt文件。 然后对数据的操作都是在该文件中。
		 * 这里没用给绝对路径，那么文件的位置是该工作空间的位置 这里的是D:\javapractice\java_notes下
		 * ------------------------------------- 绝对路径 FileWriter fw=new
		 * FileWriter("d:\\demo.txt");
		 * */
		// 将数据写入到字符流 writer中，并没有直接--写到目的文件中--，而是在内存中，字符流 writer就是在内存中。
		fw.write("asdfasdf");
		// 要想让数据显示在文件中，必须刷新该流的缓存，流的缓存临时存放着数据。
		fw.flush();
		// 多次输入数据，每写一次就要刷新流一次以把流中的数据放文件到里面。
		// 也可以连续多次writer，再最后刷新流，那么每次写入流的数据都是加在流上面，不存在覆盖，只可能溢出流
		fw.write("1231\n234");
		/**
		 * 注意若要输入换行符，单纯的\n是不可以的，必须\r\n才会在windows下的记事本等软件中显示出换行。
		 * windows系统软件中它的回车符是由两个字符来表示的\r\n，Linus系统换行就是\n一个字符
		 * 所有单独写\n记事本软件是无法识别出来的。但是其他一些notpad++是可以识别的。
		 * */
		fw.flush();

		// 与flush的区别是，它关闭流之前会刷新一次流中的缓存数据，关闭流后无法再写数据了。
		// 而flush刷新后流一直在，还可以继续写。
		fw.close();

		/**
		 * 科普： java能往windows系统中写入数据， 其实是java在调用windows系统中的写动作。这就是流资源。
		 * java是靠系统内部的方式来完成数据的写入，这些调用方式都是在使用系统的资源，所有调用后都要释放资源，所有要 关闭流。
		 * */
	}

	void testBuffereWriter() throws IOException {
		// 创建一个字符写入流对象
		FileWriter fw = new FileWriter("demo.txt");
		// 为了提高字符写入流效率，加入了缓冲技术。
		// 只要将需要被提高效率的流对象作为参数传递给缓冲区的构造函数即可。
		BufferedWriter bufw = new BufferedWriter(fw);

		bufw.write("23sdfas");
		// 换行符因为在不同系统转义方式不一样，所以用bufw的函数可以实现跨平台
		bufw.write("\r\n");
		bufw.newLine();// 只所以可以实现跨平台，主要是调用底层的代码，而底层的代码是根据安装在不同平台下
		// 的jdk版本来确定的。
		// 只要用到缓冲区就要记得刷新。
		bufw.flush();
		// 这里虽然是FileWriter调用的系统资源，但是只需要关闭缓冲区就可以了，它就是在关闭缓冲区中的流对象。
		bufw.close();

		/**
		 * 注意最好每写一次就刷新一次，这样突然停电，数据就存在硬盘中，如果不， 停电后缓冲区的数据就会消失。
		 * */
	}
	
}
