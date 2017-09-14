package com.basic;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.appium.java_client.android.AndroidDriver;

/**
 * testNG执行case 失败后 ，testNG Listener会捕获执行失败
 * 如果要实现失败自动截图，需要重写Listener的onTestFailure方法
 * 如果要实现失败重跑，需要重写Listener的onFinish方法
 * @author Charlie.chen
 */
public class TestNGListener extends TestListenerAdapter {

	private static AndroidDriver<?> driver;
	private static Logger log = Logger.getLogger(TestNGListener.class.getName());

	public static void setDriver(AndroidDriver<?> driver) {
		TestNGListener.driver = driver;
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info(tr.getName() + "Test Success" + "√√√√√√√√√√");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.error(tr.getName() + "Test Failure" + "××××××××××");
		log.info("异常原因：     " + tr.getThrowable().toString());
		new ScreenShot(driver,tr).getScreenShot();
		driver.resetApp();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error(tr.getName() + "Test Skipped");
		log.info("异常原因：     " + tr.getThrowable().toString());
		driver.resetApp();
		super.onTestSkipped(tr);
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		log.info("--------------" + tr.getName() + " Start" + "-----------");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		/**
		 * 重写onFinish方法
		 * 失败自动重跑，发现失败的用例在报告里生成了多份，移除失败的报告
		 */
		super.onFinish(testContext);
	    log.info("--------------" + "Test Finish" + "-----------");	
	        
	}

}