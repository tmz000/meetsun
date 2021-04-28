package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.FollowUp;
import com.meetsun.meetsun.vo.FollowUpVo;

@Mapper
public interface FollowUpDao {
	/**
	 * 获取跟进信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<FollowUp> getFollowUpList(FollowUpVo vo);
	int getFollowUpListTotal(FollowUpVo vo);
	/**
	 * 添加跟进信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveFollowUp(FollowUpVo vo);
	/**
	 * 修改跟进信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateFollowUp(FollowUpVo vo);
	/**
	 * 删除跟进信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteFollowUp(FollowUpVo vo);
}
