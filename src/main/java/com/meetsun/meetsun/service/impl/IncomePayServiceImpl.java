package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.IncomePayDao;
import com.meetsun.meetsun.entity.IncomePay;
import com.meetsun.meetsun.service.IncomePayService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.IncomePayVo;

@Service
public class IncomePayServiceImpl implements IncomePayService{
	
	@Autowired
	private IncomePayDao incomePayDao;
	
	@Override
	public Result<Object> getIncomePayList(IncomePayVo vo) {
		List<IncomePay> list = incomePayDao.getIncomePayList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveIncomePay(IncomePayVo vo) {
		vo.setSysId(Tools.getUUID());
		vo.setTrueMoney(String.valueOf(Double.valueOf(vo.getTrueMoney())));
		int flag = incomePayDao.saveIncomePay(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateIncomePay(IncomePayVo vo) {
		int flag = incomePayDao.updateIncomePay(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteIncomePay(IncomePayVo vo) {
		int flag = incomePayDao.deleteIncomePay(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> getIncomePayListByStatus(IncomePayVo vo) {
		List<IncomePay> list = incomePayDao.getIncomePayListByStatus(vo);
		List<IncomePay> list1 = new ArrayList<IncomePay>();
		for(IncomePay ip:list) {
			if(ip.getSum() == null) {
				ip.setSum("0");
			}
			list1.add(ip);
		}
		return Result.success(list1);
	}
	
	@Override
	public Result<Object> getIncomePayListByCreateTime(IncomePayVo vo) {
		List<IncomePay> list = incomePayDao.getIncomePayListByCreateTime(vo);
		List<IncomePay> list1 = new ArrayList<IncomePay>();
		for(IncomePay ip:list) {
			if(ip.getSum() == null) {
				ip.setSum("0");
			}
			list1.add(ip);
		}
		return Result.success(list1);
	}

}
