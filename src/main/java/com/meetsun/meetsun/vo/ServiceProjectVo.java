package com.meetsun.meetsun.vo;
 
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ServiceProjectVo{
   private String sysId;
   private String name;
   private String remark;
   private String status;
   private String typeName;
   private String price;
   private String discount;
   private String amount;
   private String toDate;
   private String photoAdrr;
   private String handPhoto;
   private String userName;
   private String buyNum;
   private String clickNum;
   private String isJoin;
   private MultipartFile detailFile;
   private MultipartFile handFile;
	
	private Integer offset;
	private Integer pageNumber;
}