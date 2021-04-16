package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.OrderSpVo;

public interface OrderSpService {
	Result<Object> getOrderSpList(OrderSpVo vo);
	Result<Object> saveOrderSp(OrderSpVo vo);
	Result<Object> updateOrderSp(OrderSpVo vo);
	Result<Object> deleteOrderSp(OrderSpVo vo);
}
