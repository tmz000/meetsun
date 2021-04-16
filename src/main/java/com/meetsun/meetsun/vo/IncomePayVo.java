package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class IncomePayVo {
	private String sysId;
	private String spName;
	private String discount;
	private String price;
   	private String amount; 
   	private String status;//0收入，1支出
   	private String createTime; 
   	private String updateTime;
   	private String type;//0线上，1线下
   	private String userName;
   	private String freeMoney;
   	private String trueMoney;
   	private String inputType;
   	private String payType;
   	
	private String flag;
   	private String month;
   	private String sum;
}
