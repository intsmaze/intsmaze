package cn.intsmaze.dump;

import java.util.ArrayList;

/**
 * Created by 刘洋 on 2018/8/16.
 */
public class WatchGC {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws Exception {
        ArrayList<OOMDump.OOMIntsmaze> list = new ArrayList<OOMDump.OOMIntsmaze>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMDump.OOMIntsmaze());
        }
        System.gc();
    }


    public static void main(String[] args) throws Exception {
        fillHeap(100000);
        Thread.sleep(20000000);
    }
}
