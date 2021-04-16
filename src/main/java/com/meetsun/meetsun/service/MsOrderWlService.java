package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderWlVo;

public interface MsOrderWlService {
	Result<Object> getMsOrderWlList(MsOrderWlVo vo);
	Result<Object> saveMsOrderWl(MsOrderWlVo vo);
	Result<Object> updateMsOrderWl(MsOrderWlVo vo);
	Result<Object> deleteMsOrderWl(MsOrderWlVo vo);
}
