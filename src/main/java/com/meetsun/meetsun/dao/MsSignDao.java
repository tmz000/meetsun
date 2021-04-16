package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsSign;
import com.meetsun.meetsun.vo.MsSignVo;

@Mapper
public interface MsSignDao {
	/**
	 * 获取签到信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsSign> getMsSignList(MsSignVo vo);
	/**
	 * 添加签到信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsSign(MsSignVo vo);
	/**
	 * 修改签到信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsSign(MsSignVo vo);
	/**
	 * 删除签到信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsSign(MsSignVo vo);
}
