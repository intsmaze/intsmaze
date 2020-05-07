package cn.intsmaze.lbs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘洋 on 2018/11/17.
 */
public class CompareOrg {


    public static void main(String[] str)
    {
        Map<String,String> map=readFile("d:\\机构_result_v3.txt");

        Map<String,String> map1=readFileV2("d:\\org_result_v2.txt");

         for(Map.Entry<String,String> e:map1.entrySet())
         {
             if(!map.containsKey(e.getKey()))
             {
                 System.out.println(e.getKey()+";;"+e.getValue());
             }
             else
             {
//                 System.out.println(e.getKey()+"befroe  "+e.getValue()+"now  "+map1.get(e.getKey()));
             }
         }

    }


    public static Map readFile(String url) {
        Map map=new HashMap();
        try {
            			File file = new File(url);
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);

            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
               String[] str=lineTxt.split("\t");
                map.put(str[8],str[11]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return map;
    }

    public static Map readFileV2(String url) {
        Map map=new HashMap();
        try {
            File file = new File(url);
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(file), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] str=lineTxt.split("\t");
                map.put(str[7],str[10]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("文件读取错误!");
        }
        return map;
    }
}
