package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderAddressVo;

public interface MsOrderAddressService {
	Result<Object> getMsOrderAddressList(MsOrderAddressVo vo);
	Result<Object> saveMsOrderAddress(MsOrderAddressVo vo);
	Result<Object> updateMsOrderAddress(MsOrderAddressVo vo);
	Result<Object> updateMsOrderAddressByIsUse(MsOrderAddressVo vo);
	Result<Object> deleteMsOrderAddress(MsOrderAddressVo vo);
	Result<Object> getMsOrderAddressListBySysId(MsOrderAddressVo vo);
}
