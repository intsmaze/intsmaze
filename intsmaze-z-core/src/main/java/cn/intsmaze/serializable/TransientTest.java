package cn.intsmaze.serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class TransientTest {
    
    public static void main(String[] args) {
       
        User user = new User("=-*/");
        User.passwd ="123456";
        
        System.out.println("read before Serializable: ");
        System.err.println("password:1 " + User.passwd);
        System.err.println("Name:1 " + user.name);
        
        try {
            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream("d:/user.txt"));
            os.writeObject(user); // 将User对象写进文件
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        User.passwd="979222969";
        
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "d:/user.txt"));
            User user1 = (User) is.readObject(); // 从流中读取User的数据
            is.close();
            
            System.out.println("\nread after Serializable: ");
            System.err.println("password:2 " + User.passwd);
            System.err.println("Name:2 " + user1.name);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}