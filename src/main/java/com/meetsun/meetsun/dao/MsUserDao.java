package com.meetsun.meetsun.dao;

import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.vo.MsUserVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsUserDao {
	/**
	 * 添加用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsUser(MsUserVo paramMsUserVo);
	/**
	 * 获取单个用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsUser> getMsUser(MsUserVo paramMsUserVo);
	/**
	 * 根据主键获取单个用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsUser> getMsUserBySysId(MsUserVo paramMsUserVo);
	/**
	 * 获取所有用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsUser> getAllMsUser(MsUserVo paramMsUserVo);
	int getAllMsUserTotal(MsUserVo paramMsUserVo);
	/**
	 * 修改用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsUserBySysId(MsUserVo paramMsUserVo);
	/**
	 * 删除用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsUserBySysId(MsUserVo paramMsUserVo);
	/**
	 * 前端获取是否是客服信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsUser> getMsUserList(MsUserVo paramMsUserVo);
}
