package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsVisitService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.MsVisitVo;

@RestController
@RequestMapping("/msVisit")
public class MsVisitController {
	@Autowired
	private MsVisitService msVisitService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsVisitList"})
	public Result<Object> getMsVisitList(@RequestBody MsVisitVo vo,HttpServletRequest request) { 
		return msVisitService.getMsVisitList(vo); 
	}
	
	@Log(operation = "新增" , remark="【到访信息】新增到访信息" , type = "0")
	@RequestMapping({"/saveMsVisit"})
	public Result<Object> saveMsVisit(@RequestBody MsVisitVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setRealName(msUser.getRealName());
		return msVisitService.saveMsVisit(vo); 
	}
   
	@Log(operation = "修改" , remark="【到访信息】修改到访信息" , type = "0")
	@RequestMapping({"/updateMsVisit"})
	public Result<Object> updateMsVisit(@RequestBody MsVisitVo vo) { 
		return msVisitService.updateMsVisit(vo); 
	}
   
	@Log(operation = "删除" , remark="【到访信息】删除到访信息" , type = "0")
	@RequestMapping({"/deleteMsVisit"})
	public Result<Object> deleteMsVisit(@RequestBody MsVisitVo vo) { 
		return msVisitService.deleteMsVisit(vo); 
	}
}
