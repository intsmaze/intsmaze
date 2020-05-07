package cn.intsmaze.serializable;
import java.io.Serializable;


public class Name implements Serializable {

	private static final long serialVersionUID = -3749898951145883162L;
	
	public String name;

	public Name(String name) {
		this.name = name;
	}
	
	public static void main(String[] args)
	{
		System.out.println("田萌你妈妈喊你去吃麻辣烫!!!!");
	}
}
