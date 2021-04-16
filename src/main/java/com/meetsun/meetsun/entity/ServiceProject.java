package com.meetsun.meetsun.entity;
 
import com.meetsun.meetsun.entity.ServiceProject;

import lombok.Data;
 
@Data
public class ServiceProject{
	private String sysId;
	private String name;
	private String remark;
	private String status;
	private String typeName;
	private String price;
	private String discount;
   	private String amount; 
   	private String buyNum; 
   	private String clickNum; 
   	private String toDate;
   	private String userName;
   	private String photoAdrr;
    private String handPhoto;
    private String isJoin;
   	private String createTime; 
   	private String updateTime;
   
}