package cn.intsmaze.lbs;

import java.io.*;

public class FileWR {
	
	public static void main(String[] args) throws IOException
	{

		System.out.println(readFileAllLine("work_position.xml"));
//		BufferedWriter out=writeFile("f://hello.txt");
//		out.write("nihao12132"+"\r\n");
//		out.close();
//		System.out.println(out);
	}

	public static BufferedWriter writeFile(String url) throws IOException {
		BufferedWriter out;
		File writename = new File(url);
		if(writename.exists())
		{
			out = new BufferedWriter(new FileWriter(writename,true));
		}
		else
		{
			writename.createNewFile(); // 创建新文件
			out = new BufferedWriter(new FileWriter(writename));
		}
		return out;
	}

	public static String readFile(String url) {
		String str = "";
		try {
//			File file = new File(url);
//			if (file.isFile() && file.exists()) {
			InputStream in = ClassLoader.getSystemResourceAsStream(url);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in, "utf-8"));
//				InputStreamReader isr = new InputStreamReader(
//						new FileInputStream(file), "utf-8");
//				BufferedReader br = new BufferedReader(isr);

				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					str += lineTxt;
				}
				br.close();
//			} else {
//				System.out.println("文件不存在!");
//			}
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}
		return str;
	}
	
	
	public static String readFileAllLine(String url) {
		String str = "";
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream(url);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(in, "utf-8"));
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					str += lineTxt+"\r\n";
				}
				br.close();
		} catch (Exception e) {
			System.out.println("文件读取错误!");
		}
		return str;
	}

}
