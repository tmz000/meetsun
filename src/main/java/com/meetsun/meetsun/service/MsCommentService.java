package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCommentVo;

public interface MsCommentService {
	Result<Object> getMsCommentList(MsCommentVo vo);
	Result<Object> getMsCommentListByStarLv(MsCommentVo vo);
	Result<Object> saveMsComment(MsCommentVo vo);
	Result<Object> updateMsComment(MsCommentVo vo);
	Result<Object> deleteMsComment(MsCommentVo vo);
}
