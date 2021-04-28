package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.vo.MsCustomVo;

@Mapper
public interface MsCustomDao {
	/**
	 * 获取会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsCustom> getMsCustomList(MsCustomVo vo);
	int getMsCustomListTotal(MsCustomVo vo);
	/**
	 * 登录会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsCustom> getMsCustomByName(MsCustomVo vo);
	/**
	 * 根据opendid获取会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	MsCustom getMsCustomByOpendId(MsCustomVo vo);
	/**
	 * 更换会员opendid信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCustomByOpendId(MsCustomVo vo);
	/**
	 * 添加会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsCustom(MsCustomVo vo);
	/**
	 * 修改会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCustom(MsCustomVo vo);
	/**
	 * 删除会员用户信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsCustom(MsCustomVo vo);
	/**
	 * 更改会员用户余额
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCustomByCardMoney(MsCustomVo vo);
	/**
	 * 开启/关闭积分活动
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCustomByIntegralFlag(MsCustomVo vo);
	/**
	 * 个人资料修改
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCustomBySysId(MsCustomVo vo);
}
