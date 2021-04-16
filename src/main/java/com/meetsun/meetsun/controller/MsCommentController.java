package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsCommentService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsCommentVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/msComment")
public class MsCommentController {
	@Autowired
	private MsCommentService msCommentService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsCommentList"})
	public Result<Object> getMsCommentList(@RequestBody MsCommentVo vo,HttpServletRequest request) { 
		return msCommentService.getMsCommentList(vo); 
	}

	@RequestMapping({"/getMsCommentListByStarLv"})
	public Result<Object> getMsCommentListByStarLv(@RequestBody MsCommentVo vo,HttpServletRequest request) { 
		return msCommentService.getMsCommentListByStarLv(vo); 
	}
	
	@Log(operation = "新增" , remark="【产品评价】新增产品评价" , type = "0")
	@RequestMapping({"/saveMsComment"})
	public Result<Object> saveMsComment(@RequestBody MsCommentVo vo,HttpServletRequest request) {
		MsUser msUser = getUser(request);
		vo.setUserName(msUser.getRealName());
		vo.setCustomSysId(vo.getSysId());
		return msCommentService.saveMsComment(vo); 
	}

	@Log(operation = "修改" , remark="【产品评价】修改产品评价" , type = "0")
	@RequestMapping({"/updateMsComment"})
	public Result<Object> updateMsComment(@RequestBody MsCommentVo vo,HttpServletRequest request) { 
		MsUser msUser = getUser(request);
		vo.setAdminName(msUser.getRealName());
		return msCommentService.updateMsComment(vo); 
	}

	@Log(operation = "删除" , remark="【产品评价】删除产品评价" , type = "0")
	@RequestMapping({"/deleteMsComment"})
	public Result<Object> deleteMsComment(@RequestBody MsCommentVo vo) { 
		return msCommentService.deleteMsComment(vo); 
	}
	
	public MsUser getUser(HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = msUserDao.getMsUser(mvo).get(0);
		return us;
	}
}
