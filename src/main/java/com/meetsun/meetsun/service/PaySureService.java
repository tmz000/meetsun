package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.PaySureVo;

public interface PaySureService {
	Result<Object> getPaySureList(PaySureVo vo);
	Result<Object> savePaySure(PaySureVo vo);
	Result<Object> updatePaySure(PaySureVo vo);
	Result<Object> deletePaySure(PaySureVo vo);
}
