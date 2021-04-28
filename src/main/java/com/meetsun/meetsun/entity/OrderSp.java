package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class OrderSp {
	private String sysId;
	private String spId;
	private String orderId;
	private String status;
	private String type;
	private String createTime;
	private String updateTime;
	
	private ServiceProject serviceProject;
	private String orderNo;
	
	private Integer offset;
	private Integer pageNumber;
}
