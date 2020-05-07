package cn.intsmaze.lbs;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 刘洋 on 2018/11/20.
 */
public class CheckAddress {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\org_result_v3.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        String lineTxt = null;
        while ((lineTxt = br.readLine()) != null) {
            String[] str=lineTxt.split("\t");
            if(HasDigit(str[10]))
            {
//                if(str[str.length-1].equals("2"))
                    System.out.println(lineTxt);
            }
        }
        br.close();
    }

    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

}
