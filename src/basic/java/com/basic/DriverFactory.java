package com.basic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.object.BasePage;

import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {
	
	public static AndroidDriver<?> driver;
	static Logger log = Logger.getLogger(ScreenShot.class.getName());

	@SuppressWarnings("rawtypes")
	public static AndroidDriver<?> createAndroidDriver(String apkName, String appActivity) {

		File apk = new File(System.getProperty("user.dir") + File.separator + "apps" + File.separator + apkName);

		DesiredCapabilities capabilities = new DesiredCapabilities();

//		capabilities.setCapability("deviceName", "HUAWEI MT7-TL10");
//		capabilities.setCapability("deviceName", "小米手机");
		capabilities.setCapability("deviceName", "HUAWEI Mate 8");

		capabilities.setCapability("platformVersion", "7.0");

		capabilities.setCapability("app", apk);

		capabilities.setCapability("appActivity", appActivity);
		
//		capabilities.setCapability("appActivity", appActivity);

		capabilities.setCapability("noSign", true);

		capabilities.setCapability("noReset", true);

		capabilities.setCapability("unicodeKeyboard", "True");

		capabilities.setCapability("resetKeyboard", "True");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			log.info("APP启动成功");
		} catch (MalformedURLException e) {
			log.error("----------APP启动失败---------");
		}
		
		return driver;
	}
	
	public static void main(String[] args) throws Exception {
		DriverFactory.createAndroidDriver("123.apk", "com.weikan.app.MainActivity");
//		new BasePage(driver, "tab栏").click("tab首页");
//		new BasePage(driver, "firstPage").click("搜索按钮");
//		new BasePage(driver, "searchPage").click("阅读量从高到低");
		Thread.sleep(5000);
//		driver.findElement(By.)
//		driver.findElement(By.id("com.paiba.app000004:id/im_search")).click();;
		new BasePage(driver,"tab栏").click("tab首页");
		
//System.out.println(f);
//driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").index(2).clickable(true)").click();
//new BasePage(driver,"tab栏").click("tab我的");
new BasePage(driver,"firstPage").click("首页列表");
boolean g = new BasePage(driver,"commonButton").isElementDisplayed("返回按钮");
System.out.println(g);
driver.findElement(By.id("com.paiba.app000004:id/iv_titlebar_back")).click();
//		driver.findElementByAccessibilityId("com.paiba.app000004:id/im_search");
//		driver.findElementByAndroidUIAutomator(using)
//		driver.findElementByClassName(using)
//		driver.findElementByCssSelector(using)
//		driver.findElementById(id)
//		driver.findElementByLinkText(using)
//		driver.findElementByName(using)
//		driver.findElementByPartialLinkText(using)
//		driver.findElementByTagName(using)
//		driver.findElementByXPath(using)
		
		Thread.sleep(5000);
		System.out.println("成功");
//	    new BasePage(driver,"firstPage").click("分类");
//		new BasePage(driver,"firstPage").swipeToLeft("分类栏");
//		new AppiumExecutorImpl(driver).swipeToLeft();
		//System.out.println(a);
//		new AppiumExecutorImpl(driver).swipeToLeft();
//		driver.findElement(By.name("我的")).click();
//		driver.findElement(By.id("com.paiba.app000004:id/iv_mine_login_avatar")).click();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		 driver.tap(1,500,1500,0);
//		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		 driver.findElement(By.xpath("//android.widget.TabWidget/android.widget.RelativeLayout/android.widget.TextView[contains(@text,'风云会')]"));
//		 System.out.println("成功"); 
//		new BasePage(driver,"tab栏").click("tab我");
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		new BasePage(driver,"firstPage").swipeToLeft();
		
		driver.quit();
	}

}
