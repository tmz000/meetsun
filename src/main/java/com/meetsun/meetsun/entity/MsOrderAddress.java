package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsOrderAddress {
	private String sysId;
	private String userName;
	private String opendId;
	private String name;
	private String mobile;
   	private String postCode;
   	private String address;
   	private String isUse;
   	private String createTime;
   	private String updateTime;
}
