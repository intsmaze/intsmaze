package com.intsmaze.spiler.service;

import java.io.BufferedWriter;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.intsmaze.spiler.bean.ConcurrentHashSet;
import com.intsmaze.spiler.bean.JobDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2018/9/10 19:32
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
