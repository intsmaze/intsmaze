package cn.intsmaze.io.nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class SocketClient implements Runnable {

	int clientNbr = 0;
	
	String[] str= {"who","what","where","hi","bye","intsmaze"};

	@Override
    public void run() {
		try {
			Socket sc = new Socket("localhost", 8383);
			OutputStream os = sc.getOutputStream();
			InputStream in = sc.getInputStream();
			os.write((str[new Random().nextInt(6)]).getBytes());
			os.flush();
			byte[] b = new byte[1024];
			int read = in.read(b, 0, 1024);
			System.out.println("from server:" + read + " bytes-->"
					+ new String(b));

//			os.write("hi".getBytes());
//			os.flush();
//			b = new byte[1024];
//			read = in.read(b, 0, 1024);
//			System.out.println("from server:" + read + " bytes-->"
//					+ new String(b));
//			
//			
//			
//			os.write("what".getBytes());
//			os.flush();
//			b = new byte[1024];
//			read = in.read(b, 0, 1024);
//			System.out.println("from server:" + read + " bytes-->"
//					+ new String(b));


//			sc.shutdownOutput();
//			sc.shutdownInput();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			new Thread(new SocketClient()).start();
		}
	}

}
