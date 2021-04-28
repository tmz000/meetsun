package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsOrder {
	private String sysId;
	private String orderNo;
	private String spName;
	private String price;
	private String discount;
   	private String amount; 
   	private String flag; //0已入账1未入账
   	private String status; //1已支付2未支付
   	private String createTime; 
   	private String updateTime;
   	private String userName;
   	private String month;
   	private String count;
   	private String sum;
   	private String freeMoney;
   	private String type;
   	private String spId;
   	private String remark;
   	private String isSend;
   	private String trueMoney;
   	private String typeName;
   	private String addressId;
   	private String projectSysId;
   	private String handPhoto;
   	private String isDel;
   	private String isShouhuo;
   	private String isUse;
   	private String cartSysId;
   	private String integral;
   	private String isPl;
   	private String isFh;
   	private String payType;
   	private String payMoney;
   	
	private String name;
	private String mobile;
   	private String address;
   	private String isPayPc;
	
	private Integer offset;
	private Integer pageNumber;
}
