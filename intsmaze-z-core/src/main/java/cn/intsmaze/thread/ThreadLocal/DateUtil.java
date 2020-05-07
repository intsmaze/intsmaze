//package cn.intsmaze.thread.ThreadLocal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by 刘洋 on 2018/8/22.
// */
//public class DateUtil {
//
//    /** 锁对象 */
//    private static final Object lockObj = new Object();
//
//    /** 存放不同的日期模板格式的sdf的Map */
////    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
//
//    private static ThreadLocal<SimpleDateFormat> threadLocal=new  ThreadLocal<SimpleDateFormat>();
//
//    /**
//     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
//     */
//    private static SimpleDateFormat getSdf(final String pattern) {
////        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
//
//        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
//        if (tl == null) {
//            synchronized (lockObj) {
//                tl = sdfMap.get(pattern);
//                if (tl == null) {
//                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
//                    System.out.println("put new sdf of pattern " + pattern + " to map");
//
//                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
//                    tl = new ThreadLocal<SimpleDateFormat>() {
//
//                        @Override
//                        protected SimpleDateFormat initialValue() {
//                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
//                            return new SimpleDateFormat(pattern);
//                        }
//                    };
//                    sdfMap.put(pattern, tl);
//                }
//            }
//        }
//
//        return tl.get();
//    }
//
//    /**
//     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
//     */
//    public static String format(Date date, String pattern) {
//        return getSdf(pattern).format(date);
//    }
//
//    public static Date parse(String dateStr, String pattern) throws ParseException {
//        return getSdf(pattern).parse(dateStr);
//    }
//
//}
