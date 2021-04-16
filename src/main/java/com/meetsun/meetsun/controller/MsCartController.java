package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.MsCartService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCartVo;

@RestController
@RequestMapping("/msCart")
public class MsCartController {
	@Autowired
	private MsCartService msCartService;
	
	@RequestMapping({"/getMsCartList"})
	public Result<Object> getMsCartList(@RequestBody MsCartVo vo,HttpServletRequest request) { 
		return msCartService.getMsCartList(vo); 
	}
	
	@RequestMapping({"/getMsCartListBySpId"})
	public Result<Object> getMsCartListBySpId(@RequestBody MsCartVo vo,HttpServletRequest request) { 
		return msCartService.getMsCartListBySpId(vo); 
	}

	@RequestMapping({"/saveMsCart"})
	public Result<Object> saveMsCart(@RequestBody MsCartVo vo,HttpServletRequest request) {
		return msCartService.saveMsCart(vo); 
	}

	@RequestMapping({"/updateMsCart"})
	public Result<Object> updateMsCart(@RequestBody MsCartVo vo) { 
		return msCartService.updateMsCart(vo); 
	}

	@RequestMapping({"/deleteMsCart"})
	public Result<Object> deleteMsCart(@RequestBody MsCartVo vo) { 
		return msCartService.deleteMsCart(vo); 
	}
}
