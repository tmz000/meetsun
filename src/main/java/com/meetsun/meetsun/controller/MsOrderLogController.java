package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.MsOrderLogService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderLogVo;

@RestController
@RequestMapping("/msOrderLog")
public class MsOrderLogController {
	@Autowired
	private MsOrderLogService msOrderLogService;
	
	@RequestMapping({"/getMsOrderLogList"})
	public Result<Object> getMsOrderLogList(@RequestBody MsOrderLogVo vo,HttpServletRequest request) { 
		return msOrderLogService.getMsOrderLogList(vo); 
	}
	
	@Log(operation = "新增" , remark="【订单日志】新增订单日志" , type = "0")
	@RequestMapping({"/saveMsOrderLog"})
	public Result<Object> saveMsOrderLog(@RequestBody MsOrderLogVo vo,HttpServletRequest request) {
		return msOrderLogService.saveMsOrderLog(vo); 
	}
   
	@Log(operation = "修改" , remark="【订单日志】修改订单日志" , type = "0")
	@RequestMapping({"/updateMsOrderLog"})
	public Result<Object> updateMsOrderLog(@RequestBody MsOrderLogVo vo) { 
		return msOrderLogService.updateMsOrderLog(vo); 
	}
   
	@Log(operation = "删除" , remark="【订单日志】删除订单日志" , type = "0")
	@RequestMapping({"/deleteMsOrderLog"})
	public Result<Object> deleteMsOrderLog(@RequestBody MsOrderLogVo vo) { 
		return msOrderLogService.deleteMsOrderLog(vo); 
	}
}
