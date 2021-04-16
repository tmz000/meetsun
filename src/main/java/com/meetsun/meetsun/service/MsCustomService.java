package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCustomVo;

public interface MsCustomService {
	Result<Object> getMsCustomList(MsCustomVo vo);
	Result<Object> getMsCustomByName(MsCustomVo vo);
	Result<Object> saveMsCustom(MsCustomVo vo);
	Result<Object> updateMsCustom(MsCustomVo vo);
	Result<Object> deleteMsCustom(MsCustomVo vo);
	Result<Object> updateMsCustomByCardMoney(MsCustomVo vo);
	Result<Object> updateMsCustomBySysId(MsCustomVo vo);
}
