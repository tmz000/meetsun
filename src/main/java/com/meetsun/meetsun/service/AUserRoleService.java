package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AUserRoleVo;

public interface AUserRoleService {
	Result<Object> getAUserRoleList(AUserRoleVo vo);
	Result<Object> saveAUserRole(AUserRoleVo vo);
	Result<Object> updateAUserRole(AUserRoleVo vo);
	Result<Object> deleteAUserRole(AUserRoleVo vo);
}
