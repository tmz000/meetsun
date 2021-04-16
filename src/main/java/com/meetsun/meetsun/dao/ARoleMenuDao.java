package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.ARoleMenu;
import com.meetsun.meetsun.vo.ARoleMenuVo;

@Mapper
public interface ARoleMenuDao {
	/**
	 * 获取角色菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<ARoleMenu> getARoleMenuList(ARoleMenuVo vo);
	/**
	 * 根据月份获取角色菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<ARoleMenu> getARoleMenuListByMonth(ARoleMenuVo vo);
	/**
	 * 添加角色菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveARoleMenu(ARoleMenuVo vo);
	/**
	 * 修改角色菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateARoleMenu(ARoleMenuVo vo);
	/**
	 * 删除角色菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteARoleMenu(ARoleMenuVo vo);
}
