package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsQuestionVo;

public interface MsQuestionService {
	Result<Object> getMsQuestionList(MsQuestionVo vo);
	Result<Object> saveMsQuestion(MsQuestionVo vo);
	Result<Object> updateMsQuestion(MsQuestionVo vo);
	Result<Object> deleteMsQuestion(MsQuestionVo vo);
}
