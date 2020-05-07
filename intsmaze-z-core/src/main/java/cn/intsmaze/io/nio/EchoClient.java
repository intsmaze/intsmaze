package cn.intsmaze.io.nio;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/** 
 * @author:YangLiu
 * @date:2018年6月27日 下午7:19:40 
 * @describe: 
 */
public class EchoClient {
	
	private LinkedList<String> outq;

	public LinkedList<String> getOutputQueue() {
		return outq;
	}
	public EchoClient() {
		this.outq = new LinkedList<String>();
	}
	public void enqueue(String bb) {
		outq.addFirst(bb);
}
	
//	private LinkedList<ByteBuffer> outq;
//
//	public LinkedList<ByteBuffer> getOutputQueue() {
//		return outq;
//	}
//
//	public void enqueue(ByteBuffer bb) {
//		outq.addFirst(bb);
//	}

//	public EchoClient() {
//		this.outq = new LinkedList<ByteBuffer>();
//	}
	
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
