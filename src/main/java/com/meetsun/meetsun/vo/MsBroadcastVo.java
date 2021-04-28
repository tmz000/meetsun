package com.meetsun.meetsun.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MsBroadcastVo {
	private String sysId;
	private String photoAdrr;
	private String status;
	private String spName;
	private String spId;
	private String name;
	private String userName;
	private String createTime; 
	private String updateTime;
	private MultipartFile file;
	private String clickNum;
	
	private Integer offset;
	private Integer pageNumber;
}
