package cn.intsmaze.lbs;

import java.io.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * Created by 刘洋 on 2018/9/10.
 */
public abstract class LbsUtils {


    public String BaiDukey = "ahK24lmsGtU0WB0t2kQxaL4wqBwhQWlz";
//    public String BaiDukey_1 = "UmxwW2aFfUcNz6EzrvOh0L1a73edMgkz";
//    public String BaiDukey_2 = "VSx8jx960PXrwry0DEi8H6q1G6sLzmb2";//另一个百度账号申请的
    public String BaiDukey_3 ="";

    protected RestTemplate restTemplate;

    protected int connectTimeout = 60000;

    protected int readTimeout = 60000;

    protected BufferedWriter out;

    public void init() throws IOException {
        restTemplate = createSimpleRestTemplate();
    }

    private RestTemplate createSimpleRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        return new RestTemplate(requestFactory);
    }


    public abstract void getLocation(String address, int firstIndex, int lastIndex) throws IOException;

    public abstract void getAddress(String address) throws Exception;


    public void cleanBranch() throws IOException {
        out = FileWR.writeFile("d:\\位置数据/分行_resultv2.txt");
        File file = new File("d:\\位置数据/分行信息.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "utf-8"));
        try {
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                getLocation(lineTxt, 3, 5);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        br.close();
    }

    public void cleanOrg(String inputPath,String outPath) throws IOException {
        out = FileWR.writeFile("d:\\位置数据/org_result_v3.txt");
        File file = new File("d:\\位置数据/org_v3.txt");
//        out = FileWR.writeFile(outPath);
//        File file = new File(inputPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "utf-8"));
        try {
            String lineTxt = null;
            int i=0;
            while ((lineTxt = br.readLine()) != null) {
                //2为机构名称，4为详细地址信息
//                if(i>2058) {
//                    BaiDukey=BaiDukey_1;
                   //通过物理位置返回gcj02ll的坐标
                    getLocation(lineTxt, 2, 3);
//                }
//                else if(i>3000)
//                {
//                    BaiDukey=BaiDukey_2;
//                    getLocation(lineTxt, 2, 4);
//                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        br.close();
    }


    public void cleanLatLon(String inputPath,String outPath) throws Exception {
        out = FileWR.writeFile(outPath);
        File file = new File(inputPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "utf-8"));
        try {
            String lineTxt = null;
            int i=1;
            while ((lineTxt = br.readLine()) != null) {
//                if(i<6000) {
//                    BaiDukey=BaiDukey_1;
                    getAddress(lineTxt);
//                }
//                else if(i>=6000&&i<12000)
//                {
//                    BaiDukey=BaiDukey_2;
//                    getAddress(lineTxt);
//                }
//                else
//                {
//                    BaiDukey=BaiDukey_3;
//                    getAddress(lineTxt);
//                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        br.close();
    }




    public BufferedWriter getOut() {
        return out;
    }

    public void setOut(BufferedWriter out) {
        this.out = out;
    }
}
