package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.FollowUpService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.FollowUpVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/followUp")
public class FollowUpController {
	@Autowired
	private FollowUpService followUpService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getFollowUpList"})
	public Result<Object> getFollowUpList(@RequestBody FollowUpVo vo,HttpServletRequest request) { 
		return followUpService.getFollowUpList(vo); 
	}
	
	@Log(operation = "新增" , remark="【跟进信息】新增跟进信息" , type = "0")
	@RequestMapping({"/saveFollowUp"})
	public Result<Object> saveFollowUp(@RequestBody FollowUpVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		vo.setRealName(us.getRealName());
		return followUpService.saveFollowUp(vo); 
	}
   
	@Log(operation = "修改" , remark="【跟进信息】修改跟进信息" , type = "0")
	@RequestMapping({"/updateFollowUp"})
	public Result<Object> updateFollowUp(@RequestBody FollowUpVo vo) { 
		return followUpService.updateFollowUp(vo); 
	}
   
	@Log(operation = "删除" , remark="【跟进信息】删除跟进信息" , type = "0")
	@RequestMapping({"/deleteFollowUp"})
	public Result<Object> deleteFollowUp(@RequestBody FollowUpVo vo) { 
		return followUpService.deleteFollowUp(vo); 
	}
}
