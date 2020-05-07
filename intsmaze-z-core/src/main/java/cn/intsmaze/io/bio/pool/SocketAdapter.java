package cn.intsmaze.io.bio.pool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketAdapter extends Socket{

	 /** 
	   * 连接状态 
	   */  
	  private boolean status=true;  
	  
	  /** 
	   * 默认的构造函数 
	   */  
	public SocketAdapter() {
		super();  
	  }  
	  
	  public SocketAdapter(String host,int port)throws UnknownHostException,IOException{  
	    super(host,port);  
	  }  
	  
	  /** 
	   * 判断此连接是否空闲 
	   * @return boolean 空闲返回ture,否则false 
	   */  
	  public boolean isFree(){  
	    return status;  
	  }  
	  
	  /** 
	   * 当使用此连接的时候设置状态为false（忙碌） 
	   */  
	  public void setBusy(){  
	    this.status=false;  
	  }  
	  
	  /** 
	   * 当客户端关闭连接的时候状态设置为true(空闲） 
	   */  
	  @Override  
	  public void close()throws IOException{  
		  // System.out.println("Close : set the status is free ");  
	        status=true;  
	        super.close();  
	  }  
	    
	  public void destroy() throws IOException{  
	    close();  
	  }  
}
