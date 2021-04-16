package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.DateBillVo;

public interface DateBillService {
	Result<Object> getDateBillList(DateBillVo vo);
	Result<Object> getDateBillListByType(DateBillVo vo);
	Result<Object> getDateBillListByYear(DateBillVo vo);
	Result<Object> saveDateBill(DateBillVo vo);
	Result<Object> updateDateBill(DateBillVo vo);
	Result<Object> deleteDateBill(DateBillVo vo);
}
