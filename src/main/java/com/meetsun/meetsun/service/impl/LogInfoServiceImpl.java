package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.LogInfoDao;
import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.service.LogInfoService;
import com.meetsun.meetsun.until.Result;

@Service
public class LogInfoServiceImpl implements LogInfoService{
	
	@Autowired
	private LogInfoDao logInfoDao;

	@Override
	public void insertLog(LogInfo vo) {
		logInfoDao.insertLog(vo);
		return;
	}

	@Override
	public Result<Object> getLogInfoList(LogInfo vo) {
		List<LogInfo> list = logInfoDao.getLogInfoList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(logInfoDao.getLogInfoListTotal(vo));
		return result;
	}

}
