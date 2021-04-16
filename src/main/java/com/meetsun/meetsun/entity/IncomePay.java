package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class IncomePay {
	private String sysId;
	private String spName;
	private String price;
	private String discount;
   	private String amount; 
   	private String status;//0收入，1支出
   	private String createTime; 
   	private String updateTime;
   	private String type;//0线上，1线下
   	private String count;
   	private String userName;
   	private String freeMoney;
   	private String trueMoney;
   	private String inputType;
   	private String payType;
   	
   	private String flag;
   	private String month;
   	private String sum;
}
