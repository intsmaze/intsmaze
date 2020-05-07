package cn.intsmaze.io.nio.nulti;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.intsmaze.io.nio.CharsetHelper;

public class NioThreadServer {

	private Selector selector;

	private ExecutorService tp = Executors.newFixedThreadPool(5);

	public static void main(String[] args) {
		NioThreadServer server = new NioThreadServer();
		server.init();
		System.out.println("server started --> 8383");
		server.listen();
	}

	private void init() {
		ServerSocketChannel servSocketChannel;
		try {
			servSocketChannel = ServerSocketChannel.open();
			servSocketChannel.configureBlocking(false);
			servSocketChannel.socket().bind(new InetSocketAddress(8383));
			selector = Selector.open();
			servSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		while (true) {
			try {
				selector.select();
				Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

				while (ite.hasNext()) {
					SelectionKey sk = ite.next();
					ite.remove();
					
					 MyTask runnable = new MyTask(selector, sk);
					 Thread thread = new Thread(runnable);
					 thread.start();
					 Thread.sleep(1000);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	class MyTask implements Runnable {
		
		private ByteBuffer readBuffer;

		Selector selector;

		SelectionKey key;

		public MyTask(Selector selector, SelectionKey key) {
			this.selector = selector;
			this.key = key;
		}

		@Override
        public void run() {
			readBuffer = ByteBuffer.allocate(1024);
			try {
				Thread.sleep(1000);
				handleKey(key);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void handleKey(SelectionKey key) throws IOException,
				ClosedChannelException {
			SocketChannel channel = null;

			try {
				if (key.isAcceptable()) {
					ServerSocketChannel serverChannel = (ServerSocketChannel) key
							.channel();
					channel = serverChannel.accept();// 接受连接请求
					System.out.println("创建一个连接"+channel+serverChannel);
					channel.configureBlocking(false);
					channel.register(selector, SelectionKey.OP_READ);
				}
				else if (key.isReadable()) {
					channel = (SocketChannel) key.channel();
					System.out.println("获得接收的数据"+channel);
					// 先清空一下buffer
					readBuffer.clear();
					int count = channel.read(readBuffer);

					if (count > 0) {
						readBuffer.flip();
						
						CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
						String question = charBuffer.toString();
						System.out.println("读到数据" + question);
						String answer = getAnswer(question);
						channel.write(CharsetHelper.encode(CharBuffer.wrap(answer)));
					} else {
						System.out.println("没读到数据");
						channel.close();
					}
				}
				else
				{
					System.out.println("其他状态");
				}
			} catch (Throwable t) {
				t.printStackTrace();
				System.out.println("发送异常"+channel);
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
}
