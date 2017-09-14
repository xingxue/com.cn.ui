package com.execute;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basic.DriverFactory;
import com.basic.TestNGListener;
import com.example.searchArticle;

import io.appium.java_client.android.AndroidDriver;

@Listeners({ com.basic.TestNGListener.class, com.basic.RetryListener.class })
public class searchArticleTest {

	private AndroidDriver<?> driver;

	@Parameters({ "text1" })
	@Test(description = "按照内容搜索text1")
	public void searchArticleForContentText1(String text1) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text1));
		assertTrue(new searchArticle(driver).searchArticleCompareContent(text1));
	}

	@Parameters({ "text2" })
	@Test(description = "按照内容搜索text2")
	public void searchArticleForContentText2(String text2) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text2));
		assertTrue(new searchArticle(driver).searchArticleCompareContent(text2));
	}

	@Parameters({ "text3" })
	@Test(description = "按照内容搜索text3")
	public void searchArticleForContentText3(String text3) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text3));
		assertTrue(new searchArticle(driver).searchArticleCompareContent(text3));
	}

	@Parameters({ "text4" })
	@Test(description = "按照内容搜索text4")
	public void searchArticleForContentText4(String text4) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text4));
		assertTrue(new searchArticle(driver).searchArticleCompareContent(text4));
	}

	@Parameters({ "text5" })
	@Test(description = "按照内容搜索text5")
	public void searchArticleForContentText5(String text5) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text5));
		assertTrue(new searchArticle(driver).searchArticleCompareContent(text5));
	}

	@Parameters({ "text6" })
	@Test(description = "按照内容搜索text6")
	public void searchArticleForContentText6(String text6) throws Exception {
		assertFalse(new searchArticle(driver).searchArticleList(text6));
	}

	@Parameters({ "text7" })
	@Test(description = "按照内容搜索text7")
	public void searchArticleForContentText7(String text7) throws Exception {
		assertFalse(new searchArticle(driver).searchArticleList(text7));
	}

	@Parameters({ "text8" })
	@Test(description = "按照内容搜索text8")
	public void searchArticleForContentText8(String text8) throws Exception {
		assertFalse(new searchArticle(driver).searchArticleList(text8));
	}

	@Parameters({ "text9" })
	@Test(description = "按照内容搜索text9")
	public void searchArticleForContentText9(String text9) throws Exception {
		assertFalse(new searchArticle(driver).searchArticleList(text9));
	}
	
	@Parameters({ "text1" })
	@Test(description = "有数据搜索上下拉是否有异常")
	public void searchDecide1(String text1) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(text1));
		assertTrue(new searchArticle(driver).searchDecide());
	}
	
	@Parameters({ "text10" })
	@Test(description = "无数据搜索上下拉是否有异常")
	public void searchDecide2(String text10) throws Exception {
		assertFalse(new searchArticle(driver).searchArticleList(text10));
		assertTrue(new searchArticle(driver).searchDecide());
	}

	@Parameters({ "searchName1" })
	@Test(description = "搜索按照阅读量高低排序")
	public void searchArticleReadNumber(String searchName1) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(searchName1));
		assertTrue(new searchArticle(driver).searchArticleReadNumber());
	}

	@Parameters({ "searchName2" })
	@Test(description = "搜索头条")
	public void searchArticleTopList(String searchName2) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(searchName2));
	}

	@Parameters({ "searchName3" })
	@Test(description = "搜索一周前列表")
	public void searchArticleWeekList(String searchName3) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(searchName3));
		assertTrue(new searchArticle(driver).searchArticleTime(searchName3));
	}

	@Parameters({ "searchName4" })
	@Test(description = "搜索两周前数据列表")
	public void searchArticleTowweekList(String searchName4) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(searchName4));
		assertTrue(new searchArticle(driver).searchArticleTime(searchName4));
	}

	@Parameters({ "searchName5" })
	@Test(description = "搜索一个月前数据列表")
	public void searchArticleMonthList(String searchName5) throws Exception {
		assertTrue(new searchArticle(driver).searchArticleList(searchName5));
		assertTrue(new searchArticle(driver).searchArticleTime(searchName5));
	}

	@BeforeClass
	public void beforeClass() {
		driver = DriverFactory.createAndroidDriver("123.apk", "com.weikan.app.MainActivity");
		TestNGListener.setDriver(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
