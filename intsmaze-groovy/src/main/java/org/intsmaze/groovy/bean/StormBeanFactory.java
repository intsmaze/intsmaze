package org.intsmaze.groovy.bean;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

public class StormBeanFactory extends DefaultListableBeanFactory {

	public static final String SPRING_BEAN_FACTORY_XML = "springframework.bean.factory.xml";

	private transient final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);

	private String xml;

	public StormBeanFactory(String location) {
		super();
		try {
			Resource resource = reader.getResourceLoader().getResource(location);
			URL url = resource.getURL();
			this.xml = Resources.toString(url, Charsets.UTF_8);
			this.reader.setValidating(false);
			this.reader.loadBeanDefinitions(resource);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public StormBeanFactory(Map stormConf) {
		this.xml = (String) stormConf.get(SPRING_BEAN_FACTORY_XML);
		this.reader.setValidating(false);
		this.reader.loadBeanDefinitions(new ByteArrayResource(xml.getBytes(Charsets.UTF_8)));
	}

	public String getXml() {
		return xml;
	}

}
