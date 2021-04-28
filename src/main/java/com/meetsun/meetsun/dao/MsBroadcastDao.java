package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsBroadcast;
import com.meetsun.meetsun.vo.MsBroadcastVo;

@Mapper
public interface MsBroadcastDao {
	/**
	 * 获取轮播图信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsBroadcast> getMsBroadcastList(MsBroadcastVo vo);
	int getMsBroadcastListTotal(MsBroadcastVo vo);
	/**
	 * 添加轮播图信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsBroadcast(MsBroadcastVo vo);
	/**
	 * 修改轮播图信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsBroadcast(MsBroadcastVo vo);
	/**
	 * 删除轮播图信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsBroadcast(MsBroadcastVo vo);
}
