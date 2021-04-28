package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsVisit {
	private String sysId;
	private String name;
	private String phone;
	private String remark;
	private String status;
	private String type;
	private String visitNo;
	private String realName;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
