package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.VipCardVo;

public interface VipCardService {
	Result<Object> getVipCardList(VipCardVo vo);
	Result<Object> saveVipCard(VipCardVo vo);
	Result<Object> updateVipCard(VipCardVo vo);
	Result<Object> deleteVipCard(VipCardVo vo);
}
