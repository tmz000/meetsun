package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsOrderWl {
	private String sysId;
	private String orderNo;
	private String orderId;
	private String wlNo;
	private String wlName;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
