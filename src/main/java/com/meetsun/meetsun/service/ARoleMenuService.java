package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ARoleMenuVo;

public interface ARoleMenuService {
	Result<Object> getARoleMenuList(ARoleMenuVo vo);
	Result<Object> saveARoleMenu(ARoleMenuVo vo);
	Result<Object> updateARoleMenu(ARoleMenuVo vo);
	Result<Object> deleteARoleMenu(ARoleMenuVo vo);
}
