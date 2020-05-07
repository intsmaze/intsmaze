package cn.intsmaze.io.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by 刘洋 on 2018/10/16.
 */
public class IndexFile {



    public static void main(String args[]) throws IOException {

        insertData();

    }


    public static void insertData() throws IOException {

        RandomAccessFile raf=new RandomAccessFile(new File("D:\\test.txt"), "rw");

        System.out.println("向文件中写入");
        raf.write('A');   //写入一个字符，只会写入第八位
        int i=112324;
        //用write方法每次只能写一个字节，如果要把i写进去需要写4次
        raf.write(i>>>24);
        raf.write(i>>>16);
        raf.write(i>>>8);
        raf.write(i);
        raf.writeInt(i);                         //也可以直接写入一个整数，占4个字节，底层实现就是上面的方法
        raf.writeUTF("这是一个UTF字符串");         // 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
        raf.writeDouble(8.236598);               // 占8个字节
        raf.writeBoolean(true);                  // 占1个字节
        raf.writeShort(395);                     // 占2个字节
        raf.writeLong(2325451L);                 // 占8个字节
        raf.writeFloat(35.5f);                   // 占4个字节
        raf.writeChar('a');                      // 占2个字节
        System.out.println(raf.getFilePointer());
        long position=raf.getFilePointer();//返回文件记录指针的当前位置
        String string="中国人intsmaze";
        byte [] by=string.getBytes();
        raf.write(by);
        int length=by.length;
        System.out.println(raf.getFilePointer());

        string="我要开始装13了";
        by=string.getBytes();
        raf.write(by);
        raf.seek(77);
        byte [] buffer1=new byte[length];
        raf.read(buffer1);
        System.out.println( new String (buffer1));




//        //读取文件，把指针移到读取的位置---利用的是seek（）
//        raf.seek(0);                                                 ///将文件记录指针定位到pos的位置
//        //一次性读取，就是把文件中的内容都读取到字节数组中
//        byte [] buffer=new byte[(int)raf.length()];
//        raf.read(buffer);
//        //将文件一次性读取到字节数组中
//        System.out.println("输出文件全部内容：");
//        System.out.println(Arrays.toString(buffer));
//        for (byte b : buffer) {
//            System.out.print(Integer.toHexString(b &0xff)+" ");      //以十六进制输出
//        }
//        System.out.println();
//        raf.seek(2);                                                //将指针移到第三个字节位置
//        int readInt = raf.readInt();                                //读取一个整数
//        System.out.println(readInt);
//        raf.skipBytes(4);                                           //跳过一个int
//        System.out.println(raf.readUTF());                          //输出UTF语句

    }
}
