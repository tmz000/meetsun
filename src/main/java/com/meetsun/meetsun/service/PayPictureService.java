package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.PayPictureVo;

public interface PayPictureService {
	Result<Object> getPayPictureList(PayPictureVo vo);
	Result<Object> savePayPicture(PayPictureVo vo);
	Result<Object> updatePayPicture(PayPictureVo vo);
	Result<Object> deletePayPicture(PayPictureVo vo);
}
