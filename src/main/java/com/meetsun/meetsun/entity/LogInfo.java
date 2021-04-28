package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class LogInfo {
	
	private String sysId;
	private String module;
	private String method;//方法名
	private String userName;
	private String realName;
	private String content;//请求的url
	private String operation;//操作方法
	private String ip;
	private String result;
	private String remark;
	private String type;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
