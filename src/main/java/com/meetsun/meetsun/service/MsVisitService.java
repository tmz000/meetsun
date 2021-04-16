package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsVisitVo;

public interface MsVisitService {
	Result<Object> getMsVisitList(MsVisitVo vo);
	Result<Object> saveMsVisit(MsVisitVo vo);
	Result<Object> updateMsVisit(MsVisitVo vo);
	Result<Object> deleteMsVisit(MsVisitVo vo);
}
