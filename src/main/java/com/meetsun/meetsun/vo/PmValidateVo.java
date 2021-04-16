package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class PmValidateVo {
	private String sysId;
	private String userId;
	private String email;
	private String resetToken;
	private String type;
	private String status;
	private String createTime;
	private String updateTime;
}
