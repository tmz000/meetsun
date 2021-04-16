package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsSignVo;

public interface MsSignService {
	Result<Object> getMsSignList(MsSignVo vo);
	Result<Object> saveMsSign(MsSignVo vo);
	Result<Object> updateMsSign(MsSignVo vo);
	Result<Object> deleteMsSign(MsSignVo vo);
}
