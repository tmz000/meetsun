package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.vo.MsOrderVo;

@Mapper
public interface MsOrderDao {
	/**
	 * 获取订单列表
	 * @param vo
	 * @return
	 */
	List<MsOrder> getMsOrderList(MsOrderVo vo);
	/**
	 * 新增订单信息
	 * @param vo
	 * @return
	 */
	int saveMsOrder(MsOrderVo vo);
	/**
	 * 修改订单信息
	 * @param vo
	 * @return
	 */
	int updateMsOrder(MsOrderVo vo);
	/**
	 * 到店使用确定
	 * @param vo
	 * @return
	 */
	int updateMsOrderByIsUse(MsOrderVo vo);
	/**
	 * APP端删除订单信息
	 * @param vo
	 * @return
	 */
	int updateMsOrderByIsDel(MsOrderVo vo);
	/**
	 * APP端确认收货
	 * @param vo
	 * @return
	 */
	int updateMsOrderByIsShouhuo(MsOrderVo vo);
	/**
	 * APP付款
	 * @param vo
	 * @return
	 */
	int updateMsOrderByStatus(MsOrderVo vo);
	/**
	 * APP评论
	 * @param vo
	 * @return
	 */
	int updateMsOrderByIsPl(MsOrderVo vo);
	/**
	 * 删除订单信息
	 * @param vo
	 * @return
	 */
	int deleteMsOrder(MsOrderVo vo);
	/**
	 * 入账之后更改flag
	 * @param vo
	 * @return
	 */
	int updateMsOrderByFlag(MsOrderVo vo);
	/**
	 * 使用积分时更改订单实际支付金额
	 * @param vo
	 * @return
	 */
	int updateMsOrderByJf(MsOrderVo vo);
	
	/**
	 * 获取近12个月的订单列表
	 * @param vo
	 * @return
	 */
	List<MsOrder> getMsOrderListByMonth(MsOrderVo vo);
}
