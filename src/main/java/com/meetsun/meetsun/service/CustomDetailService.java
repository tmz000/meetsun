package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.CustomDetailVo;

public interface CustomDetailService {
	Result<Object> getCustomDetailList(CustomDetailVo vo);
	Result<Object> getCustomDetailListByMonth(CustomDetailVo vo);
	Result<Object> saveCustomDetail(CustomDetailVo vo);
	Result<Object> updateCustomDetail(CustomDetailVo vo);
	Result<Object> deleteCustomDetail(CustomDetailVo vo);
}
