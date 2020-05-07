package cn.intsmaze.jmx;

/**
 * @author:YangLiu
 * @date:2017年12月10日 下午6:18:39
 * @describe:
 */
public interface HelloMBean {
	public String getName();

	public void setName(String name);

	public String printHello();

	public String printHello(String whoName);
}
