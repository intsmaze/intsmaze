package cn.intsmaze.io.nio.nulti;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.intsmaze.io.nio.CharsetHelper;

public class NioMultiReadServer {

	private Selector selector;

	private ExecutorService tp = Executors.newFixedThreadPool(20);

	public static void main(String[] args) {
		NioMultiReadServer server = new NioMultiReadServer();
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
					// 遍历到一个事件key
					SelectionKey sk = ite.next();
					ite.remove(); // 确保不重复处理
					if (sk.isAcceptable()) {
						doAccept(sk);
					} else if (sk.isValid() && sk.isReadable()) {
						doRead(sk);
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private void doAccept(SelectionKey sk) {
		ServerSocketChannel serverChannel = (ServerSocketChannel) sk.channel();
		SocketChannel channel = null;

		try {
			channel = serverChannel.accept();// 接受连接请求
			channel.configureBlocking(false);
			SelectionKey clientKey = channel.register(selector,
					SelectionKey.OP_READ);

			 ReplyClient echoClient = new ReplyClient();
			 echoClient.addChannal(channel.toString());
			 clientKey.attach(echoClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doRead(SelectionKey sk) throws CharacterCodingException {
		SocketChannel channel = (SocketChannel) sk.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		int len;

		try {
			readBuffer.clear();
			len = channel.read(readBuffer);
			if (len <= 0) {
				System.out.println("断开连接------------");
				disconnect(sk);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			disconnect(sk);
			return;
		}
		readBuffer.flip();
		tp.submit(new HandleMsg(sk, readBuffer));

	}

	private void disconnect(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class HandleMsg implements Runnable {

		SelectionKey sk;

		String bb;
		
		ByteBuffer readBuffer;

		public HandleMsg(SelectionKey sk, String bb) {
			super();
			this.sk = sk;
			this.bb = bb;
		}
		
		public HandleMsg(SelectionKey sk, ByteBuffer readBuffer) {
			super();
			this.sk = sk;
			this.readBuffer = readBuffer;
		}

		@Override
		public void run() {
			
			try {
				Thread.sleep(200);
				CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
				String question = charBuffer.toString();
				ReplyClient echoClient = (ReplyClient) sk.attachment();
				echoClient.enqueue(question);
				
				System.out.println("读事件处理" + question + readBuffer);
				SocketChannel channel = (SocketChannel) sk.channel();
				echoClient.addChannal(channel.toString());
				
				channel.write(CharsetHelper.encode(CharBuffer.wrap(getAnswer(question))));
				System.out.println(echoClient.toString());
			} catch (CharacterCodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
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
