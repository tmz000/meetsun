package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.LogInfo;

@Mapper
public interface LogInfoDao {
	/**
	 * 插入日志信息
	 * @param vo
	 * @return
	 */
	int insertLog(LogInfo vo);
	/**
	 * 获取日志信息
	 * @param vo
	 * @return
	 */
	List<LogInfo> getLogInfoList(LogInfo vo);
	int getLogInfoListTotal(LogInfo vo);
	/**
	 * 删除半年以前的日志信息
	 * @param vo
	 * @return
	 */
	int deleteLogInfoByDay(LogInfo vo);
}
