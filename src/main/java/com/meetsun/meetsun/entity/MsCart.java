package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsCart {
	private String sysId;
	private String customName;
	private String projectSysId;
	private String count;
	private String opendId;
	private String price;
	private String amount; 
	private String spName; 
	private String handPhoto; 
	private String status; 
   	private String createTime;
   	private String updateTime;
   	private String type;
   	private String typeName;
   	
   	private OrderSp orderSp;
}
