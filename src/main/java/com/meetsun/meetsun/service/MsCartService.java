package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCartVo;

public interface MsCartService {
	Result<Object> getMsCartList(MsCartVo vo);
	Result<Object> getMsCartListBySpId(MsCartVo vo);
	Result<Object> getMsCartListByOpendId(MsCartVo vo);
	Result<Object> saveMsCart(MsCartVo vo);
	Result<Object> updateMsCart(MsCartVo vo);
	Result<Object> updateMsCartByStatus(MsCartVo vo);
	Result<Object> deleteMsCart(MsCartVo vo);
}
