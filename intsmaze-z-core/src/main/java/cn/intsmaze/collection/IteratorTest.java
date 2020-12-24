package cn.intsmaze.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/12/8 18:22
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<String> list=new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        for (int i = 0; i < list.size(); i++) {
            String str =  list.get(i);
            if(str.equals("3"))
            {
                list.remove(i);
            }
            System.out.println(list);
        }
    }
}
