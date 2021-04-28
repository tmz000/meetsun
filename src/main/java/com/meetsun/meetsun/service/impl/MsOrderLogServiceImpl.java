package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsOrderLogDao;
import com.meetsun.meetsun.entity.MsOrderLog;
import com.meetsun.meetsun.service.MsOrderLogService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsOrderLogVo;

@Service
public class MsOrderLogServiceImpl implements MsOrderLogService{
	
	@Autowired
	private MsOrderLogDao msOrderLogDao;
	
	@Override
	public Result<Object> getMsOrderLogList(MsOrderLogVo vo) {
		List<MsOrderLog> list = msOrderLogDao.getMsOrderLogList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msOrderLogDao.getMsOrderLogListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsOrderLog(MsOrderLogVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = msOrderLogDao.saveMsOrderLog(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsOrderLog(MsOrderLogVo vo) {
		int flag = msOrderLogDao.updateMsOrderLog(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsOrderLog(MsOrderLogVo vo) {
		int flag = msOrderLogDao.deleteMsOrderLog(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
