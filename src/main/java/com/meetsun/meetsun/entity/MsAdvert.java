package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsAdvert {
	private String sysId;
	private String adName;
	private String spId;
	private String spName;
	private String adContent;
	private String toDate;
	private String price;
	private String discount;
	private String amount; 
	private String type;
	private String status;
	private String photoAdrr;
	private String createTime; 
	private String updateTime;
	private String userName;
	private String freeMoney;
	private String clickNum;
	
	private Integer offset;
	private Integer pageNumber;
}
