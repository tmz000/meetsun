package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.IncomePayService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.IncomePayVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/incomePay")
public class IncomePayController {
	@Autowired
	private IncomePayService incomePayService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getIncomePayList"})
	public Result<Object> getIncomePayList(@RequestBody IncomePayVo vo) { 
		return incomePayService.getIncomePayList(vo); 
	}

	@Log(operation = "新增" , remark="【新增】新增收支概览" , type = "0")
	@RequestMapping({"/saveIncomePay"})
	public Result<Object> saveIncomePay(@RequestBody IncomePayVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(us.getRealName());
		return incomePayService.saveIncomePay(vo); 
	}

	@Log(operation = "修改" , remark="【收支概览】修改收支概览" , type = "0")
	@RequestMapping({"/updateIncomePay"})
	public Result<Object> updateIncomePay(@RequestBody IncomePayVo vo) { 
		return incomePayService.updateIncomePay(vo); 
	}

	@Log(operation = "删除" , remark="【收支概览】删除收支概览" , type = "0")
	@RequestMapping({"/deleteIncomePay"})
	public Result<Object> deleteIncomePay(@RequestBody IncomePayVo vo) { 
		return incomePayService.deleteIncomePay(vo); 
	}
	
	@RequestMapping({"/getIncomePayListByStatus"})
	public Result<Object> getIncomePayListByStatus(@RequestBody IncomePayVo vo) { 
		return incomePayService.getIncomePayListByStatus(vo); 
	}
	
	@RequestMapping({"/getIncomePayListByCreateTime"})
	public Result<Object> getIncomePayListByCreateTime(@RequestBody IncomePayVo vo) { 
		return incomePayService.getIncomePayListByCreateTime(vo); 
	}
}
