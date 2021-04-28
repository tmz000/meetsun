package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class MsOrderLogVo {
	private String sysId;
	private String orderNo;
	private String userName;
	private String realName;
	private String content;
	private String status;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
