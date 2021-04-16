package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsOrderLog {
	private String sysId;
	private String orderNo;
	private String userName;
	private String realName;
	private String content;
	private String status;
	private String createTime;
	private String updateTime;
}
