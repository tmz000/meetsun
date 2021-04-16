package com.meetsun.meetsun.vo;
 
import java.io.Serializable;

import lombok.Data;
	
@Data
public class MsUserVo implements  Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = -2672686062149630438L;
	
	private String sysId;
	private String userName;
	private String passWord;
	private String email;
	private String status;
   	private String passwordConfirm;
   	private String realName;
   	private String createTime;
   	private String updateTime;
   	private String roleId;
   	private String loginWord;
   	private String mobile;
   	private String isCustomer;
   
   	private String flag;
   	private String token;
   
}