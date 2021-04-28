package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class AUserRole {
	private String sysId;
	private String userId;
	private String roleId;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
