package org.intsmaze.core.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

	public static Cookie getCookie(Cookie[] cookies,String name){
		
		if(name == null)
			return null;
		
		for(Cookie c:cookies){
			if(name.equals(c.getName())){
				return c;
			}
		}
		
		return null;
		
	}
	
	
	
	
	
}
