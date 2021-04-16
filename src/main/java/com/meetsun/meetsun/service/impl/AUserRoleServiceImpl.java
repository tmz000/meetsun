package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.AUserRoleDao;
import com.meetsun.meetsun.entity.AUserRole;
import com.meetsun.meetsun.service.AUserRoleService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.AUserRoleVo;

@Service
public class AUserRoleServiceImpl implements AUserRoleService{
	
	@Autowired
	private AUserRoleDao AUserRoleDao;
	
	@Override
	public Result<Object> getAUserRoleList(AUserRoleVo vo) {
		List<AUserRole> list = AUserRoleDao.getAUserRoleList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveAUserRole(AUserRoleVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = AUserRoleDao.saveAUserRole(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateAUserRole(AUserRoleVo vo) {
		int flag = AUserRoleDao.updateAUserRole(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteAUserRole(AUserRoleVo vo) {
		int flag = AUserRoleDao.deleteAUserRole(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
