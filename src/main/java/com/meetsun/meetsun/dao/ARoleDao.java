package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.vo.ARoleVo;

@Mapper
public interface ARoleDao {
	/**
	 * 获取角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<ARole> getARoleList(ARoleVo vo);
	int getARoleListTotal(ARoleVo vo);
	/**
	 * 添加角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveARole(ARoleVo vo);
	/**
	 * 修改角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateARole(ARoleVo vo);
	/**
	 * 删除角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteARole(ARoleVo vo);
}
