package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class VipCard {
	private String sysId;
	private String cardNo;
	private String cardMoney;
	private String flag;
	private String userName;
	private String discount;
	private String createTime;
   	private String updateTime;
}
