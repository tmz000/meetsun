package com.meetsun.meetsun.service;

import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.until.Result;

public interface LogInfoService {
	Result<Object> getLogInfoList(LogInfo vo);
	void insertLog(LogInfo vo);
}
