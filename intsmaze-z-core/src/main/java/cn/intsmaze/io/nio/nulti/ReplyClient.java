package cn.intsmaze.io.nio.nulti;

import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * @author:YangLiu
 * @date:2018年6月27日 下午7:19:40 
 * @describe: 
 */
public class ReplyClient {
	
	private LinkedList<String> mesqQ;
	
	private ArrayList<String> channal;
	
	public LinkedList<String> getOutputQueue() {
		return mesqQ;
	}
	public ReplyClient() {
		this.mesqQ = new LinkedList<String>();
		this.channal=new ArrayList<String>();
	}
	public void enqueue(String mesg) {
		mesqQ.addFirst(mesg);
	}

	
	public void addChannal(String str)
	{
		this.channal.add(str);
	}
	@Override
	public String toString() {
		return "ReplyClient [mesqQ=" + mesqQ + ", channal=" + channal + "]";
	}

}
