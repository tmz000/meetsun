package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class ARoleMenuVo {
	private String sysId;
	private String menuId;
	private String roleId;
	private String createTime;
	private String updateTime;

	private Integer offset;
	private Integer pageNumber;
	private String roleName;
}
