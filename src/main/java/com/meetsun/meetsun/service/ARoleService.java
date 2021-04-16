package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ARoleVo;

public interface ARoleService {
	Result<Object> getARoleList(ARoleVo vo);
	Result<Object> saveARole(ARoleVo vo);
	Result<Object> updateARole(ARoleVo vo);
	Result<Object> deleteARole(ARoleVo vo);
}
