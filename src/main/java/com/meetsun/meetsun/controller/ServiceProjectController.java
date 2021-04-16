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
import com.meetsun.meetsun.service.ServiceProjectService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;
 
 
@RestController
@RequestMapping({"/serviceProject"})
public class ServiceProjectController {
	@Autowired
	private ServiceProjectService serviceProjectService;
	@Autowired
	private MsUserDao msUserDao;
   
	@RequestMapping({"/getServiceProjectList"})
	public Result<Object> getServiceProjectList(@RequestBody ServiceProjectVo vo,HttpServletRequest request) { 
		return serviceProjectService.getServiceProjectList(vo); 
	}
	
	@RequestMapping({"/getServiceProjectListByTypeName"})
	public Result<Object> getServiceProjectListByTypeName(@RequestBody ServiceProjectVo vo,HttpServletRequest request) { 
		return serviceProjectService.getServiceProjectList(vo); 
	}

	@Log(operation = "新增" , remark="【产品管理】新增产品信息" , type = "0")
	@RequestMapping({"/saveServiceProject"})
	public Result<Object> saveServiceProject(@RequestParam("detailFile") MultipartFile detailFile,@RequestParam("vo") String vo1,HttpServletRequest request) {
		String handPhoto = (String) request.getSession().getAttribute("handPhoto");
		JSON json = JSONObject.parseObject(vo1);
		ServiceProjectVo vo = JSONObject.toJavaObject(json,ServiceProjectVo.class);
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(msUser.getRealName());
		vo.setDetailFile(detailFile);
		vo.setHandPhoto(handPhoto);
		request.getSession().removeAttribute("handPhoto");
		return serviceProjectService.saveServiceProject(vo); 
	}

	@RequestMapping({"/addHandFile"})
	public Result<Object> addHandFile(@RequestParam("handFile") MultipartFile handFile,HttpServletRequest request) {
		String handPhoto = (String) request.getSession().getAttribute("handPhoto");
		String file = Tools.savePhoto(handFile);
		if(handPhoto == null) {
			request.getSession().setAttribute("handPhoto",file);
			return Result.success("success");
		}else {
			request.getSession().setAttribute("handPhoto",handPhoto+","+file);
			return Result.success("success");
		}
	}

	@Log(operation = "修改" , remark="【产品管理】修改产品信息" , type = "0")
	@RequestMapping({"/updateServiceProject"})
	public Result<Object> updateServiceProject(@RequestBody ServiceProjectVo vo) { 
		return serviceProjectService.updateServiceProject(vo); 
	}

	@Log(operation = "删除" , remark="【产品管理】删除产品信息" , type = "0")
	@RequestMapping({"/deleteServiceProject"})
	public Result<Object> deleteServiceProject(@RequestBody ServiceProjectVo vo) { 
		return serviceProjectService.deleteServiceProject(vo); 
	}
 }