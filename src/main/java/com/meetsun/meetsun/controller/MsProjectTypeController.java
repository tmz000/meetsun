package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsProjectTypeService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsProjectTypeVo;
import com.meetsun.meetsun.vo.MsUserVo;

@RestController
@RequestMapping("/msProjectType")
public class MsProjectTypeController {
	@Autowired
	private MsProjectTypeService msProjectTypeService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsProjectTypeList"})
	public Result<Object> getMsProjectTypeList(@RequestBody MsProjectTypeVo vo,HttpServletRequest request) { 
		return msProjectTypeService.getMsProjectTypeList(vo); 
	}

	@Log(operation = "新增" , remark="【产品类型】新增产品类型" , type = "0")
	@RequestMapping({"/saveMsProjectType"})
	public Result<Object> saveMsProjectType(@RequestBody MsProjectTypeVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(msUser.getRealName());
		return msProjectTypeService.saveMsProjectType(vo); 
	}

	@Log(operation = "修改" , remark="【产品类型】修改产品类型" , type = "0")
	@RequestMapping({"/updateMsProjectType"})
	public Result<Object> updateMsProjectType(@RequestBody MsProjectTypeVo vo) { 
		return msProjectTypeService.updateMsProjectType(vo); 
	}

	@Log(operation = "删除" , remark="【产品类型】删除产品类型" , type = "0")
	@RequestMapping({"/deleteMsProjectType"})
	public Result<Object> deleteMsProjectType(@RequestBody MsProjectTypeVo vo) { 
		return msProjectTypeService.deleteMsProjectType(vo); 
	}
}
