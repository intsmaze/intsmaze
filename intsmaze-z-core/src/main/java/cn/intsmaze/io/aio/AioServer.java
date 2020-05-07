package cn.intsmaze.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * 实现服务端监听客户端，并且接受客户端发过来的数据
 * 
 * @author
 * 
 */

public class AioServer {

	public AioServer(int port) throws IOException{
		final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));		
		listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

			@Override
			public void completed(AsynchronousSocketChannel ch, Void attachment) {
				listener.accept(null, this); //接受下一个连接
				try {
					handler(ch);
				} catch (InterruptedException | ExecutionException e) {
					 
					e.printStackTrace();
				}
				
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				System.out.println("异步io失败");
				
			}
		});
		
		
	}
	
	//真正逻辑
	public void handler(AsynchronousSocketChannel ch) throws InterruptedException, ExecutionException{
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		ch.read(byteBuffer).get();
		byteBuffer.flip();
		System.out.println("服务端接受： " + byteBuffer.get());
	}
	
	public static void main(String[] args) throws Exception {
		AioServer aioServer = new AioServer(7080);
		System.out.println("服务端监听端口： " + 7080);
		Thread.sleep(10000);
	}

}
