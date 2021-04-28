package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class ARoleMenu {
	private String sysId;
	private String menuId;
	private String roleId;
	private String createTime;
	private String updateTime;
	
	private String roleName;
	private String menuName;
	
	private Integer offset;
	private Integer pageNumber;
}
