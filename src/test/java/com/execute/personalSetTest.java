package com.execute;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basic.DriverFactory;
import com.basic.TestNGListener;
import com.example.personalSet;

import io.appium.java_client.android.AndroidDriver;

/**
 * 个人设置自动化结果验证
 * @author xingxue
 *
 */
@Listeners({ com.basic.TestNGListener.class })
public class personalSetTest {
	
	AndroidDriver<?> driver = null;
	
  @Parameters({ "userName", "pwd", "picType1"})
  @Test(description = "通过相册更换头像")
  public void meHeadPhoto(String userName, String pwd, String picType1) throws Exception {
		assertTrue(new personalSet(driver).meHead(userName, pwd, picType1));
  }
  
  @Parameters({ "userName", "pwd", "picType2"})
  @Test(description = "通过相机更换头像")
  public void meHeadCamera(String userName, String pwd, String picType2) throws Exception {
		assertTrue(new personalSet(driver).meHead(userName, pwd, picType2)); 
  }
  
  @Parameters({ "userName", "pwd", "text1","text2"})
  @Test(description = "验证能否正常更改昵称")
  public void meName(String userName, String pwd, String text1,String text2) throws Exception {
		assertTrue(new personalSet(driver).meName(userName, pwd, text1)); 
		assertTrue(new personalSet(driver).meName(userName, pwd, text2)); 
  }

  @Parameters({ "userName", "pwd"})
  @Test(description = "验证能否正常更改地区")
  public void meCity(String userName, String pwd) throws Exception {
		assertTrue(new personalSet(driver).meCity(userName, pwd)); 
  }
  
  @Parameters({ "userName", "pwd", "text3","text4"})
  @Test(description = "验证能否正常更改签名")
  public void meSignature(String userName, String pwd, String text3,String text4) throws Exception {
		assertTrue(new personalSet(driver).meSignature(userName, pwd, text3)); 
		assertTrue(new personalSet(driver).meSignature(userName, pwd, text4)); 
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
