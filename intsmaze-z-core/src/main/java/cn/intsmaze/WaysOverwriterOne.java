package cn.intsmaze;

public class WaysOverwriterOne {
	/**
	 * 重载方法的规则 重载的方法参数列表各不相同 -----------------------
	 * 重载方法的返回值类型和访问限制没有特殊要求，可以相同也可以不同。 ---------------------------
	 */

	/**
	 * 基本类型参数 当注释掉int参数函数，那么当第一次调用时传入两个int型参数，系统会将两个int型参数提升为double类型，
	 * 然后调用两个double类型参数 的add方法。
	 * 总结，在方法进行匹配时，首先选择直接匹配的方法，如果没有，则将参数进行提升转换后再匹配方法，总是匹配最接近 的方法。只能进行类型的提升，不能下降。
	 * */
	public int add(int i, int j) {
		System.out.print("两个int参数的方法被调用，");
		return i + j;
	}

	// 接收两个double型参数执行加法
	public double add(double i, double j) {
		System.out.print("两个double参数的方法被调用，");
		return i + j;
	}

	/**
	 * 引用类型参数 与基本类型相同，总是匹配最兼容该参数的方法。
	 * 在下列代码中，重载方法有Vehicle类型和Car类型参数，但向其传递的Truck引用寻找的是带Car的参数方法，虽然
	 * Vehicle型与Car型均能与其兼容，但Car型最能兼容，因为Car类是Truck的直接父类。
	 * */
	// 该方法参数为Vehicle型
	public void show(Vehicle v) {
		System.out.println("调用的是具有Vehicle参数的方法！！！");
	}

	// 该方法参数为Car型
	public void show(Car c) {
		System.out.println("调用的是具有Car参数的方法！！！");
	}

	public static void main(String[] args) {
		WaysOverwriterOne a = new WaysOverwriterOne();
		System.out.println("2+5=" + a.add(2, 5));
		System.out.println("5+30.8=" + a.add(5, 30.8));

		Vehicle v = new Vehicle();
		Car c = new Car();
		Truck t = new Truck();
		a.show(v);
		a.show(c);
		a.show(t);
		
		
		/**
		 * ------------ 如果多态，那么匹配的是引用类型的参数，引用决定调用哪个重载方法，而不是对象类型。因为编译时系统知道的是 引用类型
		 * */
		Vehicle v1 = new Car();
		a.show(v1);
		
		v1 = new Truck();
		a.show(v1);
		
		Car c1 = new Truck();
		a.show(c1);
	}
}

class Vehicle {
}

// Car类继承自Vehicle类
class Car extends Vehicle {
}

// Truck类继承自Car类
class Truck extends Car {
}