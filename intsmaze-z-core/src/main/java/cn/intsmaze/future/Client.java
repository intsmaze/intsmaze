package cn.intsmaze.future;
/** 
 * @author:YangLiu
 * @date:2018年6月3日 下午12:51:50 
 * @describe: 
 */
public class Client {

	public Data request(final String queryStr)
	{
		final FutureData futrue=new FutureData();
		new Thread(){
			@Override
            public void run()
			{
				RealData realData=new RealData(queryStr);//realData的构造很慢，所以在单独的线程中进行
				futrue.setRealData(realData);
			}
		}.start();
		return futrue;
	}
	
	public static void main(String[] args) {
		
		Client client=new Client();
		
		//这里会立刻返回，因为得到的是FutureData不是RealData
		Data data=client.request("name");
		System.out.println("请求完毕");
		
		
		try {
			//用一个sleep模拟其他业务的处理逻辑，在处理过程中，realData被创建，充分利用了等待的时间
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("数据="+data.getResult());
	}
}
