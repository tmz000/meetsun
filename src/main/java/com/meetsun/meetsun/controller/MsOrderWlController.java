package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.MsOrderWlService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderWlVo;

@RestController
@RequestMapping("/msOrderWl")
public class MsOrderWlController {
	@Autowired
	private MsOrderWlService msOrderWlService;
	
	@RequestMapping({"/getMsOrderWlList"})
	public Result<Object> getMsOrderWlList(@RequestBody MsOrderWlVo vo,HttpServletRequest request) { 
		return msOrderWlService.getMsOrderWlList(vo); 
	}
	
	@Log(operation = "新增" , remark="【订单物流】新增订单物流信息" , type = "0")
	@RequestMapping({"/saveMsOrderWl"})
	public Result<Object> saveMsOrderWl(@RequestBody MsOrderWlVo vo,HttpServletRequest request) {
		return msOrderWlService.saveMsOrderWl(vo); 
	}
   
	@Log(operation = "修改" , remark="【订单物流】修改订单物流信息" , type = "0")
	@RequestMapping({"/updateMsOrderWl"})
	public Result<Object> updateMsOrderWl(@RequestBody MsOrderWlVo vo) { 
		return msOrderWlService.updateMsOrderWl(vo); 
	}
   
	@Log(operation = "删除" , remark="【订单物流】删除订单物流信息" , type = "0")
	@RequestMapping({"/deleteMsOrderWl"})
	public Result<Object> deleteMsOrderWl(@RequestBody MsOrderWlVo vo) { 
		return msOrderWlService.deleteMsOrderWl(vo); 
	}
}
