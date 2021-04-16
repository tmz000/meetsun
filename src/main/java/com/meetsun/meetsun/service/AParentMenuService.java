package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AParentMenuVo;

public interface AParentMenuService {
	Result<Object> getAParentMenuList(AParentMenuVo vo);
	Result<Object> saveAParentMenu(AParentMenuVo vo);
	Result<Object> updateAParentMenu(AParentMenuVo vo);
	Result<Object> deleteAParentMenu(AParentMenuVo vo);
}
