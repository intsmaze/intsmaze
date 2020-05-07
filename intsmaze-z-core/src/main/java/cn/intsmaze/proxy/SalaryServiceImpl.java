package cn.intsmaze.proxy;

public class SalaryServiceImpl implements SalaryService {

	@Override
    public String showSalary(String str) {
		System.out.println("查看工资"+str);
		return "("+str+")";
	}

	@Override
    public void useSalary() {
		System.out.println("工资用完了");
	}

}
