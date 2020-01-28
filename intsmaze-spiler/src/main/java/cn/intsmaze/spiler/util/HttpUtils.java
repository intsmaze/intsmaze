package cn.intsmaze.spiler.util;

//import cn.intsmaze.spiler.service.StartCBRC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(HttpUtils.class);

    public static void main(String[] args) throws IOException {

        System.out.println(HttpUtils.toHttp("http://search.51job.com/jobsearch/search_result.php?fromJs=1&keywordtype=1&lang=c&stype=2&postchannel=0000&fromType=1&confirmdate=9&jobarea=020000&industrytype=01", "gbk"));
    }

    public static HttpURLConnection configConnection(String urlPath) {

        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlPath);
            connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//	  	  	connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cookie", "Hm_lvt_860f3361e3ed9c994816101d37900758=1431943232; BIGipServerPool_apache_web=1375797514.20480.0000; Hm_lpvt_860f3361e3ed9c994816101d37900758=1431943236");
            connection.setRequestProperty("Referer", "http://www.sporttery.cn/");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
//			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ");

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                connection = null;
            }
        }
    }

    /**
     * java.net.URL
     *
     * @param surl
     * @param charset
     * @return
     */
    public static String getHttpMess(String surl, String charset) {
        StringBuffer sbReturn = new StringBuffer();

        HttpURLConnection connection = null;
        try {
            logger.info("发送请求{}",surl);
            connection = configConnection(surl);
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.info("响应码:{}",connection.getResponseCode());
                if (connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM) {
                    String newUrl = connection.getHeaderField("Location");
                    logger.info("发送请求{}",surl);
                    connection = configConnection(newUrl);
                    logger.info("响应码:{}",connection.getResponseCode());
                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            InputStream in = null;
            in = connection.getInputStream();
            BufferedReader data = new BufferedReader(
                    new InputStreamReader(in, charset));
            String tempbf;
            while ((tempbf = data.readLine()) != null) {
                sbReturn.append(tempbf);
                sbReturn.append("\r\n");
            }
            data.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                connection = null;
            }
        }
        logger.info("接收响应数据");
        return sbReturn.toString();
    }

    public synchronized static String toHttp(String url, String charset) {
        String sReturn = null;
        try {
            sReturn = getHttpMess(url, charset);
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sReturn;
    }
}
