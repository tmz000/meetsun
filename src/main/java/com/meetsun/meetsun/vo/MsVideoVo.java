package com.meetsun.meetsun.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MsVideoVo {
	private String sysId;
	private String userName;
	private String name;
	private String remark;
	private String spName;
   	private String createTime;
   	private String updateTime;
   	private String spId;
   	private String videoAddr;
   	private String clickNumber;
   	private MultipartFile file;
   	private String customSysId;
	
	private Integer offset;
	private Integer pageNumber;
}
