package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class PaySure {
	private String sysId;
	private String orderId;
	private String payMoney;
	private String payType;
	private String isPayWeb;
	private String isPayPc;
	private String status;
	private String payRemark;
	private String createTime;
	private String updateTime;
	
	private String orderNo;
}
