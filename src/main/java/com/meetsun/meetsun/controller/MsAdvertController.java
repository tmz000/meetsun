package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsAdvertService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsAdvertVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/msAdvert")
public class MsAdvertController {
	@Autowired
	private MsAdvertService msAdvertService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping("/getMsAdvertList")
	public Result<Object> getMsAdvertList(@RequestBody MsAdvertVo vo,HttpServletRequest request) { 
		return msAdvertService.getMsAdvertList(vo); 
	}

	@Log(operation = "新增" , remark="【广告】新增广告" , type = "0")
	@RequestMapping(value = "/saveMsAdvert", method = RequestMethod.POST)
	public Result<Object> saveMsAdvert(@RequestParam("file") MultipartFile file,@RequestParam("vo") String vo1,HttpServletRequest request) {
		JSON json = JSONObject.parseObject(vo1);
		MsAdvertVo vo = JSONObject.toJavaObject(json,MsAdvertVo.class);
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(us.getRealName());
		vo.setFile(file);
		vo.setRealPath((String)request.getSession().getServletContext().getRealPath("/"));
		return msAdvertService.saveMsAdvert(vo); 
	}

	@Log(operation = "修改" , remark="【广告】修改广告" , type = "0")
	@RequestMapping({"/updateMsAdvert"})
	public Result<Object> updateMsAdvert(@RequestBody MsAdvertVo vo) { 
		return msAdvertService.updateMsAdvert(vo); 
	}

	@Log(operation = "删除" , remark="【广告】删除广告" , type = "0")
	@RequestMapping({"/deleteMsAdvert"})
	public Result<Object> deleteMsAdvert(@RequestBody MsAdvertVo vo) { 
		return msAdvertService.deleteMsAdvert(vo); 
	}
}
