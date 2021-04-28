package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class OrderSpVo {
	private String sysId;
	private String spId;
	private String orderId;
	private String status;
	private String type;
	private String price;
	private String count;
	private String amount;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
	
}
