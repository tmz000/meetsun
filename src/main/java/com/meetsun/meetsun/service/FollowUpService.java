package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.FollowUpVo;

public interface FollowUpService {
	Result<Object> getFollowUpList(FollowUpVo vo);
	Result<Object> saveFollowUp(FollowUpVo vo);
	Result<Object> updateFollowUp(FollowUpVo vo);
	Result<Object> deleteFollowUp(FollowUpVo vo);
}
