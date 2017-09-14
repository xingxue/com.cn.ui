package com.basic;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import com.appium.TimeNow;

import io.appium.java_client.android.AndroidDriver;
/**
 * 
 * @author xingxue
 * 截屏方法
 * 获取当前时间
 */
public class ScreenShot {

	 AndroidDriver<?> driver;

     Logger log = Logger.getLogger(ScreenShot.class.getName());

	private String path;

	public ScreenShot(AndroidDriver<?> driver,ITestResult tr){
        this.driver=driver;
        path = System.getProperty("user.dir") + "//snapshot//" + tr.getName() + "_"
				+ new TimeNow().getTimeymdhms() + ".png";
    }
	
	public ScreenShot(AndroidDriver<?> driver,String num){
		this.driver=driver;
        path = System.getProperty("user.dir") + "//snapshot-compare//" + num + ".png";
    }
	
	/**
	 * 截屏功能
	 * 截屏并存储到项目的snapshot文件夹下
	 * @param driver
	 */
	public void getScreenShot() {
		File screen = driver.getScreenshotAs(OutputType.FILE);
        File screenFile = new File(path);
		try {
			 FileUtils.copyFile(screen, screenFile);
			 log.info("截图保存的路径:" + path);
		} catch (Exception e) {
			log.error("----------截屏失败----------");
		}
	}
	
	/**
	 * 获取截屏路径
	 * @return
	 */
	public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
