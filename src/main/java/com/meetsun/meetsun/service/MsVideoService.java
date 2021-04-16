package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsVideoVo;

public interface MsVideoService {
	Result<Object> getMsVideoList(MsVideoVo vo);
	Result<Object> saveMsVideo(MsVideoVo vo);
	Result<Object> updateMsVideo(MsVideoVo vo);
	Result<Object> deleteMsVideo(MsVideoVo vo);
}
