package cn.intsmaze.serializable;
import java.io.Serializable;


public class User extends Name implements Serializable {
	
    public User(String name) {
		super(name);
	}

	private static final long serialVersionUID = 8294180014912103005L;  
    
    public static String passwd;
    
}
