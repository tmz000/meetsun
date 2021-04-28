package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsVisit;
import com.meetsun.meetsun.vo.MsVisitVo;

@Mapper
public interface MsVisitDao {
	/**
	 * 获取到访信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsVisit> getMsVisitList(MsVisitVo vo);
	int getMsVisitListTotal(MsVisitVo vo);
	/**
	 * 添加到访信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsVisit(MsVisitVo vo);
	/**
	 * 修改到访信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsVisit(MsVisitVo vo);
	/**
	 * 删除到访信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsVisit(MsVisitVo vo);
}
