package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AMenuVo;

public interface AMenuService {
	Result<Object> getAMenuList(AMenuVo vo);
	Result<Object> saveAMenu(AMenuVo vo);
	Result<Object> updateAMenu(AMenuVo vo);
	Result<Object> deleteAMenu(AMenuVo vo);
}
