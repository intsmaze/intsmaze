package cn.intsmaze.spiler.service;

import java.io.BufferedWriter;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cn.intsmaze.spiler.bean.ConcurrentHashSet;
import cn.intsmaze.spiler.bean.JobDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author:YangLiu
 * @date:2018年4月9日 下午4:20:56
 * @describe:
 */
public class Spiler {

    private static final Logger logger = LoggerFactory
            .getLogger(Spiler.class);

    protected static Gson gson = new Gson();

    protected static BlockingQueue<JobDescription> qcwuJobDescriptionQueue = new LinkedBlockingQueue<JobDescription>(1000000);

    protected static Set<String> qcwuKeySet = new ConcurrentHashSet(10000); //Collections.synchronizedSet(new HashSet<String>(100000));

    protected static String keyDate = "";

    protected BufferedWriter bw;

}
