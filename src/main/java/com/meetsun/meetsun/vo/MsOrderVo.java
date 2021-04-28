package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class MsOrderVo {
	private String sysId;
	private String orderNo;
	private String spName;
	private String price;
	private String discount;
   	private String amount; 
   	private String status;//0已支付1未支付
   	private String flag; //0已入账1未入账
   	private String createTime; 
   	private String updateTime;
   	private String userName;
   	private String freeMoney;
   	private String type;
   	private String count;
   	private String opendId;
   	private String customName;
   	private String spId;
   	private String remark;
   	private String isSend;
   	private String typeName;
   	private String trueMoney;
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
   	
	private String content;
	private String starLv;
	private String userTx;
	private String customSysId;
	
	private Integer offset;
	private Integer pageNumber;
}
