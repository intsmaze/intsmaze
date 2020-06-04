package com.intsmaze.rpc.websocket;
import java.io.IOException;
import java.io.InputStream;

public class Request {
	
	// 输入流
	private InputStream input;
	
	//截取url,如http://localhost:8080/index.html ，截取部分为 /index.html
	private String uri;

	/**
	 * 从套接字中读取字符信息
	 */
	public void parse() {
		StringBuffer request = new StringBuffer(2048);
		int i = 0;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		for (int j = 0; j < i; j++) {
			request.append((char) (buffer[j]));
		}
		System.out.println(request.toString());
		uri = parseUri(request.toString());
	}

	/**
	 * 截取请求的url
	 */
	private String parseUri(String requestString) {
		int index1 = 0;
		int index2 = 0;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}
	
	public Request(InputStream inputStream) {
		this.input = inputStream;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
