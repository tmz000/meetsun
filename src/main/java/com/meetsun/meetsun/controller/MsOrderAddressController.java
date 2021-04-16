package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.service.MsOrderAddressService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsOrderAddressVo;

@RestController
@RequestMapping("/msOrderAddress")
public class MsOrderAddressController {
	@Autowired
	private MsOrderAddressService msOrderAddressService;
	
	@RequestMapping({"/getMsOrderAddressList"})
	public Result<Object> getMsOrderAddressList(@RequestBody MsOrderAddressVo vo,HttpServletRequest request) { 
		vo.setOpendId((String)request.getSession().getAttribute("opendId"));
		return msOrderAddressService.getMsOrderAddressList(vo); 
	}

	@Log(operation = "新增")
	@RequestMapping({"/saveMsOrderAddress"})
	public Result<Object> saveMsOrderAddress(@RequestBody MsOrderAddressVo vo,HttpServletRequest request) {
		MsCustom msCustom = (MsCustom)request.getSession().getAttribute("msCustom");
		vo.setUserName(msCustom.getUserName());
		vo.setOpendId(msCustom.getOpendId());
		return msOrderAddressService.saveMsOrderAddress(vo); 
	}

	@Log(operation = "修改")
	@RequestMapping({"/updateMsOrderAddress"})
	public Result<Object> updateMsOrderAddress(@RequestBody MsOrderAddressVo vo) { 
		return msOrderAddressService.updateMsOrderAddress(vo); 
	}

	@Log(operation = "删除")
	@RequestMapping({"/deleteMsOrderAddress"})
	public Result<Object> deleteMsOrderAddress(@RequestBody MsOrderAddressVo vo) { 
		return msOrderAddressService.deleteMsOrderAddress(vo); 
	}
}
