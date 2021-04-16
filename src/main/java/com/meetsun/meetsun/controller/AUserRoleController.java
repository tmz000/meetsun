package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.AUserRoleService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AUserRoleVo;

@RestController
@RequestMapping("/aUserRole")
public class AUserRoleController {
	@Autowired
	private AUserRoleService aUserRoleService;
	
	@RequestMapping({"/getAUserRoleList"})
	public Result<Object> getAUserRoleList(@RequestBody AUserRoleVo vo,HttpServletRequest request) { 
		return aUserRoleService.getAUserRoleList(vo); 
	}

	@Log(operation = "新增")
	@RequestMapping({"/saveAUserRole"})
	public Result<Object> saveAUserRole(@RequestBody AUserRoleVo vo,HttpServletRequest request) {
		return aUserRoleService.saveAUserRole(vo); 
	}

	@Log(operation = "修改")
	@RequestMapping({"/updateAUserRole"})
	public Result<Object> updateAUserRole(@RequestBody AUserRoleVo vo) { 
		return aUserRoleService.updateAUserRole(vo); 
	}

	@Log(operation = "删除")
	@RequestMapping({"/deleteAUserRole"})
	public Result<Object> deleteAUserRole(@RequestBody AUserRoleVo vo) { 
		return aUserRoleService.deleteAUserRole(vo); 
	}
}
