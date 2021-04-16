package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderVo;

public interface MsOrderService {
	
	Result<Object> getMsOrderList(MsOrderVo vo);
	Result<Object> saveMsOrder(MsOrderVo vo);
	Result<Object> updateMsOrder(MsOrderVo vo);
	Result<Object> updateMsOrderByIsUse(MsOrderVo vo);
	Result<Object> updateMsOrderByIsDel(MsOrderVo vo);
	Result<Object> updateMsOrderByStatus(MsOrderVo vo);
	Result<Object> updateMsOrderByIsShouhuo(MsOrderVo vo);
	Result<Object> updateMsOrderByIsPl(MsOrderVo vo);
	Result<Object> deleteMsOrder(MsOrderVo vo);
	Result<Object> getMsOrderListByMonth(MsOrderVo vo);
	Result<Object> updateMsOrderByFlag(MsOrderVo vo);
	
}
