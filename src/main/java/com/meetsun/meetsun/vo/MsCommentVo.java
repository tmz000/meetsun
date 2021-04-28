package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class MsCommentVo {
	private String sysId;
	private String userName;
	private String content;
	private String projectSysId;
	private String starLv;
	private String createTime;
	private String updateTime;
	private String toExamine;
	private String adminName;
	private String userTx;
	private String customSysId;
	
	private Integer offset;
	private Integer pageNumber;
}
