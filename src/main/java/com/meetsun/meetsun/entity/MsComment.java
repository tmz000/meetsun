package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsComment {
	private String sysId;
	private String userName;
	private String content;
	private String projectSysId;
	private String starLv;
	private String createTime;
	private String updateTime;
	private String toExamine;
	private String adminName;
	private String userTx;
	private String customSysId;
	
	private String count;
	private String spName;
}
