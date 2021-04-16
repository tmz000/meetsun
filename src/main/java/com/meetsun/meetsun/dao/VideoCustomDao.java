package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.VideoCustom;
import com.meetsun.meetsun.vo.VideoCustomVo;

@Mapper
public interface VideoCustomDao {
	/**
	 * 获取收支列表
	 * @param vo
	 * @return
	 */
	List<VideoCustom> getVideoCustomList(VideoCustomVo vo);
	/**
	 * 新增收支信息
	 * @param vo
	 * @return
	 */
	int saveVideoCustom(VideoCustomVo vo);
	/**
	 * 修改收支信息
	 * @param vo
	 * @return
	 */
	int updateVideoCustom(VideoCustomVo vo);
	/**
	 * 删除收支信息
	 * @param vo
	 * @return
	 */
	int deleteVideoCustom(VideoCustomVo vo);
}
