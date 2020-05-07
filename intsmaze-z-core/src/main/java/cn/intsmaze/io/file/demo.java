package cn.intsmaze.io.file;

import java.nio.ByteBuffer;

/** 
 * @author:YangLiu
 * @date:2018年8月6日 下午3:21:10 
 * @describe: 
 */
public class demo {

	public static void main(String[] args) {
		for(int i=0;i<20000;i++) {  
//		    ByteBuffer.allocateDirect(1024*100); //100K 
			ByteBuffer.allocate(1024*100); //100K  
		  }  
	}

}
