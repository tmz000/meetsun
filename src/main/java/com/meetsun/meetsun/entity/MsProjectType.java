package com.meetsun.meetsun.entity;

import lombok.Data;

@Data
public class MsProjectType {
	private String sysId;
	private String name;
	private String status;
	private String remark;
	private String type;
   	private String userName;
   	private String orderBy;
   	private String createTime; 
   	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
