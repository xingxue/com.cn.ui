package com.execute;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basic.DriverFactory;
import com.basic.TestNGListener;
import com.example.Login;
import com.object.BasePage;

import io.appium.java_client.android.AndroidDriver;

@Listeners({ com.basic.TestNGListener.class })
public class LoginTest {

	AndroidDriver<?> driver = null;

	@Parameters({ "userName", "pwd" , "name1"})
	@Test(description = "通过我的tab登录")
	//测试前先确认输入的账号的我的用户名
	public void loginByTab(String userName, String pwd,String name1) throws Exception {
		new Login(driver).loginByTab(userName, pwd);
		Assert.assertEquals(new BasePage(driver, "threePage").findElement("我的用户名").getText(), name1);
	}

	@Parameters({ "userName", "pwdError" })
	@Test(description = "通过我的tab登录，登陆时密码不正确")
	public void loginByTabError(String userName, String pwdError) throws Exception {
		new Login(driver).loginByTab(userName, pwdError);
		Assert.assertFalse(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Test(description = "通过我的tab登录，登陆时空账号密码")
	public void loginByTabNull() throws Exception {
		new Login(driver).loginByTab("", "");
		Assert.assertFalse(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过发布按钮登录")
	public void loginByPublish(String userName, String pwd) throws Exception {
		new Login(driver).loginByPublish(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过评论按钮登录")
	public void loginByComment(String userName, String pwd) throws Exception {
		new Login(driver).loginByComment(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过点赞按钮登录")
	public void loginByLike(String userName, String pwd) throws Exception {
		new Login(driver).loginByLike(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过回复评论登录")
	public void loginByCommented(String userName, String pwd) throws Exception {
		new Login(driver).loginByCommented(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过文章评论登录")
	public void loginByCommentArticle(String userName, String pwd) throws Exception {
		new Login(driver).loginByCommentArticle(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过文章回复评论登录")
	public void loginByCommentedArticle(String userName, String pwd) throws Exception {
		new Login(driver).loginByCommentedArticle(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过文章点赞登录")
	public void loginByLikeArticle(String userName, String pwd) throws Exception {
		new Login(driver).loginByLikeArticle(userName, pwd);
		Assert.assertTrue(new BasePage(driver, "threePage").isElementDisplayed("我的用户名"));
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		driver = DriverFactory.createAndroidDriver("123.apk", "com.weikan.app.MainActivity");
		TestNGListener.setDriver(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
