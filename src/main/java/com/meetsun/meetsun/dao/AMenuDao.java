package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.AMenu;
import com.meetsun.meetsun.vo.AMenuVo;

@Mapper
public interface AMenuDao {
	/**
	 * 获取菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<AMenu> getAMenuList(AMenuVo vo);
	/**
	 * 添加菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveAMenu(AMenuVo vo);
	/**
	 * 修改菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateAMenu(AMenuVo vo);
	/**
	 * 删除菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteAMenu(AMenuVo vo);
}
