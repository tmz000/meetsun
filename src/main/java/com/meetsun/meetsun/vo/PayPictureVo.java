package com.meetsun.meetsun.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PayPictureVo {
	private String sysId;
	private String payAccount;
	private String payName;
	private String payPic;
	private String payType;
	private String status;
	private String remark;
	private String createTime;
	private String updateTime;
	
	private MultipartFile file;
}
