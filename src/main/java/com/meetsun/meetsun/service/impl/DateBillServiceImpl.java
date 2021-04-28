package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.DateBillDao;
import com.meetsun.meetsun.entity.DateBill;
import com.meetsun.meetsun.service.DateBillService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.DateBillVo;

@Service
public class DateBillServiceImpl implements DateBillService{
	
	@Autowired
	private DateBillDao dateBillDao;
	
	@Override
	public Result<Object> getDateBillList(DateBillVo vo) {
		List<DateBill> list = dateBillDao.getDateBillList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(dateBillDao.getDateBillListTotal(vo));
		return result;
	} 
	
	@Override
	public Result<Object> getDateBillListByType(DateBillVo vo) {
		List<DateBill> list = dateBillDao.getDateBillListByType(vo);
		List<DateBill> list1 = new ArrayList<DateBill>();
		Double countSum = 0.0;
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				countSum = countSum + Double.valueOf(db.getSum());
			}
		}
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				db.setCountSum(String.valueOf(String.format("%.2f", countSum)));
				list1.add(db);
			}
		}
		return Result.success(list1);
	}
	
	@Override
	public Result<Object> getDateBillListByYear(DateBillVo vo) {
		if(vo.getBillDate() == null || vo.getBillDate() == "") {
			vo.setBillDate(Tools.getYear()+"-12-01");
		}
		List<DateBill> list = dateBillDao.getDateBillListByYear(vo);
		List<DateBill> li = new ArrayList<DateBill>();
		Double countSum = 0.0;
		if(list != null && list.size() > 0) {
			for(DateBill db : list) {
				if(db.getSum() == null) {
					db.setSum("0");
				}
				countSum = countSum + Double.valueOf(db.getSum());
			}
		}
		for(DateBill db:list) {
			db.setCountSum(String.valueOf(countSum));
			li.add(db);
		}
		return Result.success(li);
	}

	@Override
	public Result<Object> saveDateBill(DateBillVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = dateBillDao.saveDateBill(vo);
		if (flag > 0) {
			DateBillVo vo1 = new DateBillVo();
			if(vo.getStatus().equals("0")) {
				vo1.setCountMoney(String.valueOf(String.format("%.2f", Double.valueOf(vo.getCountMoney())-Double.valueOf(vo.getBillMoney()))));
			}else if(vo.getStatus().equals("1")) {
				vo1.setCountMoney(String.valueOf(String.format("%.2f", Double.valueOf(vo.getCountMoney())+Double.valueOf(vo.getBillMoney()))));
			}
			dateBillDao.updateDateBill(vo1);
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateDateBill(DateBillVo vo) {
		int flag = 0;
		if(vo.getSysId() != null && vo.getSysId() != "") {
			List<DateBill> list = dateBillDao.getDateBillList(vo);
			DateBillVo vo1 = new DateBillVo();
			if(list.get(0).getStatus().equals("0")) {
				vo1.setCountMoney(String.valueOf(Double.valueOf(list.get(0).getCountMoney())+Double.valueOf(list.get(0).getBillMoney())-Double.valueOf(vo.getBillMoney())));
			}else if(list.get(0).getStatus().equals("1"))  {
				vo1.setCountMoney(String.valueOf(Double.valueOf(list.get(0).getCountMoney())-Double.valueOf(list.get(0).getBillMoney())+Double.valueOf(vo.getBillMoney())));
			}
			flag = dateBillDao.updateDateBill(vo);
			dateBillDao.updateDateBill(vo1);
		}else {
			flag = dateBillDao.updateDateBill(vo);
		}
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteDateBill(DateBillVo vo) {
		List<DateBill> list = dateBillDao.getDateBillList(vo);
		DateBillVo vo1 = new DateBillVo();
		if(list.get(0).getStatus().equals("0")) {
			vo1.setCountMoney(String.valueOf(String.format("%.2f", Double.valueOf(list.get(0).getCountMoney())+Double.valueOf(list.get(0).getBillMoney()))));
		}else if(list.get(0).getStatus().equals("1"))  {
			vo1.setCountMoney(String.valueOf(String.format("%.2f", Double.valueOf(list.get(0).getCountMoney())-Double.valueOf(list.get(0).getBillMoney()))));
		}
		dateBillDao.updateDateBill(vo1);
		int flag = dateBillDao.deleteDateBill(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
