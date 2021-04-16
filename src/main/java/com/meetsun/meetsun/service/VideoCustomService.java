package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.VideoCustomVo;

public interface VideoCustomService {
	
	Result<Object> getVideoCustomList(VideoCustomVo vo);
	Result<Object> saveVideoCustom(VideoCustomVo vo);
	Result<Object> updateVideoCustom(VideoCustomVo vo);
	Result<Object> deleteVideoCustom(VideoCustomVo vo);
}
