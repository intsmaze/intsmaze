package com.intsmaze.rpc.websocket;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	// WEB_ROOT是服务器的根目录
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";

	// 关闭的命令
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	
	/**
	 * 运行httpServer,在浏览器中打下http://localhost:8080/index.jsp,就能看到服务器响应的结果了。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpServer server = new HttpServer();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1,InetAddress.getByName("127.0.0.1"));
			while (true) {
				try {
					Socket socket = null;
					InputStream input = null;
					OutputStream output = null;
					
					//接收客户端请求
					socket = serverSocket.accept();
					input = socket.getInputStream();
					output = socket.getOutputStream();
					
					// 封装request请求
					Request request = new Request(input);
					request.parse();
					
					// 封装response对象
					Response response = new Response(output);
					response.setRequest(request);
					response.sendStaticResource();
					socket.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
