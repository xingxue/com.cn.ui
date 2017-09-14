package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.appium.compare;
import com.object.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class searchArticle {

	private AndroidDriver<?> driver;

	public searchArticle(AndroidDriver<?> driver) {
		this.driver = driver;
	}

	/**
	 * 搜索功能，按照阅读数排序 验证阅读数是否是从高到底
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchArticleReadNumber() throws Exception {
		String a1 = new BasePage(driver, "searchPage").findElementTrue("搜索列表阅读数").getText();
		for (int i = 0; i < 3; i++) {
			new BasePage(driver, "searchPage").swipeToUp(); // 执行三次上滑
		}
		String a2 = new BasePage(driver, "searchPage").findElementTrue("搜索列表阅读数").getText();
		// System.out.println(a1);
		// System.out.println(a2);
		boolean f = compare.numberCompare(a1, a2); // 比较阅读数大小,a1大于a2为true
		new BasePage(driver, "commonButton").click("返回按钮");
		new BasePage(driver, "searchPage").click("搜索页面返回按钮");
		System.out.println(f);
		return f;
	}

	/**
	 * 搜索功能，搜索一周前两周前一个月前的文章 判断文章否是一周前，两周前，一个月前的
	 * 
	 * @param text
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchArticleTime(String text) throws Exception {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String time = new BasePage(driver, "searchPage").findElement("搜索列表时间").getText();
		time = "2017年" + time;
		// System.out.println(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日"); // 时间形式年月日
		Date date1 = formatter.parse(time);
		// System.out.println(date1);
		Date date2 = formatter.parse(formatter.format(new Date()));
		// System.out.println(date2);
		long time1 = date2.getTime() - date1.getTime(); // 输出时间间隔
		// System.out.println(time1);
		new BasePage(driver, "commonButton").click("返回按钮");
		new BasePage(driver, "searchPage").click("搜索页面返回按钮");
		if (text.equals("一个月前")) {
			if (time1 > 2505500000L) { // 一个月时间60*60*24*29*1000
				return true;
			} else {
				return false;
			}
		} else if (text.equals("两周前")) { // 两周时间60*60*24*14*1000
			if (time1 > 1209000000) {
				return true;
			} else {
				return false;
			}
		} else if (text.equals("一周前")) { // 一周时间60*60*24*7*1000
			if (time1 > 604750000) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	
	/**
	 * 验证上下拉功能
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchDecide() throws Exception {
		new BasePage(driver, "searchPage").swipeToDownNumber(5);
		new BasePage(driver, "searchPage").swipeToUpNumber(15);
		new BasePage(driver, "commonButton").click("返回按钮");
		new BasePage(driver, "searchPage").click("搜索页面返回按钮");
		return true;
	}

	/**
	 * 搜索功能，进入搜索列表 验证页面是否为空
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchArticleList(String text) throws Exception {
		new BasePage(driver, "tab栏").click("tab首页");
		new BasePage(driver, "firstPage").click("搜索按钮");
		// System.out.println(text);
		/**
		 * if通过搜索按钮进入 else通过搜索框进入
		 */
		if (text.equals("阅读量从高到低")) {
			new BasePage(driver, "searchPage").click("阅读量从高到低");
		} else if (text.equals("只看头条")) {
			new BasePage(driver, "searchPage").click("只看头条");
		} else if (text.equals("一周前")) {
			new BasePage(driver, "searchPage").click("一周前");
		} else if (text.equals("两周前")) {
			new BasePage(driver, "searchPage").click("两周前");
		} else if (text.equals("一个月前")) {
			new BasePage(driver, "searchPage").click("一个月前");
		} else {
			new BasePage(driver, "searchPage").type("搜索框", text);
			excuteAdbShell("adb shell ime set com.sohu.inputmethod.sogou/.SogouIME"); // 更改为搜狗输入法
			new BasePage(driver, "searchPage").click("搜索框");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.pressKeyCode(AndroidKeyCode.ENTER); // 点击右下角的搜索，即ENTER键
			excuteAdbShell("adb shell ime set io.appium.android.ime/.UnicodeIME"); // 变更回默认输入法
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean list = new BasePage(driver, "searchPage").isElementDisplayed("搜索列表阅读数");
		if (text.equals("只看头条")) {
			/**
			 * 点击搜索 头条不判断大小等所以要返回进行下次操作
			 */
			new BasePage(driver, "commonButton").click("返回按钮");
			new BasePage(driver, "searchPage").click("搜索页面返回按钮");
		}
		else if (list == false) {
			/**
			 * 搜索框搜索 如果输入为false,空页面不做比较,所以要返回进行下次操作
			 */
			if(!text.equals("1234567890")) {
				 new BasePage(driver, "commonButton").click("返回按钮");
				 new BasePage(driver, "searchPage").click("搜索页面返回按钮");
			 }
			
		}
		return list;
	}

	/**
	 * 执行adb命令
	 * 
	 * @param s
	 */
	private void excuteAdbShell(String s) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(s);
		} catch (Exception e) {
			System.out.println("执行命令:" + s + "出错");
		}
	}

	/**
	 * 搜索框进入,判断搜索的文字是否在标题中存在
	 * 
	 * @param text
	 * @return boolean
	 * @throws Exception
	 */
	public boolean searchArticleCompareContent(String text) throws Exception {
		int number = 0;
		String y = new BasePage(driver, "searchPage").findElement("搜索列表标题").getText();
		new BasePage(driver, "commonButton").click("返回按钮");
		new BasePage(driver, "searchPage").click("搜索页面返回按钮");
		for (int i = 0; i < y.length(); i++) {
			if (y.indexOf(text) != -1) { // 判断text是否在y中存在
				number = 1;
			}
		}
		if (number == 1) {
			return true;
		} else {
			return false;
		}
	}
}
