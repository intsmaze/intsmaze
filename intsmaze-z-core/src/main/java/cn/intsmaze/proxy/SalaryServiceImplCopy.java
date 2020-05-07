package cn.intsmaze.proxy;

public class SalaryServiceImplCopy {

	public void showSalary(String age) {
		System.out.println("查看工资");
	}

	public void useSalary() {
		System.out.println("工资用完了");
	}

	@Override
	public String toString() {
		return "SalaryServiceImplCopy [getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
