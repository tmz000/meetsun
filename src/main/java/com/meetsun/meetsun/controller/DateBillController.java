package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.DateBillService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.DateBillVo;

@RestController
@RequestMapping("/dateBill")
public class DateBillController {
	@Autowired
	private DateBillService dateBillService;
	
	@RequestMapping({"/getDateBillList"})
	public Result<Object> getDateBillList(@RequestBody DateBillVo vo,HttpServletRequest request) { 
		return dateBillService.getDateBillList(vo); 
	}
	
	@RequestMapping({"/getDateBillListByType"})
	public Result<Object> getDateBillListByType(@RequestBody DateBillVo vo,HttpServletRequest request) { 
		return dateBillService.getDateBillListByType(vo); 
	}
	
	@RequestMapping({"/getDateBillListByYear"})
	public Result<Object> getDateBillListByYear(@RequestBody DateBillVo vo,HttpServletRequest request) { 
		return dateBillService.getDateBillListByYear(vo); 
	}
	
	//@Log(operation = "新增" , remark="【日志账单】新增日志账单" , type = "0")
	@RequestMapping({"/saveDateBill"})
	public Result<Object> saveDateBill(@RequestBody DateBillVo vo,HttpServletRequest request) {
		return dateBillService.saveDateBill(vo); 
	}
   
	//@Log(operation = "修改" , remark="【日志账单】修改日志账单" , type = "0")
	@RequestMapping({"/updateDateBill"})
	public Result<Object> updateDateBill(@RequestBody DateBillVo vo) { 
		return dateBillService.updateDateBill(vo); 
	}
   
	//@Log(operation = "删除" , remark="【日志账单】删除日志账单" , type = "0")
	@RequestMapping({"/deleteDateBill"})
	public Result<Object> deleteDateBill(@RequestBody DateBillVo vo) { 
		return dateBillService.deleteDateBill(vo); 
	}
}
