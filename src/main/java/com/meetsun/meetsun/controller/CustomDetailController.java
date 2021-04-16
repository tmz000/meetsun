package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.CustomDetailService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.CustomDetailVo;

@RestController
@RequestMapping("/customDetail")
public class CustomDetailController {
	@Autowired
	private CustomDetailService customDetailService;
	
	@RequestMapping({"/getCustomDetailList"})
	public Result<Object> getCustomDetailList(@RequestBody CustomDetailVo vo,HttpServletRequest request) { 
		return customDetailService.getCustomDetailList(vo); 
	}

	@Log(operation = "新增" , remark="【会员明细】" , type = "0")
	@RequestMapping({"/saveCustomDetail"})
	public Result<Object> saveCustomDetail(@RequestBody CustomDetailVo vo,HttpServletRequest request) {
		//MsUser msUser = (MsUser)request.getSession().getAttribute("msUser");
		//vo.setUserName(msUser.getRealName());
		return customDetailService.saveCustomDetail(vo); 
	}

	@Log(operation = "修改" , remark="【会员明细】" , type = "0")
	@RequestMapping({"/updateCustomDetail"})
	public Result<Object> updateCustomDetail(@RequestBody CustomDetailVo vo) { 
		return customDetailService.updateCustomDetail(vo); 
	}

	@Log(operation = "删除" , remark="【会员明细】" , type = "0")
	@RequestMapping({"/deleteCustomDetail"})
	public Result<Object> deleteCustomDetail(@RequestBody CustomDetailVo vo) { 
		return customDetailService.deleteCustomDetail(vo); 
	}
}
