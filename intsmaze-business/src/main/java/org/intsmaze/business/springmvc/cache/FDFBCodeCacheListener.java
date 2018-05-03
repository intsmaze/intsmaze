package org.intsmaze.business.springmvc.cache;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

public class FDFBCodeCacheListener implements CacheEventListener {

	private static Logger logger = Logger.getLogger(FDFBCodeCacheListener.class);
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void notifyElementEvicted(Ehcache arg0, Element arg1) {
		// TODO Auto-generated method stub
		logger.info("=============notifyElementEvicted=============");
	}

	public void notifyElementExpired(Ehcache arg0, Element arg1) {
		// TODO Auto-generated method stub
		logger.info("=============notifyElementExpired=============");
	}

	public void notifyElementPut(Ehcache arg0, Element arg1)
			throws CacheException {
		// TODO Auto-generated method stub
		logger.info("=============notifyElementPut=============");
	}

	public void notifyElementRemoved(Ehcache arg0, Element arg1)
			throws CacheException {
		// TODO Auto-generated method stub
		logger.info("=============notifyElementRemoved=============");
	}

	public void notifyElementUpdated(Ehcache arg0, Element arg1)
			throws CacheException {
		// TODO Auto-generated method stub
		logger.info("=============notifyElementUpdated=============");
	}

	public void notifyRemoveAll(Ehcache arg0) {
		// TODO Auto-generated method stub
		logger.info("=============notifyRemoveAll=============");
	}

	public Object clone() throws CloneNotSupportedException {  
	      throw new CloneNotSupportedException();  
	   }}
