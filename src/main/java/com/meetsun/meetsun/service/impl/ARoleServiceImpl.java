package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.ARoleDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.ARoleService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.ARoleVo;
import com.meetsun.meetsun.vo.MsUserVo;

@Service
public class ARoleServiceImpl implements ARoleService{
	
	@Autowired
	private ARoleDao aRoleDao;
	@Autowired
	private MsUserDao msUserDao;
	
	@Override
	public Result<Object> getARoleList(ARoleVo vo) {
		List<ARole> list = aRoleDao.getARoleList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(aRoleDao.getARoleListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveARole(ARoleVo vo) {
		try {
			ARoleVo vo1 = new ARoleVo();
			vo1.setRoleName(vo.getRoleName());
			List<ARole> list1 = aRoleDao.getARoleList(vo1);
			ARoleVo vo2 = new ARoleVo();
			vo2.setRoleLv(vo.getRoleLv());
			List<ARole> list2 = aRoleDao.getARoleList(vo2);
			if(list1 != null && list1.size() > 0) {
				return Result.error("已经存在该角色类型！");
			}else if(list2 != null && list2.size() > 0) {
				return Result.error("已经存在该角色等级！");
			}else {
				vo.setSysId(Tools.getUUID());
				int flag = aRoleDao.saveARole(vo);
				if (flag > 0) {
					return Result.success("success");
				}
			}
		}catch (Exception e) {
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateARole(ARoleVo vo) {
		int flag = aRoleDao.updateARole(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteARole(ARoleVo vo) {
		MsUserVo mv = new MsUserVo();
		mv.setRoleId(vo.getSysId());
		List<MsUser> list = msUserDao.getMsUser(mv);
		if(list != null && list.size() > 0) {
			return Result.error("请先删除角色绑定的用户！");
		}
		int flag = aRoleDao.deleteARole(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
