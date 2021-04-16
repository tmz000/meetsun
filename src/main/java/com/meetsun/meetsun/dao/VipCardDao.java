package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.VipCard;
import com.meetsun.meetsun.vo.VipCardVo;

@Mapper
public interface VipCardDao {
	/**
	 * 获取会员卡信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<VipCard> getVipCardList(VipCardVo vo);
	/**
	 * 添加会员卡信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveVipCard(VipCardVo vo);
	/**
	 * 修改会员卡信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateVipCard(VipCardVo vo);
	/**
	 * 删除会员卡信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteVipCard(VipCardVo vo);
}
