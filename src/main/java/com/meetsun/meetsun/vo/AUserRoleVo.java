package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class AUserRoleVo {
	private String sysId;
	private String userId;
	private String roleId;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
