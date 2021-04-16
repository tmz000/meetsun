package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.IncomePayVo;

public interface IncomePayService {
	
	Result<Object> getIncomePayList(IncomePayVo vo);
	Result<Object> saveIncomePay(IncomePayVo vo);
	Result<Object> updateIncomePay(IncomePayVo vo);
	Result<Object> deleteIncomePay(IncomePayVo vo);
	Result<Object> getIncomePayListByStatus(IncomePayVo vo);
	Result<Object> getIncomePayListByCreateTime(IncomePayVo vo);
}
