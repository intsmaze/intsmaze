package cn.intsmaze.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 
 * @author YangLiu
 * @date 2018年5月15日
 * @version 1.0 服务端采用nio方式，客户端可以用nio或bio,只是方式不同，连接通信毕竟都是tcp请求。
 * SocketChannel是TCP,如果udp则是DatagramChannel（了解即可）
 * 
服务端开始轮询1
读取事件
接受连接请求:1
接受连接请求  睡完觉了


服务端开始轮询2
读取事件
接受连接请求:2
接受连接请求  睡完觉了

读取事件
读数据：2
读数据  睡完觉了
读到数据2
读到数据0intsmaze


服务端开始轮询3
读取事件
读数据：3
读数据  睡完觉了
读到数据3
读到数据0intsmaze

服务端开始轮询4
读取事件
读数据：4
读数据  睡完觉了
读到数据4
读到数据where

服务端开始轮询5
读取事件
读数据：5
读数据  睡完觉了
读到数据5
读到数据where

 */
public class NioServer {
	
	static int i=0;
	
	private ByteBuffer readBuffer;
	// 通道管理器
	private Selector selector;

	public static void main(String[] args) {
		NioServer server = new NioServer();
		server.init();
		System.out.println("server started --> 8383");
		server.listen();
	}

	private void init() {
		readBuffer = ByteBuffer.allocate(1024);
		ServerSocketChannel servSocketChannel;

		try {
			// 创建一个socket channel； channel是nio中对通信通道的抽象，不分入站出站方向
			servSocketChannel = ServerSocketChannel.open();
			// 设置通道为非阻塞的方式
			servSocketChannel.configureBlocking(false);
			// 将通道绑定在服务器的ip地址和某个端口上
			servSocketChannel.socket().bind(new InetSocketAddress(8383));

			// 打开一个多路复用器
			selector = Selector.open();
			Selector selector1=Selector.open();
			System.out.println(selector);//不是同一个对象
			System.out.println(selector1);//不是同一个对象
			
			
			// 将上面创建好的socket channel注册到selector多路复用器上
			// 对于服务端来说，一定要先注册一个OP_ACCEPT事件用来响应客户端的连接请求
			/*
			 * 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，注册
			 * 该事件后，当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
			 */
			servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * 
	 * intsmaze理解:多路复用技术是操作系统层实现的，java只是提供了相关的api去调用操作系统底层的实现，所以在代码上面看不到线程相关的切换操作，
	 * 是两个线程，一个是我们的业务处理线程，一个就是事件选择线程。
	 */
	private void listen() {
		System.out.println("服务端启动成功！");
		// 轮询访问selector
		while (true) {
			try {
				
				System.out.println("服务端开始轮询"+(++i));
				// 去询问一次selector选择器
				selector.select();//等待需要处理的新事件，阻塞，将一直持续到下一个传入的试卷
				System.out.println("接收到一个请求，开始处理");
				
				
				// 获得selector中选中的项的迭代器，选中的项为注册的事件
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
				
				while (ite.hasNext()) {
					System.out.println("读取事件");
					// 遍历到一个事件key
					SelectionKey key = ite.next();
					ite.remove(); // 删除已选的key，以防重复处理
					// 处理事件 客户请求连接事件
					handleKey(key);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private void handleKey(SelectionKey key) throws IOException,
			ClosedChannelException {
		SocketChannel channel = null;
		int number=i;
		try {
			if (key.isAcceptable()) {//
				ServerSocketChannel serverChannel = (ServerSocketChannel) key
						.channel();
				// 获得和客户端连接的通道
				channel = serverChannel.accept();// 接受连接请求
				// 设置成非阻塞
				channel.configureBlocking(false);
				
				// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读权限
				channel.register(selector, SelectionKey.OP_READ);
				//通过将于该客户端的连接channel注册到selector，那么下次该客户端连接发送数据，就能准确识别出对应的客户端，保证不会向错误的客户端发送错误的数据
				
				
				System.out.println("接受连接请求:"+number+channel);
				Thread.sleep(10000);
				System.out.println("接受连接请求  睡完觉了");
			} else if (key.isReadable()) { // 处理读取客户端发来的信息的事件
				channel = (SocketChannel) key.channel();
				// 先清空一下buffer
				readBuffer.clear();
				/*
				 * 当客户端channel关闭后，会不断收到read事件，但没有消息，即read方法返回-1
				 * 所以这时服务器端也需要关闭channel，避免无限无效的处理
				 */
				int count = channel.read(readBuffer);
				System.out.println("读数据：" + number+channel);
				Thread.sleep(10000);
				System.out.println("读数据  睡完觉了");
				if (count > 0) {
					System.out.println("读到数据" + number);
					// 一定需要调用flip函数，否则读取错误数据
					// 简单来说，flip操作就是让读写指针、limit指针复位到正确的位置
					readBuffer.flip();
					/*
					 * 使用CharBuffer配合取出正确的数据; String question = new
					 * String(readBuffer.array());可能会出错，
					 * 因为前面readBuffer.clear();并未真正清理数据 只是重置缓冲区的position, limit,
					 * mark， 而readBuffer.array()会返回整个缓冲区的内容。
					 * decode方法只取readBuffer的position到limit数据。
					 * 例如，上一次读取到缓冲区的是"where", clear后position为0，limit为 1024，
					 * 再次读取“bye"到缓冲区后，position为3，limit不变，
					 * flip后position为0，limit为3，前三个字符被覆盖了，但"re"还存在缓冲区中， 所以 new
					 * String(readBuffer.array()) 返回 "byere",
					 * 而decode(readBuffer)返回"bye"。
					 */
					CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
					String question = charBuffer.toString();
					// 根据客户端的请求，调用相应的业务方法获取业务结果
					System.out.println("读到数据" + question);
					String answer = getAnswer(question);
					channel.write(CharsetHelper.encode(CharBuffer.wrap(answer)));
				} else {
					System.out.println("没读到数据" + number);
					// 这里关闭channel，因为客户端已经关闭channel或者异常了
					channel.close();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			if (channel != null) {
				channel.close();
			}
		}
	}

	private String getAnswer(String question) {
		String answer = null;

		switch (question) {
		case "who":
			answer = "我是凤姐\n";
			break;
		case "what":
			answer = "我是来帮你解闷的\n";
			break;
		case "where":
			answer = "我来自外太空\n";
			break;
		case "hi":
			answer = "hello\n";
			break;
		case "bye":
			answer = "88\n";
			break;
		default:
			answer = "请输入 who， 或者what， 或者where";
		}

		return answer;
	}
}