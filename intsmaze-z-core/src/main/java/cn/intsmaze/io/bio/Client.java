package cn.intsmaze.io.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	int i = 0;
	private String url = "127.0.0.1";
	private int port = 1000;
	private Socket socket = null;
	private BufferedWriter bufOut = null;
	private BufferedReader bufIn = null;

	public Client() throws UnknownHostException, IOException {
		socket = new Socket(url, port);
		bufOut = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		bufIn = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		bufOut.write("我来了" + "\n");
		bufOut.flush();
		System.out.println(bufIn.readLine());
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {

		Client c = new Client();

	}
}
