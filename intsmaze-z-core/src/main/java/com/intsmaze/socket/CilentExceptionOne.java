package com.intsmaze.socket;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
public class CilentExceptionOne {

	public static void main(String args[]){
	    String host="www.hao123.com";
	    int port=25;
	    if(args.length>1){
	        host = args[0];
	        port=Integer.parseInt(args[1]);
	    }
	    new CilentExceptionOne().connect(host,port);
	  }
	  public void connect(String host,int port){
		  
	    SocketAddress remoteAddr=new InetSocketAddress(host,port);
	    
	    Socket socket=null;
	    String result="";
	    try {
	        long begin=System.currentTimeMillis();
	        socket = new Socket();
	        
	        socket.connect(remoteAddr,10000);  //超时时间为1秒钟
	        
	        long end=System.currentTimeMillis();
	        
	        result=(end-begin)+"ms";  //计算连接所花的时间
	    }catch (BindException e) {
	        result="Local address and port can't be binded";
	        //显示绑定本地IP地址和端口时会发生的，如IP地址不是本机所具有的，或端口已经被别的进程占用。
	        //设置方式，一种构造函数时，显示指定，第二种就是无参构造函数后，调用bind()方法。设定。
	        //Socket s=new Socket();s.bind(SocketAddress bindpoint);
	        //注意，Socket s=new Socket("www.hao123.com",80);这样构造后，无法使用bind()方法，因为系统已经默认设定好了。
	        //待验证。。。
	    }catch (UnknownHostException e) {
	        result="Unknown Host";//无法识别主机名或IP地址，如host="localhost123";时
	    }catch (ConnectException e) {
	        result="Connection Refused";//1，服务器没有监听端口，如www.hao123.com监听端口是80.但是port=25;
	        //2,服务器进程拒绝客户端的连接请求，见CilentExceptionTwo
	    }catch (SocketTimeoutException e) {
	        result="TimeOut";
	    }catch (IOException e) {
	        result="failure";
	    } finally {
	        try {
	            if(socket!=null)socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println(remoteAddr+" : "+result);
	  }
	}