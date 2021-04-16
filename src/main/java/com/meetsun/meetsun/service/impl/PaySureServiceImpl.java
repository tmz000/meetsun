package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.PaySureDao;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.PaySure;
import com.meetsun.meetsun.service.PaySureService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.PaySureVo;

@Service
public class PaySureServiceImpl implements PaySureService{
	
	@Autowired
	private PaySureDao paySureDao;
	@Autowired
	private MsOrderDao msOrderDao;

	@Override
	public Result<Object> getPaySureList(PaySureVo vo) {
		List<PaySure> list = paySureDao.getPaySureList(vo);
		List<PaySure> list1 = new ArrayList<PaySure>();
		if(list != null && list.size() > 0) {
			for(PaySure ps:list) {
				MsOrderVo mv = new MsOrderVo();
				mv.setSysId(ps.getOrderId());
				List<MsOrder> mli = msOrderDao.getMsOrderList(mv);
				if(mli != null && mli.size() > 0) {
					ps.setOrderNo(mli.get(0).getOrderNo());
				}
				list1.add(ps);
			}
		}
		return Result.success(list1);
	}

	@Override
	public Result<Object> savePaySure(PaySureVo vo) {
		if(vo.getPayType().equals("0")) {
			vo.setPayMoney(String.valueOf(Math.round(Double.valueOf(vo.getIntegralTrue())*Double.valueOf(vo.getDiscount())/10)));
			vo.setStatus("1");
		}else {
			vo.setStatus("0");
		}
		int flag = paySureDao.savePaySure(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updatePaySure(PaySureVo vo) {
		int flag = paySureDao.updatePaySure(vo);
     	if (flag > 0) {
     		if(vo.getIsPayPc().equals("2")) {
     			MsOrderVo mv = new MsOrderVo();
     			mv.setSysId(vo.getOrderId());
     			mv.setStatus("1");
     			msOrderDao.updateMsOrderByStatus(mv);
     		}
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deletePaySure(PaySureVo vo) {
		int flag = paySureDao.deletePaySure(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
