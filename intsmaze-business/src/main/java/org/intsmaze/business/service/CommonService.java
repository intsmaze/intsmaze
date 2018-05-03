package org.intsmaze.business.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.intsmaze.core.util.FDFBSqlTimestampConvertor;

/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-23 上午8:06:46
 * @version :
 * 
 */
public abstract class CommonService {
	
	static {  
	    ConvertUtils.register(new LongConverter(null), Long.class);  
	    ConvertUtils.register(new ShortConverter(null), Short.class);  
	    ConvertUtils.register(new IntegerConverter(null), Integer.class);  
	    ConvertUtils.register(new DoubleConverter(null), Double.class);  
	    ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);  
//	    ConvertUtils.register(new FDFBSqlDateConvertor(null), java.sql.Date.class);//自定义的conver，用于防止vo中date类型为空时，不报错 
	    ConvertUtils.register(new FDFBSqlTimestampConvertor(null), java.sql.Timestamp.class); //自定义的convertor，用于防止vo中timestamp类型为空时，报错
	} 
	
	/**
	 * vo转po
	 * @param voObj
	 * @return
	 */
	public Object copyBean(Object sourceObj, Object targetObj) {
		if(sourceObj == null || targetObj == null)
		{
			return null;
		}
		try {
			BeanUtils.copyProperties(targetObj, sourceObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetObj;
	}
	
	/**
	 * vo转po
	 * @param voObj
	 * @return
	 */
	public List copyList(List list, Class sourceClazz, Class targetClazz) {
		
		if(list==null || list.size() <1)
		{
			return null;
		}
		else
		{
			List resultList = new ArrayList(list.size());
			try {
				for(int i=0,n=list.size(); i<n; i++)
				{
					Object sourceObj = list.get(i);
					Class<?> targetObjName = null;  
			        try {  
			        	targetObjName = Class.forName(targetClazz.getName());
			        	Object targetObj = targetObjName.newInstance();
			        	BeanUtils.copyProperties(targetObj, sourceObj);
			        	resultList.add(targetObj);
			        } catch (ClassNotFoundException e) {  
			            e.printStackTrace();  
			        } catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultList;
		}
	}
	

}
