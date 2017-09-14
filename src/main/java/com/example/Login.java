package com.example;

import java.util.concurrent.TimeUnit;

import com.object.BasePage;

import io.appium.java_client.android.AndroidDriver;

/**
 * 登录功能
 * @author xingxue
 *
 */
public class Login {
	
	private AndroidDriver<?> driver;

	public Login(AndroidDriver<?> driver) {
		this.driver = driver;
	}
	
	/**
	 * 通过tab登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByTab(String userName,String pwd) throws Exception{
//		    new BasePage(driver,"tab栏").click("tab首页");
        	new BasePage(driver,"tab栏").click("tab我的");
    		isLogined(); 
			login(userName,pwd);
    }
	
	/**
	 * 通过tab微信登录
	 * @param userNameWeixin
	 * @param pwdWeixin
	 * @throws Exception
	 */
//	public void loginByTabWeinxin(String userNameWeixin,String pwdWeixin) throws Exception{
//		new BasePage(driver,"tab栏").click("tab我的");
//		isLogined();
//		new BasePage(driver,"loginPage").click("微信");	
//		boolean d = new BasePage(driver,"loginPageWeixin").isElementDisplayed("微信登录");
//		if(d==true) {
//			loginWeixin(userNameWeixin,pwdWeixin);
//		}
//		loginWeinxinTrue();
//    }
	
