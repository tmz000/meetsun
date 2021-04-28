package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class PayPicture {
	private String sysId;
	private String payAccount;
	private String payName;
	private String payPic;
	private String payType;
	private String status;
	private String remark;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
