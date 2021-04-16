package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class AMenu {
	private String sysId;
	private String parentId;
	private String menuName;
	private String menuIcon;
	private String menuLink;
	private String menuRemark;
	private String menuNum;
	private String createTime;
	private String updateTime;
	
	private String parentMenuName;
	
}
