package com.meetsun.meetsun.until;
 
import com.meetsun.meetsun.until.ResultEnum;
 
public enum ResultEnum{
	STATUS_SUCCESS("01", "成功"),
	STATUS_FAILURE("02", "失败"),
	STATUS_NOT_LOGIN("EL-0001", "请登录后操作");
   
	private String code;
	private String msg;
   
	ResultEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
   
	public String getcode() { 
		return this.code; 
	}
   
	public void setcode(String code) { 
		this.code = code; 
	}
   
	public String getmsg() { 
		return this.msg; 
	}
   
  	public void setmsg(String msg) { 
  		this.msg = msg; 
  	}
}
