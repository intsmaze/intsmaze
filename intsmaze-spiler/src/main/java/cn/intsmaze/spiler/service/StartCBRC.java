//package cn.intsmaze.spiler.service;
//
//import cn.intsmaze.spiler.DBService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by 刘洋 on 2018/9/8.
// */
//public class StartCBRC {
//
//    private static final Logger logger = LoggerFactory
//            .getLogger(StartCBRC.class);
//
//    public static void main(String[] args) throws IOException,
//            InterruptedException {
//
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-job-hash.xml","spring-mysql.xml");
//
//
//        int threadNum=Integer.valueOf(args[0]);
//        ExecutorService es = Executors
//                .newFixedThreadPool(threadNum);
//        es.submit(new Thread() {
//            public void run() {
//                try {
//                    SpilerQcwu.cartesianProduct();
//                    logger.info("use:{}", SpilerQcwu.urlQueue.size());
//                    while (true) {
//                        if (SpilerQcwu.urlQueue.isEmpty()) {
//                            logger.warn("队列构造线程睡眠30min");
//                            Thread.sleep(30 * 60 * 1000);
//                            SpilerQcwu.cartesianProduct();
//                            logger.info("use:{}", SpilerQcwu.urlQueue.size());
//                        } else {
//                            logger.info("还有{}个爬取完，等待构造爬取队列", SpilerQcwu.urlQueue.size());
//                        }
//                        Thread.sleep(30 * 1000);
//
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Thread.sleep(5000);
//        for (int i = 0; i < threadNum-1; i++) {
//            es.submit(new SpilerQcwu());
//        }
//
//        es.submit(new DBService(applicationContext));
//
//
//        while(true)
//        {
//            Thread.sleep(60*1000);
//            logger.error("------>>>>>>"+Spiler.qcwuJobDescriptionQueue.size());
//        }
//
//
//
//
////		while (true) {
////			if (end == SpilerQcwu.THREAD_NUM) {
////				logger.info("use:" + Constant.usedUrl.size());
////				logger.info("nouse:" + Constant.noUsedUrl.size());
////				while (true) {
////					String url = Constant.usedUrl.poll();
////					logger.info(url);
////					if (url == null)
////						break;
////				}
////				while (true) {
////					String url = Constant.noUsedUrl.poll();
////					logger.info(url);
////					if (url == null)
////						break;
////
////				}
////				break;
////			}
////			Thread.sleep(60000);
////		}
//
//    }
//
//
//}
