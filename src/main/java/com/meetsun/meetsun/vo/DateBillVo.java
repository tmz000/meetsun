package com.meetsun.meetsun.vo;

import lombok.Data;

@Data
public class DateBillVo {
	private String sysId;
	private String billDate;
	private String billRemark;
	private String billMoney;
	private String billType;
	private String status;
	private String countMoney;
	private String createTime;
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
