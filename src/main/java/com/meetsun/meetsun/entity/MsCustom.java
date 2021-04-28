package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsCustom {
	private String sysId;
	private String userName;
	private String passWord;
	private String email;
   	private String status;
   	private String idCard;
   	private String cardNo;
   	private String cardMoney;
   	private String opendId;
   	private String realName;
   	private String integral;
   	private String integralFlag;
   	private String createTime;
   	private String updateTime;
   	private String picture;
	
	private Integer offset;
	private Integer pageNumber;
}
