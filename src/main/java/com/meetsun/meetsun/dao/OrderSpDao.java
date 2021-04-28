package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.OrderSp;
import com.meetsun.meetsun.vo.OrderSpVo;

@Mapper
public interface OrderSpDao {
	/**
	 * 获取订单产品关联信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<OrderSp> getOrderSpList(OrderSpVo vo);
	int getOrderSpListTotal(OrderSpVo vo);
	/**
	 * 添加订单产品关联信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveOrderSp(OrderSpVo vo);
	/**
	 * 修改订单产品关联信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateOrderSp(OrderSpVo vo);
	/**
	 * 删除订单产品关联信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteOrderSp(OrderSpVo vo);
}
