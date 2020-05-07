package cn.intsmaze.proxy.customImpl.one;


import cn.intsmaze.proxy.customImpl.Moveable;
import cn.intsmaze.proxy.customImpl.Tank;

public class Client {
	public static void main(String[] args) throws Exception {
		Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class, new Tank());// 这种方式，每次生成的都是对指定的一个实现类比如Tank的代理类，如果我们想要
		// 实现该接口的其他实现类的代理类就要再写Proxy类了，所以我们应该思考，在生成指定接口代理的同时，不仅可以随意传接口，也可以随意指定接口的实现类，我们真正要代理的类。
		m.move();
	}
}
