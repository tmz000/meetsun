package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class VideoCustomVo {
	private String sysId;
	private String customSysId;
	private String videoSysId;
	private String createTime;
   	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
