package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsBroadcastService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsBroadcastVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/msBroadcast")
public class MsBroadcastController {
	@Autowired
	private MsBroadcastService msBroadcastService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsBroadcastList"})
	public Result<Object> getMsBroadcastList(@RequestBody MsBroadcastVo vo,HttpServletRequest request) { 
		return msBroadcastService.getMsBroadcastList(vo); 
	}

	@Log(operation = "新增" , remark="【轮播】新增轮播" , type = "0")
	@RequestMapping({"/saveMsBroadcast"})
	public Result<Object> saveMsBroadcast(@RequestParam("file") MultipartFile file,@RequestParam("vo") String vo1,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		JSON json = JSONObject.parseObject(vo1);
		MsBroadcastVo vo = JSONObject.toJavaObject(json,MsBroadcastVo.class);
		vo.setUserName(us.getRealName());
		vo.setFile(file);
		return msBroadcastService.saveMsBroadcast(vo); 
	}

	@Log(operation = "修改" , remark="【轮播】修改轮播" , type = "0")
	@RequestMapping({"/updateMsBroadcast"})
	public Result<Object> updateMsBroadcast(@RequestBody MsBroadcastVo vo) { 
		return msBroadcastService.updateMsBroadcast(vo); 
	}

	@Log(operation = "删除" , remark="【轮播】 删除轮播" , type = "0")
	@RequestMapping({"/deleteMsBroadcast"})
	public Result<Object> deleteMsBroadcast(@RequestBody MsBroadcastVo vo) { 
		return msBroadcastService.deleteMsBroadcast(vo); 
	}
}
