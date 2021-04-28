package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class FollowUp {
	private String sysId;
	private String realName;
	private String remark;
	private String visitId;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
