package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsUserVo;

public interface MsUserService {
	
	Result<Object> saveMsUser(MsUserVo paramMsUserVo);
	Result<Object> getMsUser(MsUserVo paramMsUserVo);
	Result<Object> getMsUserList(MsUserVo paramMsUserVo);
	Result<Object> getAllMsUser(MsUserVo paramMsUserVo);
	Result<Object> updateMsUserBySysId(MsUserVo paramMsUserVo);
	Result<Object> deleteMsUserBySysId(MsUserVo paramMsUserVo);
	Result<Object> getMsUserBySysId(MsUserVo paramMsUserVo);
	
}
