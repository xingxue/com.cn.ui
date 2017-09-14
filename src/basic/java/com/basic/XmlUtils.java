package com.basic;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.object.Locator;
import com.object.Locator.ByType;

public class XmlUtils {


	public static HashMap<String, Locator> readXMLDocument(String xmlPath,String pageName) throws Exception {

		Logger log = Logger.getLogger(XmlUtils.class.getName());
		
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
		
		File file = new File(xmlPath);
		if (!file.exists()) {
			log.error("Can't find " + xmlPath);
			return locatorMap=null;
		}
		
		//创建SAXReader对象
        SAXReader reader = new SAXReader();
        //读取文件 转换成Document 
        Document document = reader.read(file);
        //获取根节点元素对象
        Element root = document.getRootElement();
		
        //遍历
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element page = (Element) i.next();
			if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
				log.info("pageName is:" + pageName);
				
				for (Iterator<?> l = page.elementIterator(); l.hasNext();) {
					String type = null;
//					String timeOut = "1";
					String value = null;
					String locatorName = null;
					Element locator = (Element) l.next();
					
					for (Iterator<?> j = locator.attributeIterator(); j.hasNext();) {
						Attribute attribute = (Attribute) j.next();
						if (attribute.getName().equals("type")) {
							type = attribute.getValue();
							//log.info("get locator type " + type);
						} 
//						else if (attribute.getName().equals("timeOut")) {
//							timeOut = attribute.getValue();
//							//log.info("get locator timeOut " + timeOut);
//						} 
						else {
							value = attribute.getValue();
							//log.info("get locator value " + value);
						}

					}
					Locator temp = new Locator(value, getByType(type));
					locatorName = locator.getText();
					//log.info("locatorName is " + locatorName);
					locatorMap.put(locatorName, temp);
				}
				continue;
			}

		}
		return locatorMap;

	}
	
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
	    } else if (type.equalsIgnoreCase("AndroidUIAutomator")) {
		    byType = ByType.AndroidUIAutomator;
	    }
		return byType;
	}

}
