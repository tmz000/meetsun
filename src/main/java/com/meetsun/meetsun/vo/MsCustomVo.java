package com.meetsun.meetsun.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MsCustomVo {
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
   	private String flag;
	private String spName;
	private String spId;
	private MultipartFile file;
	private String integralTrue;
	private String orderSysId;
	private Boolean isUseJf;//是否使用积分true使用，false不使用
	
	private String payType;
	private String payMoney;
	private String freeMoney;
	private boolean isJoin;
	
	public boolean getIsJoin() {
		if(this.isJoin) {
			return true;
		}
		return false;
	}
	
	private Integer offset;
	private Integer pageNumber;
}
