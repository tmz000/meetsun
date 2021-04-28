package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.AUserRole;
import com.meetsun.meetsun.vo.AUserRoleVo;

@Mapper
public interface AUserRoleDao {
	/**
	 * 获取用户角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<AUserRole> getAUserRoleList(AUserRoleVo vo);
	int getAUserRoleListTotal(AUserRoleVo vo);
	/**
	 * 根据月份获取用户角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<AUserRole> getAUserRoleListByMonth(AUserRoleVo vo);
	/**
	 * 添加用户角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveAUserRole(AUserRoleVo vo);
	/**
	 * 修改用户角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateAUserRole(AUserRoleVo vo);
	/**
	 * 删除用户角色信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteAUserRole(AUserRoleVo vo);
}
