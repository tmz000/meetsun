package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.AMenuDao;
import com.meetsun.meetsun.dao.AParentMenuDao;
import com.meetsun.meetsun.entity.AMenu;
import com.meetsun.meetsun.entity.AParentMenu;
import com.meetsun.meetsun.service.AParentMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.AMenuVo;
import com.meetsun.meetsun.vo.AParentMenuVo;

@Service
public class AParentMenuServiceImpl implements AParentMenuService{
	
	@Autowired
	private AParentMenuDao aParentMenuDao;
	@Autowired
	private AMenuDao aMenuDao;
	
	@Override
	public Result<Object> getAParentMenuList(AParentMenuVo vo) {
		List<AParentMenu> list = aParentMenuDao.getAParentMenuList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveAParentMenu(AParentMenuVo vo) {
		List<AParentMenu> list = aParentMenuDao.getAParentMenuList(vo);
		if(list != null && list.size() > 0) {
			return Result.error("已存在该菜单名称！");
		}
		vo.setSysId(Tools.getUUID());
		int flag = aParentMenuDao.saveAParentMenu(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateAParentMenu(AParentMenuVo vo) {
		int flag = aParentMenuDao.updateAParentMenu(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteAParentMenu(AParentMenuVo vo) {
		AMenuVo avo = new AMenuVo();
		avo.setParentId(vo.getSysId());
		List<AMenu> list = aMenuDao.getAMenuList(avo);
		if(list != null && list.size() > 0) {
			return Result.error("该父类菜单下存在子菜单，不能删除！");
		}
		int flag = aParentMenuDao.deleteAParentMenu(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
