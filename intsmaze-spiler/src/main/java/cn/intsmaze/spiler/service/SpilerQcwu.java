package cn.intsmaze.spiler.service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cn.intsmaze.spiler.DateUtils;
import cn.intsmaze.spiler.StringUtil;
import cn.intsmaze.spiler.util.FileWR;
import cn.intsmaze.spiler.bean.JobDescription;
import cn.intsmaze.spiler.bean.MtBean;
import cn.intsmaze.spiler.util.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpilerQcwu extends Spiler implements Runnable {

    private static final Logger logger = LoggerFactory
            .getLogger(SpilerQcwu.class);

    static String url = "http://search.51job.com/jobsearch/search_result.php?"
            + "fromJs=1&keywordtype=1&lang=c&stype=2&postchannel=0000&fromType=1&confirmdate=9";

    private static String areaPath = "Area-Sim.txt";

    private static String searchConditionPath = "work_position-Sim.xml";

    //    private static String filePath=" /home/admin/spiler/";
    private static String filePath = "d:\\home/admin/spiler/";

    //各城市各行业组合的检索url的首页
    public static BlockingQueue<String> urlQueue = new LinkedBlockingQueue<String>();

    private MtBean mbean = new MtBean();

    private String[] strUrl;

    //爬取的url
    private String spilerUrl;

    //表名
    private String tableName;

    public void run() {
        while (true) {
            try {
                String condition = urlQueue.poll();
                if (condition == null) {
                    logger.warn("队列为空，睡眠60s");
                    Thread.sleep(60 * 1000);
                    continue;
                }
                logger.info("从队列获取元素{}", condition);

                if (urlCanUse(url, condition))
                    WriteJob();
                else {
                    logger.error("url no used {}", spilerUrl);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // 仅仅检查该url是否可用，并对该url增加最近24小时的限制
    public boolean urlCanUse(String url, String dataValue) {
        try {
            String arr[] = dataValue.split(":");
            // jobarea=250200
            String jobarea = "jobarea=" + arr[0];
            // industrytype=01
            String industrytype = "industrytype=" + arr[1];
            spilerUrl = url + "&" + jobarea + "&" + industrytype;
            tableName = arr[0] + "_" + arr[1];//地区+行业 编号作为表明
            spilerUrl = orderByJobTime(spilerUrl);
            if (spilerUrl == null)
                return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }


    public void WriteJob() {
        try {
            logger.info("职位主页:" + mbean.getPisition());
            bw = FileWR.writeFile(filePath + tableName + "-" + mbean.getPisition() + "-" + DateUtils.getMonthHour(0) + ".txt");
            int i = 0;
            while (true) {
                spilerUrl = spilerCondition(spilerUrl, tableName, i);
                i++;
                logger.info("职位主页:{},本职位共{}页,当前{}页", mbean.getPisition(), mbean.getPageNum(), i);
                if (spilerUrl == null) {
                    break;
                }
            }
            logger.info(mbean.getPisition() + "爬取完成");
            bw.close();
        } catch (Exception e) {
            logger.error("", e);
        }
    }


    /**
     * 将该页面的职位数据存入指定的介质中
     *
     * @param elements
     * @param tableName
     */
    public void parsingJobDetails(Elements elements, String tableName, MtBean mbean) {
        JobDescription jp = new JobDescription();
        for (int i = 0; i < elements.size(); i++) {
            String positionUrl = "";
            try {
                Element element = elements.get(i);
                String time = element.select(".t5").html();

                String nowDate = DateUtils.getMonth(0);
                if (!time.trim().equals(nowDate))//要爬当天发布的职位，不然无法追踪某个岗位发布的时间周期
                {
//                    logger.warn("该职位不是当前发布,执行过滤");
                    continue;
                }

                String position = element.select(".t1 span a").html();
                positionUrl = element.select(".t1 span a").attr("href");

                String positionCode = StringUtil.getPositionCode(positionUrl);


                //缓存的时间日期不是当前，则清空缓存内容，重新存储新的key.
                if (!keyDate.equals(nowDate)) {
                    keyDate = nowDate;
                    qcwuKeySet.clear();
                }

                //判断该职位是否之前已经发布了只是被更新了
                if (qcwuKeySet.contains(positionCode)) {
                    jp.setEndTime(time);
//                    logger.warn("{}-->>职位主页:{},该职位当天{}已经抓取，执行过滤,url is {}",mbean.getPisition(), positionCode,positionUrl);
//                    logger.warn("职位主页:{},该职位当天{}已经抓取，执行过滤", mbean.getPisition(), positionCode);
                    continue;
                } else {
                    //update  jp.setStarttime(time);
                    qcwuKeySet.add(positionCode);
                }

                String companyUrl = element.select(".t2 a").attr("href");

                String companyCode = StringUtil.getPositionCode(companyUrl);

                String companyName = element.select(".t2 a").attr("title");
                String companyNameSimple = element.select(".t2 a").html();
                String workSplace = element.select(".t3").html();
                String salary = element.select(".t4").html();

                jp.setTableName(tableName);

//				jp.setJobDetail(getJobDetails(positionUrl));//暂时不爬取职位介绍，先优化整个设计
                jp.setCompanyCode(companyCode);
                jp.setPositionCode(positionCode);
                jp.setPosition(position);
                jp.setPositionUrl(positionUrl);
                jp.setCompanyUrl(companyUrl);
                jp.setCompanyName(companyName);
                jp.setCompanyNameSimple(companyNameSimple);
                jp.setWorkSplace(workSplace);
                jp.setSalary(salary);
                jp.setTime(time);

                try {
                    String gsonStr = gson.toJson(jp);
                    qcwuJobDescriptionQueue.put(jp);
                    qcwuJobDescriptionQueue.put(jp);
                    qcwuJobDescriptionQueue.put(jp);
                    qcwuJobDescriptionQueue.put(jp);
                    bw.write(jp.toString() + "\r\n");

//                    bw.write(gsonStr + "\r\n");
                    bw.flush();
                } catch (IOException e) {
                    logger.error("positionUrl is:" + positionUrl, e);
                }
            } catch (Exception e) {
                logger.error("positionUrl is:" + positionUrl, e);
            }
        }
    }

    /**
     * @author:YangLiu
     * @date:2018年4月9日 下午4:53:26
     * @describe:获取最近24小时发布的职位 的url查询地址
     */
    public String orderByJobTime(String spilerUrl) {
        String smess = HttpUtils.toHttp(spilerUrl, "gbk");
        Document doc = Jsoup.parse(smess);
        Elements elements = doc.select("div #filter_issuedate");
        elements = elements.select("a");

        Elements ele = doc.select("title");
        if (ele.size() == 0) {
            logger.warn("title is null");
            return null;
        }
        // 【北京,通信/电信/网络设备招聘，求职】-前程无忧 中创建文件不允许有/否则报错
        mbean.setPisition(ele.get(0).html().replaceAll("/", ","));

        for (int i = 0; i < elements.size(); i++) {
//			if(elements.get(i).text().equals("近三天"))
            if (elements.get(i).text().trim().equals("24小时内")) {
                return elements.get(i).attr("href");
            }
        }
        return null;
    }

    /**
     * @param spilerUrl
     * @param tableName
     * @return 将本页的职位数据写入存储介质，并返回下一页的url
     */
    public String spilerCondition(String spilerUrl, String tableName, int forI) {
        String smess = "";
        smess = HttpUtils.toHttp(spilerUrl, "gbk");
        Document doc = Jsoup.parse(smess);
        Elements elements = doc.select("div .dw_table");
        // 得到一页职位招聘列表
        elements = elements.select(".el").not(".title");

        parsingJobDetails(elements, tableName, mbean);

        String str = doc.select("div .p_in .td").text();//共17页，到第 页
        int i = str.indexOf("页");
        int num = Integer.valueOf(str.substring(1, i));//共多少页
        if (forI == 0) {
            mbean.setPageNum(num);
            elements = doc
                    .select("div .dw_page .p_box .p_wp .p_in ul li:last-child a");// 获取下一页地址
            if (elements.size() == 0) {
                return null;
            }
            Element eleMentDemo = elements.get(0);
            //		 System.out.println(eleMentDemo.html());//下一页
            //		 System.out.println(eleMentDemo.attr("href"));//下一页的url
            //        return eleMentDemo.attr("href");
            //获取url后，从后往前爬，
            strUrl = eleMentDemo.attr("href").split("html");
        }
        int nowPage = mbean.getPageNum() - forI;
        if(nowPage<=0)
        {
            return null;
        }
        String sUrl = strUrl[0].substring(0, Integer.valueOf(strUrl[0].lastIndexOf(","))) + "," + nowPage + ".html" + strUrl[1];
        return sUrl;
    }

//	public String getJobDetails(String jobDetailsUrl) {
//		String smess = HttpUtils.toHttp(jobDetailsUrl, "gbk");
//		Document doc = Jsoup.parse(smess);
//		Elements elements = doc.select("div .tCompany_main");
//
//		// 得到一页职位招聘列表
//		elements = doc.select("div .tBorderTop_box");
//		String detailsStr = "";
//		for (int i = 0; i < elements.size() - 1; i++) {
//			Element element = elements.get(i);
//			detailsStr = StringUtils.join(detailsStr, element.text(), "\r\n");
//		}
//		return detailsStr;
//	}


    /**
     * 笛卡儿积,得到所有的查询条件的首页面。
     *
     * @return
     */
    public static void cartesianProduct(
    ) {
        List<String> listArea = spilerSearchAreaFile();
//		List<String> listArea=spilerSearchArea;
        List<String> listPosition = spilerSearchPosition();

//		BlockingQueue<String> cacheQueue = new LinkedBlockingQueue<String>();
        for (int i = 0; i < listArea.size(); i++) {
            for (int j = 0; j < listPosition.size(); j++) {
                urlQueue.offer(listArea.get(i) + ":" + listPosition.get(j));
            }
        }
    }

    public static List<String> spilerSearchAreaFile() {
        List<String> list = new ArrayList<String>();
        String smess = FileWR.readFileAllLine(areaPath);
        String areas[] = smess.split("\r\n");

        for (int i = 0; i < areas.length; i++) {
            list.add(areas[i].split(":")[1]);
        }
        return list;
    }

    public static List<String> spilerSearchPosition() {
        // BufferedWriter out=FileWR.writeFile("D:\\Position.txt");
        List<String> list = new ArrayList<String>();
        String smess = FileWR.readFile(searchConditionPath);
        Document doc = Jsoup.parse(smess);
        Elements ele = doc.select("#indtype_click_center_right .js_more");
        for (int i = 0; i < ele.size(); i++) {
            Element element = ele.get(i);
            // if(out!=null)
            // {
            // out.write(element.select("em").html()+":"+element.select("em").attr("data-value")+"\r\n");
            // out.flush();
            // }
            list.add(element.select("em").attr("data-value"));
        }
        // if(out!=null)
        // out.close();
//        System.out.println(ele.size());
        return list;
    }


//	public static List<String> spilerSearchArea() {
//		// BufferedWriter out=FileWR.writeFile("D:\\Area.txt");
//		List<String> list = new ArrayList<String>();
//		String smess = FileWR.readFile("D:\\work_area.xml");
//		Document doc = Jsoup.parse(smess);
//		Elements ele = doc
//				.select("div #work_position_click_center_right .js_more");
//		for (int i = 0; i < ele.size(); i++) {
//			Element element = ele.get(i);
//			// if(out!=null)
//			// {
//			// out.write(element.select("em").html()+":"+element.select("em").attr("data-value")+"\r\n");
//			// out.flush();
//			// }
//			list.add(element.select("em").attr("data-value"));
//		}
//		// if(out!=null)
//		// out.close();
//		System.out.println(ele.size());
//		return list;
//	}

}
