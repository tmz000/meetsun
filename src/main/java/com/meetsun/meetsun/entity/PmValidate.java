package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class PmValidate {
	private String sysId;
	private String userId;
	private String email;
	private String resetToken;
	private String type;
	private String status;
	private String createTime;
	private String updateTime;
}
