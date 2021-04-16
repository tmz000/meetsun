package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsAdvertVo;

public interface MsAdvertService {
	Result<Object> getMsAdvertList(MsAdvertVo vo);
	Result<Object> saveMsAdvert(MsAdvertVo vo);
	Result<Object> updateMsAdvert(MsAdvertVo vo);
	Result<Object> deleteMsAdvert(MsAdvertVo vo);
}
