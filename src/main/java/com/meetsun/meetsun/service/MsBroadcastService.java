package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsBroadcastVo;

public interface MsBroadcastService {
	Result<Object> getMsBroadcastList(MsBroadcastVo vo);
	Result<Object> saveMsBroadcast(MsBroadcastVo vo);
	Result<Object> updateMsBroadcast(MsBroadcastVo vo);
	Result<Object> deleteMsBroadcast(MsBroadcastVo vo);
}
