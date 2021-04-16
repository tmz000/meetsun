package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class MsCartVo {
	private String sysId;
	private String customName;
	private String projectSysId;
	private String count;
	private String opendId;
	private String price;
	private String amount; 
	private String spName; 
	private String handPhoto; 
   	private String createTime;
   	private String updateTime;
   	private String type;
   	private String status;
   	private String sumCount;
   	private String sumMoney;
   	private String remark;
   	private String[] checkSysId;
   	private String[] projectSys;
   	private String addressId;
   	private String typeName;
   	
   	private String orderId;
   	
}
