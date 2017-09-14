package com.appium;


import java.text.SimpleDateFormat;
import java.util.Date;

public class compare {
	//比较参数比较样式2月1日，02月01，12月12日
	/**
	 * 比较参数a1是否大于等于a2，如果大于等于输出true，否则输出false
	 * 比较样式2月1日，02月01，12月12日，只含有月日样式
	 * 弊端不能跨年比较
	 * @param a1
	 * @param a2
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean timeCompareFirst(String a1,String a2) throws Exception {
		String time1="2017年"+a1;
		String time2="2017年"+a2;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日"); 
		 Date date1=formatter.parse(time1); 
		 Date date2=formatter.parse(time2); 
		 if( date1.getTime() >= date2.getTime()) {
			 return true;
		 }
		 else {
			 return false;
		 } 
	}
	
	/**
	 * 比较两个数字的大小
	 * a1大于等于a2输出true，否则输出false
	 * 获取页面信息都是字符串类型的，所以统一转换为数字类型
	 * @param a1
	 * @param a2
	 * @return boolean
	 */
	public static boolean numberCompare(String a1,String a2) {
		int number1 = Integer.parseInt(a1);
		int number2 = Integer.parseInt(a2);
		if( number1 >= number2) {
			 return true;
		 }
		 else {
			 return false;
		 } 
	}
}
