package com.example;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.appium.RandomString;
import com.basic.ImageDiff;
import com.basic.ScreenShot;
import com.object.BasePage;

import io.appium.java_client.android.AndroidDriver;

/**
 * 个人设置自动化测试流程
 * @author xingxue
 *
 */
public class personalSet {
	
	Logger log = Logger.getLogger(ScreenShot.class.getName());
	
	private AndroidDriver<?> driver;

	public personalSet(AndroidDriver<?> driver) {
		this.driver = driver;
	}

	
	/**
	 * 更换头像通过相册和相机
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public boolean meHead(String userName, String pwd,String picType) throws Exception {
		  new BasePage(driver, "tab栏").click("tab我的");
		  new Login(driver).loginAll(userName, pwd);
		  new BasePage(driver, "threePage").click("我的头像");
		  Thread.sleep(3000);
		  String num1 = new RandomString().getRandomString(10,"混合");
		  new ScreenShot(driver,num1).getScreenShot();
		  Thread.sleep(3000);
		  String s1 = new ScreenShot(driver,num1).getPath();
		  new BasePage(driver, "threePage").click("设置头像大图");
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  new BasePage(driver, "threePage").click("个人设置");
		  new BasePage(driver, "meSetPage").click("设置头像");
		  if(picType.equals("相片")) {
			  new BasePage(driver, "photograph").click("相片");
		  }else if(picType.equals("相机")) {
			  new BasePage(driver, "photograph").click("相机");
			  boolean a1 = new BasePage(driver, "photograph").isElementDisplayed("是否允许调用摄像头标题");
				if(a1==true) {
					new BasePage(driver, "photograph").click("允许调用摄像头");
				}
			  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			  new BasePage(driver, "photograph").click("相机按钮");
			  Thread.sleep(3000);
			  new BasePage(driver, "photograph").click("相机确认按钮");
		  }
		  Thread.sleep(3000);
		  new BasePage(driver, "meSetPage").click("设置保存按钮");
		  new BasePage(driver, "threePage").click("我的头像");
		  Thread.sleep(3000);
		  String num2 = new RandomString().getRandomString(10,"混合");
		  new ScreenShot(driver,num2).getScreenShot();
		  Thread.sleep(3000);
		  String s2 = new ScreenShot(driver,num2).getPath();
		  Thread.sleep(3000);
		  new BasePage(driver, "threePage").click("设置头像大图");
		  double l1 = new ImageDiff(driver).compareImages(s1, s2);
		  log.info("头像前名称:"+num1+"头像后名称:"+num2);
		  if(l1<90) {
			  return true;
		  }
		  else{
			  return false;
		  }  
	  }
	
	/**
	 * 更改用户名称，验证是否成功
	 * @param userName
	 * @param pwd
	 * @param text
	 * @return
	 * @throws Exception
	 * 
	 * 注：名称为空未处理
	 */
	public boolean meName(String userName, String pwd,String text) throws Exception {
		  new BasePage(driver, "tab栏").click("tab我的");
		  new Login(driver).loginAll(userName, pwd);
		  String text1 = new BasePage(driver, "threePage").getText("我的用户名");
		  new BasePage(driver, "threePage").click("个人设置");
		  new BasePage(driver, "meSetPage").clear("设置昵称");
		  new BasePage(driver, "meSetPage").type("设置昵称", text);
		  new BasePage(driver, "meSetPage").click("设置保存按钮");
		  String text2 = new BasePage(driver, "threePage").getText("我的用户名");
		  log.info("用户名前:"+text1+"用户名后:"+text2);
		  if(text.equals(text2)&&!text1.equals(text2)) {
			  return true;
		  }
		  else{
			 return false;
		  }
	}
	
	/**
	 * 更改地区，验证是否成功
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws Exception
	 * 
	 * 注：地区为空未处理
	 */
	@SuppressWarnings("static-access")
	public boolean meCity(String userName, String pwd) throws Exception {
		  new BasePage(driver, "tab栏").click("tab我的");
		  new Login(driver).loginAll(userName, pwd);
		  String text1 = new BasePage(driver, "threePage").getText("我的地区");
		  new BasePage(driver, "threePage").click("个人设置");
		  new BasePage(driver, "meSetPage").click("设置地区"); 
		  int n1 = Integer.valueOf(new RandomString().getRandomString(1,"数字")).intValue();
		  for(int i=0;i<n1;i++) {
			  new BasePage(driver, "meSetPage").swipeToUp("设置省份");
		  }
		  int n2 = Integer.valueOf(new RandomString().getRandomString(1,"数字")).intValue();
		  for(int i=0;i<n2;i++) {
			  new BasePage(driver, "meSetPage").swipeToUp("设置城市");
		  }
		  new BasePage(driver, "meSetPage").click("设置地区确定按钮");
		  String text2 = new BasePage(driver, "meSetPage").getText("设置地区");
		  new BasePage(driver, "meSetPage").click("设置保存按钮");
		  String text3 = new BasePage(driver, "threePage").getText("我的地区");
		  log.info("地区前:"+text1+"地区中:"+text2+"地区后:"+text3);
		  if(text2.equals(text3)&&!text1.equals(text2)) {
			  return true;
		  }
		  else {
			  return false;
		  }
	}
	
	/**
	 * 更改签名，验证是否成功
	 * @param userName
	 * @param pwd
	 * @param text
	 * @return
	 * @throws Exception
	 * 
	 * 注：签名为空未处理
	 * 个性签名打点没法验证
	 */
	public boolean meSignature(String userName, String pwd,String text) throws Exception {
		  new BasePage(driver, "tab栏").click("tab我的");
		  new Login(driver).loginAll(userName, pwd);
		  String text1 = new BasePage(driver, "threePage").getText("我的签名");
		  new BasePage(driver, "threePage").click("个人设置");
		  new BasePage(driver, "meSetPage").clear("设置个人签名");
		  new BasePage(driver, "meSetPage").type("设置个人签名", text);
		  Boolean a = new BasePage(driver, "meSetPage").isElementDisplayed("设置保存按钮");
		  System.out.println(a);
		  new BasePage(driver, "meSetPage").click("设置保存按钮");
		  System.out.println(1);
		  String text2 = new BasePage(driver, "threePage").getText("我的签名");
		  System.out.println(text2);
		  log.info("个人签名前:"+text1+" 个人签名后:"+text2+" 更改的内容："+text);
		  text = "个性签名："+text;
		  if(text.equals(text2)&&!text1.equals(text2)) {
			  return true;
		  }
		  else{
			 return false;
		  }
	}

}
