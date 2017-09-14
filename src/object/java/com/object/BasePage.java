package com.object;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.appium.AppiumExecutorImpl;
import com.basic.XmlUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BasePage extends AppiumExecutorImpl {

	    protected AndroidDriver<?> driver;
	    protected String pageName;  //页面名称
	    protected String xmlPath;   //页面元素路径
	    protected HashMap<String, Locator> locatorMap;
	    public Logger log = Logger.getLogger(BasePage.class.getName());
	    
	    public BasePage(AppiumDriver<?> driver,String pageName) throws Exception  {
			super((AndroidDriver<?>) driver);
			this.driver = (AndroidDriver<?>) driver;
			this.pageName=pageName;
			
			//获取资源文件page.xml的路径
			xmlPath=System.getProperty("user.dir")+"\\src\\object\\java\\com\\object\\page.xml";	
	    	//xmlPath=BasePage.class.getClassLoader().getResource("page.xml").getPath();
			
			//locatorMap = XmlUtils.readXMLDocument(xmlPath, this.getClass().getSimpleName());
			locatorMap = XmlUtils.readXMLDocument(xmlPath, pageName);

		}
	    
	    public void type(String locatorName, String values) {
	        super.type(getLocator(locatorName), values );
	    }


	    public void click(String locatorName) {
	        super.click(getLocator(locatorName));
	    }
	    
	    public void click(String locatorName, int n) {
	        super.click(getLocator(locatorName),n);
	    }
	    
	    public void clear(String locatorName) {
	    	super.clear(getLocator(locatorName));

		}


	    public String getText(String locatorName) {
	        return super.getText(getLocator(locatorName));
	    }


	    public MobileElement findElement(String locatorName) {
	        return super.findElement(getLocator(locatorName));
	    }
	    
	    public MobileElement findElementNumber(String locatorName, int n) {
	        return super.findElementNumber(getLocator(locatorName), n);
	    }

	    public boolean isElementDisplayed(String locatorName) {
	        return super.isElementDisplayed(getLocator(locatorName));
	    }
	    
	    public MobileElement findElementTrue(String locatorName) {
	        return super.findElementTrue(getLocator(locatorName));
	    }
	    
	    public void swipeToLeft(String locatorName) {
	         super.swipeToLeft(getLocator(locatorName));
	    }
	    
	    public void swipeToRight(String locatorName) {
	        super.swipeToRight(getLocator(locatorName));
	    }
	    
	    public void swipeToUp(String locatorName) {
	         super.swipeToUp(getLocator(locatorName));
	    }
	    
	    public void swipeToDown(String locatorName) {
	         super.swipeToDown(getLocator(locatorName));
	    }
	    
	    public  Locator getLocator(String locatorName) {

	        Locator locator =  null;

	        if(locatorMap!=null)
	        {
	            locator = locatorMap.get(locatorName);
	        }
	        return locator;
	    }
}
