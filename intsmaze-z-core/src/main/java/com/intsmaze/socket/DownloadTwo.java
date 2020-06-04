package com.intsmaze.socket;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class DownloadTwo
{
	public static void main(String[] args) throws IOException{
		System.out.println("输入链接：");
		Scanner sc=new Scanner(System.in);
		String url1=sc.nextLine();
		
		//建立url，!!!!!
    	//建立到这个url的连接，!!!!!
    	URL url=new URL(url1);
    	URLConnection urlConn=url.openConnection();
    	            	
    	System.out.println("Host: "+url.getHost());
    	System.out.println("Port: "+url.getDefaultPort());
    	System.out.println("ContentType: "+urlConn.getContentType());
    	System.out.println("ContentLength: "+urlConn.getContentLength());
    	String line=System.getProperty("line.separator");

    	InputStream is=urlConn.getInputStream();
    	
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));    
    	String line2 = null;    
    	BufferedWriter output = new BufferedWriter(new FileWriter("3.html")); //写入流

    	while ((line2 = reader.readLine()) != null) {    
    		System.out.println(line2 + "\n");
    		output.write(line2);
    	}    	
    	output.close();
    	is.close();
    }
}

    
    
    
		
		
		
		
		
//		JFrame jf=new JFrame("网页下载程序");
//		
//		jf.setSize(600,400);
//		jf.setLocation(100,100);
//		
//		JPanel p=new JPanel();
		
	
	
	
		
//		JLabel l=new JLabel("Please input URL:");
//		final JTextField tf=new JTextField(30);
//		
//		p.add(l);
//		p.add(tf);
//		
//		jf.getContentPane().add(p,"North");
//		
//		final JTextArea ta=new JTextArea();
//		jf.getContentPane().add(ta,"Center");
//		JScrollPane text=new JScrollPane(ta);
//		p.add(text); 
//		
//		JButton btn=new JButton("Download");
//		jf.getContentPane().add(btn,"South");
//		
//		btn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String str=tf.getText();
//            try {
//            	//建立url，!!!!!
//            	//建立到这个url的连接，!!!!!
//            	URL url=new URL(str);
//            	URLConnection urlConn=url.openConnection();
//            	            	
//            	String line=System.getProperty("line.separator");
//            	ta.append("Host: "+url.getHost());
//            	ta.append(line);
//            	ta.append("Port: "+url.getDefaultPort());
//            	ta.append(line);
//            	ta.append("ContentType: "+urlConn.getContentType());
//            	ta.append(line);
//            	ta.append("ContentLength: "+urlConn.getContentLength());
//            	
//            	InputStream is=urlConn.getInputStream();
//            	
//            	BufferedReader reader = new BufferedReader(new InputStreamReader(is));    
//            	String line2 = null;    
//            	BufferedWriter output = new BufferedWriter(new FileWriter("3.html")); //写入流
//
//            	while ((line2 = reader.readLine()) != null) {    
//            		ta.append(line2 + "\n");
//            		output.write(line2);
//            	}    
//            	
//            	output.close();
//            	is.close();
//            	}
//            catch (Exception ex) {
//            	ex.printStackTrace();
//            }
//
//          }
//        });
//    
//		jf.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		jf.setVisible(true);
//	}
