package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class DateBill {
	private String sysId;
	private String billDate;
	private String billRemark;
	private String billMoney;
	private String billType;
	private String status;
	private String countMoney;
	private String createTime;
	private String updateTime;
	
	private String month;
	private String count;
	private String sum;
	private String countSum;
	
}
