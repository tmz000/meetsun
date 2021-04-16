package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsQuestion {
	private String sysId;
	private String userName;
	private String customId;
	private String content;
	private String status;
	private String contact;
	private String result;
	private String flag;
	private String isUse;
	private String createTime;
	private String updateTime;

}
