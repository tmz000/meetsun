package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class PaySureVo {
	private String sysId;
	private String orderId;
	private String payMoney;
	private String freeMoney;
	private String discount;
	private String payType;
	private String isPayWeb;
	private String isPayPc;
	private String payRemark;
	private String status;
	private String createTime;
	private String updateTime;

	private String integralTrue;
}
