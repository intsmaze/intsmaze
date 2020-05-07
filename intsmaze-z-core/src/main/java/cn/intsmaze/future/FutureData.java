package cn.intsmaze.future;
/** 
 * @author:YangLiu
 * @date:2018年6月3日 下午12:43:14 
 * @describe: FutureData是future模式的关键，是真实数据RealData的代理，封装了获取RealData的操作
 */
public class FutureData implements Data {
	
	protected RealData realData=null;
	
	protected boolean isReady=false;

	public synchronized void setRealData(RealData realData) {
		if(isReady)
		{
			return;
		}
		this.realData=realData;
		isReady=true;
		notifyAll();//realData已经被注入，通知getResult()
	}

	@Override
    public synchronized String getResult() {
		while(!isReady)
		{
			try {
				wait();//一直等待，直到realData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.result;
	}
}
