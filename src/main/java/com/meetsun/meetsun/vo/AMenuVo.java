package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class AMenuVo {
	private String sysId;
	private String parentId;
	private String menuName;
	private String menuIcon;
	private String menuLink;
	private String menuRemark;
	private String menuNum;
	private String createTime;
	private String updateTime;
}
