package com.meetsun.meetsun.entity;
 
import java.io.Serializable;

import com.meetsun.meetsun.entity.MsUser;

import lombok.Data;

@Data
public class MsUser implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1297635160776178644L;
	
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
   	private String roleName;
   	private String mobile;
   	private String isCustomer;
	
	private Integer offset;
	private Integer pageNumber;
   	
}