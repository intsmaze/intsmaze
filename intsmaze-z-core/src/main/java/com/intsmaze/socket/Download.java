package com.intsmaze.socket;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Download
{
	public static void main(String[] args){
		JFrame jf=new JFrame("网页下载程序");
		jf.setSize(600,400);
		jf.setLocation(100,100);
		JPanel p=new JPanel();
		JLabel l=new JLabel("Please input URL:");
		final JTextField tf=new JTextField(30);
		p.add(l);
		p.add(tf);
		jf.getContentPane().add(p,"North");
		final JTextArea ta=new JTextArea();
		jf.getContentPane().add(ta,"Center");
		JButton btn=new JButton("Download");
		jf.getContentPane().add(btn,"South");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=tf.getText();
            try {
            	
            	//建立url，!!!!!
            	//建立到这个url的连接，!!!!!
            	URL url=new URL(str);
            	URLConnection urlConn=url.openConnection();
            	            	
            	String line=System.getProperty("line.separator");
            	ta.append("Host: "+url.getHost());
            	ta.append(line);
            	ta.append("Port: "+url.getDefaultPort());
            	ta.append(line);
            	ta.append("ContentType: "+urlConn.getContentType());
            	ta.append(line);
            	ta.append("ContentLength: "+urlConn.getContentLength());
            	
            	InputStream is=urlConn.getInputStream();
            	//InputStreamReader isr=new InputStreamReader(is);
            	//BufferedReader br=new BufferedReader(isr);
            	
            	FileOutputStream fos=new FileOutputStream("1.html");
            	//String strLine;
            	//while((strLine=br.readLine())!=null)
            		int data;
              		while((data=is.read())!=-1){
              			//fos.write(strLine.getBytes());
              			//fos.write(line.getBytes());
              			fos.write(data);
              		}
              		//br.close();
              		is.close();
              		fos.close();
            	}
            catch (Exception ex) {
            	ex.printStackTrace();
            }

          }
        });
    
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		jf.setVisible(true);
	}
}
