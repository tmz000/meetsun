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
	private AUserRoleDao aUserRoleDao;
	
	@Override
	public Result<Object> getAUserRoleList(AUserRoleVo vo) {
		List<AUserRole> list = aUserRoleDao.getAUserRoleList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(aUserRoleDao.getAUserRoleListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveAUserRole(AUserRoleVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = aUserRoleDao.saveAUserRole(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateAUserRole(AUserRoleVo vo) {
		int flag = aUserRoleDao.updateAUserRole(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteAUserRole(AUserRoleVo vo) {
		int flag = aUserRoleDao.deleteAUserRole(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
