package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsOrderWl;
import com.meetsun.meetsun.vo.MsOrderWlVo;

@Mapper
public interface MsOrderWlDao {
	/**
	 * 获取订单物流信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsOrderWl> getMsOrderWlList(MsOrderWlVo vo);
	int getMsOrderWlListTotal(MsOrderWlVo vo);
	/**
	 * 添加订单物流信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsOrderWl(MsOrderWlVo vo);
	/**
	 * 修改订单物流信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsOrderWl(MsOrderWlVo vo);
	/**
	 * 删除订单物流信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsOrderWl(MsOrderWlVo vo);
}