	/**
	 * 通过发布按钮登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByPublish(String userName,String pwd) throws Exception{
			new BasePage(driver,"tab栏").click("tab我的");
			isLogined(); 
			new BasePage(driver,"tab栏").click("tab文友圈");
			new BasePage(driver,"twoPage").click("发布文友圈");
			login(userName,pwd);
			new BasePage(driver,"tab栏").click("tab我的");
		
	}
	
	/**
	 * 通过文友圈评论按钮登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByComment(String userName,String pwd) throws Exception{
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab文友圈");
		boolean  p = new BasePage(driver,"twoPage").isElementDisplayed("评论按钮");
		int i = 0;
		while(p==false) {
			new BasePage(driver,"twoPage").swipeToUp();
			p = new BasePage(driver,"twoPage").isElementDisplayed("评论按钮");
			i++;
 			if(i>5) {
				break;
			}
		}
		new BasePage(driver,"twoPage").click("评论按钮");
		login(userName,pwd);
		new BasePage(driver,"tab栏").click("tab我的");
	}
	
	/**
	 * 通过文友圈点赞按钮登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByLike(String userName,String pwd) throws Exception{
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab文友圈");
		boolean  p = new BasePage(driver,"twoPage").isElementDisplayed("点赞按钮");
		int i = 0;
		while(p==false) {
			new BasePage(driver,"twoPage").swipeToUp();
			 p = new BasePage(driver,"twoPage").isElementDisplayed("点赞按钮");
			i++;
			if(i>5) {
				break;
			}
		}
		new BasePage(driver,"twoPage").click("点赞按钮");
		login(userName,pwd);
		new BasePage(driver,"tab栏").click("tab我的");
	}
	
	/**
	 * 通过文友圈回复评论登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByCommented(String userName,String pwd) throws Exception{
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab文友圈");
		BasePage pageTwo = new BasePage(driver,"twoPage");
		boolean p = pageTwo.isElementDisplayed("评论列表");
		int i=0;
		while(p==false) {
			pageTwo.swipeToUp();
			p=pageTwo.isElementDisplayed("评论列表");
			i++;
			if(i>10) {
				break;
			}
		}
		pageTwo.click("评论列表");
		login(userName,pwd);
		new BasePage(driver,"tab栏").click("tab我的");
	}
	
	/**
	 * 通过文章评论按钮登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByCommentArticle(String userName,String pwd) throws Exception {
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab首页");
		fristPageIsNull();
		new BasePage(driver,"firstPage").click("首页列表");
//		int i=0;
//		while(i<30) {
//			new BasePage(driver,"articlePage").swipeToUp();
//			i++;
//		}
		for(int j = 0;j < 30;j++) {
			
			new BasePage(driver,"articlePage").swipeToUp();
		}
		
		new BasePage(driver,"articlePage").click("web评论按钮");
		login(userName,pwd);
		new BasePage(driver,"commonButton").click("返回按钮");
		new BasePage(driver,"tab栏").click("tab我的");
	}
	
	/**
	 * 通过文章回复评论登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByCommentedArticle(String userName,String pwd) throws Exception {
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab首页");
		fristPageIsNull();
		new BasePage(driver,"firstPage").click("首页列表");
		articleCommentIsNull();
		new BasePage(driver,"articlePage").click("web评论列表");
		login(userName,pwd);
		new BasePage(driver,"commonButton").click("返回按钮");
		new BasePage(driver,"tab栏").click("tab我的");
	}
	
	/**
	 * 通过文章点赞登陆
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public void loginByLikeArticle(String userName,String pwd) throws Exception {
		new BasePage(driver,"tab栏").click("tab我的");
		isLogined(); 
		new BasePage(driver,"tab栏").click("tab首页");
		fristPageIsNull();
		new BasePage(driver,"firstPage").click("首页列表");
		articleCommentIsNull();
		new BasePage(driver,"articlePage").click("web点赞按钮");
		login(userName,pwd);
		new BasePage(driver,"commonButton").click("返回按钮");
		new BasePage(driver,"tab栏").click("tab我的");
	}

	/**
	 * 判断是否登录，如果已登录退出登录
	 * @throws Exception
	 */
	public void isLogined() throws Exception {
		boolean l = new BasePage(driver, "loginPage").isElementDisplayed("登录");
		if(l==false) {
			loginOut();
		}
		
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	public  void login(String userName,String pwd) throws Exception {	
	    new BasePage(driver,"loginPage").type("手机号", userName);
	    new BasePage(driver,"loginPage").type("密码", pwd);
//	    driver.hideKeyboard();
	    new BasePage(driver,"loginPage").click("登录");		 
	}
	
	/**
	 * 微信登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
//	public  void loginWeixin(String userNameWeixin,String pwdWeixin) throws Exception  {	
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	    new BasePage(driver,"loginPageWeixin").type("微信账号", userNameWeixin);
//	    new BasePage(driver,"loginPageWeixin").type("微信密码", pwdWeixin);  
//	    new BasePage(driver,"loginPageWeixin").click("微信登录");	
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	}
	
	/**
	 * 点击微信确认页面
	 * 方法不对
	 * @throws Exception 
	 */
//	public void loginWeinxinTrue() {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		try {
//			 driver.tap(1,500,1200,0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
//	}
	
	/**
	 * 寻找有评论的文章
	 * @throws Exception
	 */
	public void articleCommentIsNull() throws Exception {
		int n=1;
		int z=1;
		boolean w = false;
		shop:
		do {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			for(int a = 0;a < 8;a++) {
				new BasePage(driver,"articlePage").swipeToUpNumber(5);
				w = new BasePage(driver,"articlePage").isElementDisplayed("web评论列表");
					if(w==true) {
						new BasePage(driver,"articlePage").swipeToUp();
						break shop;
					}else if(new BasePage(driver,"articlePage").isElementDisplayed("web评论按钮")==true) {
						break;
					}			
			}
			new BasePage(driver,"commonButton").click("返回按钮");
			
			do {
				new BasePage(driver,"firstPage").swipeToUpNumber(n);
				new BasePage(driver,"firstPage").click("首页标题");
				n++;
				if(n>5) {
					break;
				}
			}
			while(new BasePage(driver,"firstPage").isElementDisplayed("首页标题"));
			z++;
			if(z>5) {
				break;
			}
		}
		while(w==false);
	}
	
	/**
	 * 判断首页列表是否异常
	 * @throws Exception
	 */
	public void fristPageIsNull() throws Exception {
		if(new BasePage(driver,"firstPage").isElementDisplayed("首页异常")==true) {
			new BasePage(driver,"firstPage").click("首页异常");
		}
	}
	
	/**
	 * 退出登录
	 * @throws Exception
	 */
	public void loginOut() throws Exception {
		 new BasePage(driver,"tab栏").click("tab我的");
		 new BasePage(driver,"threePage").click("设置");
		 new BasePage(driver,"setPage").click("退出登录");
		 
	}
	
	/**
	 * 未登录的先登录
	 * @param userName
	 * @param pwd
	 * @throws Exception
	 */
	  public void loginAll(String userName, String pwd) throws Exception {
		  boolean l = new BasePage(driver, "loginPage").isElementDisplayed("登录");
		  if(l==true) {
				new Login(driver).login(userName, pwd);
			}
	  }

}
