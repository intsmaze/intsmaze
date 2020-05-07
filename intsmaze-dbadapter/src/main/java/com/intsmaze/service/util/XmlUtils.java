package com.intsmaze.service.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午3:01:37
 * @describe:
 */
public class XmlUtils {

	/**
	 * @Description: TODO
	 * @param: src/main/java/cn/hand/aml/service/dao.xml
	 * @return: void
	 * @throws DocumentException
	 */
	public static Map<String, String> readXMLForSql(String[] pathArr)
			throws DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		if (pathArr != null) {
			for (String path : pathArr) {
				InputStream in = ClassLoader.getSystemResourceAsStream(path);
				// File file = new File(path);
				SAXReader reader = new SAXReader();
				Document doc = reader.read(in);

				Element rootElement = doc.getRootElement();
				Element fooElement;

				for (Iterator i = rootElement.elementIterator("sql"); i
						.hasNext();) {
					fooElement = (Element) i.next();
					Attribute attribute = fooElement.attribute("id");
					String text = attribute.getText();
					map.put(text, fooElement.getText());
				}
			}
		}
		return map;
	}

}
