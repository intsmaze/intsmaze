package cn.intsmaze.netty.httpfile;//package bhz.netty.httpfile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//import bhz.utils.HttpCallerUtils;
//import bhz.utils.HttpProxy;
//
//public class Test {
//
//	
//	public static void main(String[] args) throws Exception{
//		Map<String, String> params = new HashMap<String, String>();
//		byte[] ret = HttpCallerUtils.getStream("http://192.168.1.111:8765/images/a.doc", params);
//		
//		//byte[] ret = HttpProxy.get("http://192.168.1.111:8765/images/006.jpg");
//        //写出文件
//        String writePath = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + "a.doc";
//        FileOutputStream fos = new FileOutputStream(writePath);
//        fos.write(ret);
//        fos.close();    
//		
//		
//	}
//}
