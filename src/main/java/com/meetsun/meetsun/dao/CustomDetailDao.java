package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.CustomDetail;
import com.meetsun.meetsun.vo.CustomDetailVo;

@Mapper
public interface CustomDetailDao {
	/**
	 * 获取会员卡消费信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<CustomDetail> getCustomDetailList(CustomDetailVo vo);
	int getCustomDetailListTotal(CustomDetailVo vo);
	/**
	 * 根据月份获取会员卡消费信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<CustomDetail> getCustomDetailListByMonth(CustomDetailVo vo);
	/**
	 * 添加会员卡消费信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveCustomDetail(CustomDetailVo vo);
	/**
	 * 修改会员卡消费信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateCustomDetail(CustomDetailVo vo);
	/**
	 * 删除会员卡消费信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteCustomDetail(CustomDetailVo vo);
}
