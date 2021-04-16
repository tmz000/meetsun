package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.PaySureService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.PaySureVo;

@RestController
@RequestMapping("/paySure")
public class PaySureController {
	@Autowired
	private PaySureService paySureService;
	
	@RequestMapping({"/getPaySureList"})
	public Result<Object> getPaySureList(@RequestBody PaySureVo vo,HttpServletRequest request) { 
		return paySureService.getPaySureList(vo); 
	}
	
	@Log(operation = "新增" , remark="【支付确认】新增支付确认" , type = "0")
	@RequestMapping({"/savePaySure"})
	public Result<Object> savePaySure(@RequestBody PaySureVo vo,HttpServletRequest request) {
		return paySureService.savePaySure(vo); 
	}
   
	@Log(operation = "修改" , remark="【支付确认】修改支付确认" , type = "0")
	@RequestMapping({"/updatePaySure"})
	public Result<Object> updatePaySure(@RequestBody PaySureVo vo) { 
		return paySureService.updatePaySure(vo); 
	}
   
	@Log(operation = "删除" , remark="【支付确认】删除支付确认" , type = "0")
	@RequestMapping({"/deletePaySure"})
	public Result<Object> deletePaySure(@RequestBody PaySureVo vo) { 
		return paySureService.deletePaySure(vo); 
	}
}
