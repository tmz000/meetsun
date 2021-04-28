package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class CustomDetailVo {
	private String sysId;
	private String userName;
	private String adminName;
	private String spName;
	private String cardNo;
   	private String cardMoney;
   	private String payMoney;
   	private String createTime;
   	private String updateTime;
   	private String integral;
   	private String flag;
   	private String payType;
	
	private Integer offset;
	private Integer pageNumber;
}
