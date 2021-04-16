package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsAdvert;
import com.meetsun.meetsun.vo.MsAdvertVo;

@Mapper
public interface MsAdvertDao {
	/**
	 * 获取广告信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsAdvert> getMsAdvertList(MsAdvertVo vo);
	/**
	 * 添加广告信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsAdvert(MsAdvertVo vo);
	/**
	 * 修改广告信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsAdvert(MsAdvertVo vo);
	/**
	 * 删除广告信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsAdvert(MsAdvertVo vo);
	/**
	 * 更改广告到期信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsAdvertStatus(MsAdvertVo vo);
}
