package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsOrderLog;
import com.meetsun.meetsun.vo.MsOrderLogVo;

@Mapper
public interface MsOrderLogDao {
	/**
	 * 获取订单日志信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsOrderLog> getMsOrderLogList(MsOrderLogVo vo);
	/**
	 * 添加订单日志信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsOrderLog(MsOrderLogVo vo);
	/**
	 * 修改订单日志信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsOrderLog(MsOrderLogVo vo);
	/**
	 * 删除订单日志信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsOrderLog(MsOrderLogVo vo);
	/**
	 * 定期删除订单日志信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsOrderLogByDay(MsOrderLogVo vo);
}
