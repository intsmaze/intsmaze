package com.intsmaze.spiler;

import java.io.IOException;

import com.intsmaze.spiler.util.FileWR;

public class Demo {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String smess = FileWR.readFileAllLine("D:\\Area.txt");
		smess.split("\r\n");
		System.out.println(smess.split("\r\n"));
	}

}
