package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.AParentMenu;
import com.meetsun.meetsun.vo.AParentMenuVo;

@Mapper
public interface AParentMenuDao {
	/**
	 * 获取父类菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<AParentMenu> getAParentMenuList(AParentMenuVo vo);
	int getAParentMenuListTotal(AParentMenuVo vo);
	/**
	 * 添加父类菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveAParentMenu(AParentMenuVo vo);
	/**
	 * 修改父类菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateAParentMenu(AParentMenuVo vo);
	/**
	 * 删除父类菜单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteAParentMenu(AParentMenuVo vo);
}
