package cn.intsmaze.proxy.customImpl.two;


import cn.intsmaze.proxy.customImpl.Moveable;
import cn.intsmaze.proxy.customImpl.Tank;

public class Client {
	public static void main(String[] args) throws Exception {
		Tank t = new Tank();
		//这个类很重要
		InvocationHandler h = new TimeHandler(t);
		
		Moveable m = (Moveable)Proxy.newProxyInstance(Moveable.class,h);
		
		m.move();
	}
}
