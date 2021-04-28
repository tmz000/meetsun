package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class ARoleVo {
	private String sysId;
	private String roleName;
	private String roleLv;
	private String createTime;
	private String updateTime;

	private Integer offset;
	private Integer pageNumber;
	private String menuId;
}
