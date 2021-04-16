package com.meetsun.meetsun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.MsCustomService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCustomVo;

@RestController
@RequestMapping("/msCustom")
public class MsCustomController {
	@Autowired
	private MsCustomService msCustomService;
	
	@RequestMapping({"/getMsCustomList"})
	public Result<Object> getMsCustomList(@RequestBody MsCustomVo vo) { 
		return msCustomService.getMsCustomList(vo); 
	}
	
	@RequestMapping({"/getMsCustomByName"})
	public Result<Object> getMsCustomByName(@RequestBody MsCustomVo vo) { 
		return msCustomService.getMsCustomByName(vo); 
	}

	@Log(operation = "新增" , remark="【客户】新增客户" , type = "0")
	@RequestMapping({"/saveMsCustom"})
	public Result<Object> saveMsCustom(@RequestBody MsCustomVo vo) {
		return msCustomService.saveMsCustom(vo); 
	}

	@Log(operation = "修改" , remark="【客户】新增客户" , type = "0")
	@RequestMapping({"/updateMsCustom"})
	public Result<Object> updateMsCustom(@RequestBody MsCustomVo vo) { 
		return msCustomService.updateMsCustom(vo); 
	}

	@Log(operation = "删除" , remark="【客户】删除客户" , type = "0")
	@RequestMapping({"/deleteMsCustom"})
	public Result<Object> deleteMsCustom(@RequestBody MsCustomVo vo) { 
		return msCustomService.deleteMsCustom(vo); 
	}

	@Log(operation = "修改",remark = "【客户】修改客户会员卡可用金额和积分" , type = "0")
	@RequestMapping({"/updateMsCustomByCardMoney"})
	public Result<Object> updateMsCustomByCardMoney(@RequestBody MsCustomVo vo) { 
		return msCustomService.updateMsCustomByCardMoney(vo); 
	}
}
