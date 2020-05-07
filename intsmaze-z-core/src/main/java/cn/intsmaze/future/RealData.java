package cn.intsmaze.future;
/** 
 * @author:YangLiu
 * @date:2018年6月3日 下午12:43:48 
 * @describe: 
 */
public class RealData implements Data {

	protected  final String result;
	
	public RealData(String para) {
		//realData构造很慢，需要用户等待很久，使用sleep模拟
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<10;i++)
		{
			sb.append(para);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		result=sb.toString();
	}
	
	@Override
	public String getResult() {
		return result;
	}
}
