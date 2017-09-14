package com.object;

public class Locator {

	    private String address;  //定位地址
	    private int waitSec;    //等待时间
	    private ByType byType;  //定位方式
	    
	    public enum ByType{
             xpath, id, name, className,AndroidUIAutomator
        }
	    
	    public Locator(String address, ByType byType) {
//	        this.waitSec = waitSec;
	        this.address = address;
	        this.byType = byType;
	    }
	    
	    public String getAddress() {
	        return address;
	    }
	    
	    public int getwaitSec() {
	        return waitSec;
	    }
	    
	    public ByType getBy() {
	        return byType;
	    }
	    
	    

}
