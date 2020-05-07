package cn.intsmaze.io.nio;

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
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

				long e = 0;
				while (ite.hasNext()) {
					SelectionKey sk = ite.next();
					ite.remove(); // 确保不重复处理
					if (sk.isAcceptable()) {
						doAccept(sk);
					} else if (sk.isValid() && sk.isReadable()) {
						doRead(sk);
					} else if (sk.isValid() && sk.isWritable()) {
						doWrite(sk);
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

			EchoClient echoClient = new EchoClient();
			clientKey.attach(echoClient);
			System.out.println("接收请求"+echoClient);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doRead(SelectionKey sk) throws CharacterCodingException {
		SocketChannel channel = (SocketChannel) sk.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		int len;

		try {
			len = channel.read(readBuffer);
			if (len <= 0) {
				disconnect(sk);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			disconnect(sk);
			return;
		}
		readBuffer.flip();

		CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
		String question = charBuffer.toString();
		System.out.println("读事件处理" + question+readBuffer);
		// tp.execute(new HandleMsg(sk, question));
		tp.execute(new HandleMsg(sk, readBuffer));
	}

	private void doWrite(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();

		EchoClient echoClient = (EchoClient) sk.attachment();

		LinkedList<String> outq = echoClient.getOutputQueue();
		String bb = outq.getLast();
		try {
			System.out.println("写事件处理" + bb+"::"+echoClient);
			int len = channel.write(CharsetHelper.encode(CharBuffer.wrap(bb
					+ "***")));
			if (len == -1) {
				disconnect(sk);
				return;
			}
			// if(bb.remaining()==0)
			// {
			// outq.removeLast();
			// }
			// int len =
			// channel.write(CharsetHelper.encode(CharBuffer.wrap(bb)));
			// if (len == -1) {
			// disconnect(sk);//这里如果端口连接了，那么一次只能响应客户端一次数据请求，如果客户端想在一个连接中连续发生两次请求，则要注释该方法
			// return;
			// }
		} catch (IOException e) {
			e.printStackTrace();
			disconnect(sk);
		}
		if (outq.size() == 0) {
			sk.interestOps(SelectionKey.OP_READ);
		}
	}

	private void disconnect(SelectionKey sk) {
		System.out.println("----------------");
		SocketChannel channel = (SocketChannel) sk.channel();
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class HandleMsg implements Runnable {

		SelectionKey sk;

		ByteBuffer bb;

		public HandleMsg(SelectionKey sk, ByteBuffer bb) {
			super();
			this.sk = sk;
			this.bb = bb;
		}

		@Override
		public void run() {
			CharBuffer charBuffer;
			try {
				
				charBuffer = CharsetHelper.decode(bb);
				String question = charBuffer.toString();
				EchoClient echoClient = (EchoClient) sk.attachment();
				System.out.println("异步队列:"+question+"::"+echoClient+":::"+bb);
				echoClient.enqueue(question);
			} catch (CharacterCodingException e) {
				e.printStackTrace();
			}

			// try {
			// // SocketChannel channel = (SocketChannel) sk.channel();
			// //// LinkedList<String> outq = echoClient.getOutputQueue();
			// //// String bb = outq.getLast();
			// // LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
			// // ByteBuffer bb = outq.getLast();
			// channel.write(CharsetHelper.encode(CharBuffer.wrap(CharsetHelper.decode(bb).toString()+"***")));
			// } catch (CharacterCodingException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

//			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			sk.interestOps(SelectionKey.OP_WRITE);
			selector.wakeup();
		}

	}
}
