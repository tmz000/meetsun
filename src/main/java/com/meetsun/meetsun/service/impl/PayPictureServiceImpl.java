package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.PayPictureDao;
import com.meetsun.meetsun.entity.PayPicture;
import com.meetsun.meetsun.service.PayPictureService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.PayPictureVo;

@Service
public class PayPictureServiceImpl implements PayPictureService{
	
	@Autowired
	private PayPictureDao payPictureDao;
	
	@Override
	public Result<Object> getPayPictureList(PayPictureVo vo) {
		List<PayPicture> list = payPictureDao.getPayPictureList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> savePayPicture(PayPictureVo vo) {
		List<PayPicture> list = payPictureDao.getPayPictureList(vo);
		if(list != null && list.size() > 0) {
			return Result.error("已存在相同的可用支付方式，请不要重复添加！");
		}
		vo.setSysId(Tools.getUUID());
		vo.setPayPic(Tools.savePayTypePhoto(vo.getFile(),"pay/"));
		int flag = payPictureDao.savePayPicture(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updatePayPicture(PayPictureVo vo) {
		int flag = payPictureDao.updatePayPicture(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deletePayPicture(PayPictureVo vo) {
		List<PayPicture> list = payPictureDao.getPayPictureList(vo);
		Tools.deletePhotoAdrr(list.get(0).getPayPic());
		int flag = payPictureDao.deletePayPicture(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
