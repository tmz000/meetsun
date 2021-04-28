package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsVideo;
import com.meetsun.meetsun.vo.MsVideoVo;

@Mapper
public interface MsVideoDao {
	/**
	 * 获取视频信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsVideo> getMsVideoList(MsVideoVo vo);
	int getMsVideoListTotal(MsVideoVo vo);
	/**
	 * 添加视频信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsVideo(MsVideoVo vo);
	/**
	 * 修改视频信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsVideo(MsVideoVo vo);
	/**
	 * 删除视频信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsVideo(MsVideoVo vo);
}
