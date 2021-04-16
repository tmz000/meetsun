package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.AMenuDao;
import com.meetsun.meetsun.dao.ARoleDao;
import com.meetsun.meetsun.dao.ARoleMenuDao;
import com.meetsun.meetsun.entity.AMenu;
import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.entity.ARoleMenu;
import com.meetsun.meetsun.service.ARoleMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.AMenuVo;
import com.meetsun.meetsun.vo.ARoleMenuVo;
import com.meetsun.meetsun.vo.ARoleVo;

@Service
public class ARoleMenuServiceImpl implements ARoleMenuService{
	
	@Autowired
	private ARoleMenuDao aRoleMenuDao;
	@Autowired
	private ARoleDao aRoleDao;
	@Autowired
	private AMenuDao aMenuDao;
	
	@Override
	public Result<Object> getARoleMenuList(ARoleMenuVo vo) {
		List<ARoleMenu> list = aRoleMenuDao.getARoleMenuList(vo);
		List<ARoleMenu> list1  = new ArrayList<ARoleMenu>();
		if(list != null && list.size() > 0){
			for(ARoleMenu am : list) {
				ARoleVo vo1 = new ARoleVo();
				vo1.setSysId(am.getRoleId());
				am.setRoleName(aRoleDao.getARoleList(vo1).get(0).getRoleName());
				List<String> menuId= new ArrayList<String>();
				for(String id:am.getMenuId().split(",")) {
					AMenuVo vo2 = new AMenuVo();
					vo2.setSysId(id.trim());
					List<AMenu> ll = aMenuDao.getAMenuList(vo2);
					menuId.add(ll.get(0).getMenuName());
				}
				am.setMenuName(menuId.toString().replace("[","").replace("]",""));
				list1.add(am);
			}
		}
		return Result.success(list1);
	}

	@Override
	public Result<Object> saveARoleMenu(ARoleMenuVo vo) {
		ARoleVo avo = new ARoleVo();
		avo.setRoleName(vo.getRoleName());
		List<ARole> list = aRoleDao.getARoleList(avo);
		if(list != null && list.size() > 0) {
			for(ARole rl : list) {
				vo.setRoleId(rl.getSysId());
			}
		}
		ARoleMenuVo vo1 = new ARoleMenuVo();
		vo1.setRoleId(vo.getRoleId());
		List<ARoleMenu> li1 = aRoleMenuDao.getARoleMenuList(vo1);
		if(li1 != null && li1.size() > 0) {
			return Result.error("该角色已经分配了权限！");
		}
		List<String> menuId = new ArrayList<String>();
		if(vo.getMenuId() != null || vo.getMenuId() != "") {
			for(String menuName : vo.getMenuId().split(",")) {
				AMenuVo vv = new AMenuVo();
				vv.setMenuName(menuName);
				List<AMenu> li = aMenuDao.getAMenuList(vv);
				menuId.add(li.get(0).getSysId());
			}
		}
		vo.setMenuId(menuId.toString().replace("[","").replace("]","").replaceAll(" +",""));
		vo.setSysId(Tools.getUUID());
		int flag = aRoleMenuDao.saveARoleMenu(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateARoleMenu(ARoleMenuVo vo) {
		List<String> menuId = new ArrayList<String>();
		if(vo.getMenuId() != null || vo.getMenuId() != "") {
			for(String menuName : vo.getMenuId().split(",")) {
				AMenuVo vv = new AMenuVo();
				vv.setMenuName(menuName.trim());
				List<AMenu> li = aMenuDao.getAMenuList(vv);
				menuId.add(li.get(0).getSysId());
			}
		}
		ARoleVo avo = new ARoleVo();
		avo.setRoleName(vo.getRoleName());
		List<ARole> list = aRoleDao.getARoleList(avo);
		if(list != null && list.size() > 0) {
			for(ARole rl : list) {
				vo.setRoleId(rl.getSysId());
			}
		}
		vo.setMenuId(menuId.toString().replace("[","").replace("]","").replaceAll(" +",""));
		int flag = aRoleMenuDao.updateARoleMenu(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteARoleMenu(ARoleMenuVo vo) {
		int flag = aRoleMenuDao.deleteARoleMenu(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
