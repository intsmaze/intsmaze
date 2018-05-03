package org.intsmaze.business.velocityTools;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class VMResourceLoader implements ResourceLoader{
	
	private ClassLoader classLoader;
	
	public VMResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}
	
	public VMResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	
	public Resource getResource(String location) {
		// TODO Auto-generated method stub
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(CLASSPATH_URL_PREFIX)) {
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
		}
		else {
			try {
				// Try to parse the location as a URL...
				URL url = new URL(location);
				return new UrlResource(url);
			}
			catch (MalformedURLException ex) {
				// No URL -> resolve as resource path.
				return null;
			}
		}
	}

	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
	}

}
