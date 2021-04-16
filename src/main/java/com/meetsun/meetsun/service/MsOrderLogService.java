package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderLogVo;

public interface MsOrderLogService {
	Result<Object> getMsOrderLogList(MsOrderLogVo vo);
	Result<Object> saveMsOrderLog(MsOrderLogVo vo);
	Result<Object> updateMsOrderLog(MsOrderLogVo vo);
	Result<Object> deleteMsOrderLog(MsOrderLogVo vo);
}
