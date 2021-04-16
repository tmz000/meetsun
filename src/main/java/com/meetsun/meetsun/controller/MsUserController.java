package com.meetsun.meetsun.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.ARoleDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsUserService;
import com.meetsun.meetsun.until.EnandeDecrypt;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ARoleVo;
import com.meetsun.meetsun.vo.MsUserVo;
 
 
 
@RestController
@RequestMapping({"/msUser"})
public class MsUserController {
	@Autowired
	private MsUserService msUserService;
	@Autowired
	private MsUserDao msUserDao;
	@Autowired
	private ARoleDao aRoleDao;
   
	@RequestMapping({"/saveMsUser"})
	@Log(operation = "新增" , remark="【用户管理】新增后台用户信息" , type = "0")
	public Result<Object> saveMsUser(@RequestBody MsUserVo us) { 
		return msUserService.saveMsUser(us); 
	}
	
	//@Log(operation = "登录" , remark="【用户管理】后台用户登录" , type = "0")
	@RequestMapping({"/loginMsUser"})
	public Result<Object> loginMsUser(@RequestBody MsUserVo us) { 
		return msUserService.getMsUser(us); 
	}
   
	@RequestMapping({"/getMsUser"})
	public Result<Object> getMsUser(HttpServletRequest request) throws Exception {
		String param = request.getQueryString();
		if(param != null) {
			String token = param.split("=")[1];
			MsUserVo mvo =new MsUserVo();
			mvo.setSysId(token);
			MsUser us = msUserDao.getMsUser(mvo).get(0);
			String roleId = us.getRoleId();
			if(roleId != "" && roleId != null) {
				ARoleVo vo =new ARoleVo();
				vo.setSysId(roleId);
				List<ARole> li = aRoleDao.getARoleList(vo);
				us.setRoleName(li.get(0).getRoleName());
				us.setPassWord(new String(EnandeDecrypt.decryptBASE64(us.getPassWord())));
			}
			return Result.success(us);
		}
		return Result.error("系统异常，请联系管理员！");
	}
	
	@RequestMapping(value = {"/getAllMsUser"}, method = {RequestMethod.POST})
	public Result<Object> getAllMsUser(@RequestBody MsUserVo us) { 
		return msUserService.getAllMsUser(us); 
	}
	
	@Log(operation = "修改" , remark="【用户管理】修改后台用户信息" , type = "0")
	@RequestMapping(value = {"/updateMsUserBySysId"}, method = {RequestMethod.POST})
	public Result<Object> updateMsUserBySysId(@RequestBody MsUserVo us, SessionStatus sessionStatus,HttpSession session,HttpServletRequest request) { 
		return msUserService.updateMsUserBySysId(us); 
	}
	
	@Log(operation = "删除" , remark="【用户管理】删除后台用户信息" , type = "0")
	@RequestMapping(value = {"/deleteMsUserBySysId"}, method = {RequestMethod.POST})
	public Result<Object> deleteMsUserBySysId(MsUserVo us) { 
		return msUserService.deleteMsUserBySysId(us); 
	}
 }
