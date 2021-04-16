package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsOrderService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/msOrder")
public class MsOrderController {
	
	@Autowired
	private MsOrderService msOrderService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsOrderList"})
	public Result<Object> getMsOrderList(@RequestBody MsOrderVo vo) { 
		return msOrderService.getMsOrderList(vo); 
	}

	@Log(operation = "新增" , remark="【订单】新增订单" , type = "0")
	@RequestMapping({"/saveMsOrder"})
	public Result<Object> saveMsOrder(@RequestBody MsOrderVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(msUser.getRealName());
		return msOrderService.saveMsOrder(vo); 
	}

	@Log(operation = "修改" , remark="【订单】修改订单信息" , type = "0")
	@RequestMapping({"/updateMsOrder"})
	public Result<Object> updateMsOrder(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrder(vo); 
	}

	@Log(operation = "修改",remark = "【订单】修改客户的使用状态" , type = "0")
	@RequestMapping({"/updateMsOrderByIsUse"})
	public Result<Object> updateMsOrderByIsUse(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByIsUse(vo); 
	}
	
	@Log(operation = "修改",remark = "【订单】修改客户的收货状态" , type = "0")
	@RequestMapping({"/updateMsOrderByIsShouhuo"})
	public Result<Object> updateMsOrderByIsShouhuo(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByIsShouhuo(vo); 
	}
	
	@Log(operation = "修改",remark = "【订单】手动入账操作" , type = "0")
	@RequestMapping({"/updateMsOrderByFlag"})
	public Result<Object> updateMsOrderByFlag(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByFlag(vo); 
	}

	@Log(operation = "删除" , remark="【订单】删除订单" , type = "0")
	@RequestMapping({"/deleteMsOrder"})
	public Result<Object> deleteMsOrder(@RequestBody MsOrderVo vo) { 
		return msOrderService.deleteMsOrder(vo); 
	}
	
	@RequestMapping({"/getMsOrderListByMonth"})
	public Result<Object> getMsOrderListByMonth(@RequestBody MsOrderVo vo) { 
		return msOrderService.getMsOrderListByMonth(vo); 
	}
}
