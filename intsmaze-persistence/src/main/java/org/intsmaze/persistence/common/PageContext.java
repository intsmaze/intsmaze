/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.common;

import org.apache.log4j.Logger;


public class PageContext extends   Page  {

	private static final Logger logger = Logger.getLogger(PageContext.class);

	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();


	public static PageContext getContext()
	{
		PageContext ci = context.get();
		if(ci == null)
		{
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}

	public  static void removeContext()
	{
		context.remove();
	}

	protected void initialize() {

		

	}
	


	
	
	

	
}
