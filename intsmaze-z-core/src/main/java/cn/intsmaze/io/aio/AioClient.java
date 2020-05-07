package cn.intsmaze.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class AioClient {
	
	private AsynchronousSocketChannel client = null;
	
	public AioClient(String hostname,int port) throws Exception{
		client = AsynchronousSocketChannel.open();
		Future<?> future = client.connect(new InetSocketAddress(hostname, port));
		Object object = future.get();
		System.out.println(object);
	}
	
	public void write(byte b){
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		byteBuffer.put(b);
		byteBuffer.flip();
		client.write(byteBuffer);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		AioClient aioClient = new AioClient("127.0.0.1", 7080);
		aioClient.write("1".getBytes()[0]);
		
		
		
	}

}
