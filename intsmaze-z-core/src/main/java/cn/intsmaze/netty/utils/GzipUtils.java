package cn.intsmaze.netty.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {

    public static byte[] gzip(byte[] data) throws Exception{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	GZIPOutputStream gzip = new GZIPOutputStream(bos);
    	gzip.write(data);
    	gzip.finish();
    	gzip.close();
    	byte[] ret = bos.toByteArray();
    	bos.close();
    	return ret;
    }
    
    public static byte[] ungzip(byte[] data) throws Exception{
    	ByteArrayInputStream bis = new ByteArrayInputStream(data);
    	GZIPInputStream gzip = new GZIPInputStream(bis);
    	byte[] buf = new byte[1024];
    	int num = -1;
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	while((num = gzip.read(buf, 0 , buf.length)) != -1 ){
    		bos.write(buf, 0, num);
    	}
    	gzip.close();
    	bis.close();
    	byte[] ret = bos.toByteArray();
    	bos.flush();
    	bos.close();
    	return ret;
    }
    
    public static void main(String[] args) throws Exception{
		
    	//读取文件
    	String readPath = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "006.jpg";
        File file = new File(readPath);  
        FileInputStream in = new FileInputStream(file);  
        byte[] data = new byte[in.available()];  
        in.read(data);  
        in.close();  
        
        System.out.println("文件原始大小:" + data.length);
        //测试压缩
        
        byte[] ret1 = GzipUtils.gzip(data);
        System.out.println("压缩之后大小:" + ret1.length);
        
        byte[] ret2 = GzipUtils.ungzip(ret1);
        System.out.println("还原之后大小:" + ret2.length);
        
        //写出文件
        String writePath = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + "006.jpg";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(ret2);
        fos.close();    	
    	
    	
	}
    
    
    
}
