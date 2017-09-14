package com.appium;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeNow {
	public  String getTimeymdhms() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = df.format(new Date());
		return currentTime;
	}
	public  String getTimehms() {
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		String currentTime = df.format(new Date());
		return currentTime;
	}

}
