package com.meetsun.meetsun.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MsSign implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//主键
	private String sysId;
	//用户id
	private String userId;
	//签到信息
	private String signDate;
	//总签到数
	private String count;
	//七天连续签到
	private String continueSign;
	//总积分
	private String jfCount;
	//积分变动
	private String integral;
	//创建时间
	private String createTime;
	//更新时间
	private String updateTime;
	
	private Integer offset;
	private Integer pageNumber;
}
