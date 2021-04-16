package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.ARoleService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ARoleVo;

@RestController
@RequestMapping("/aRole")
public class ARoleController {
	@Autowired
	private ARoleService aRoleService;
	
	@RequestMapping({"/getARoleList"})
	public Result<Object> getARoleList(@RequestBody ARoleVo vo,HttpServletRequest request) { 
		return aRoleService.getARoleList(vo); 
	}
	
	@Log(operation = "新增" , remark="【角色管理】新增角色" , type = "0")
	@RequestMapping({"/saveARole"})
	public Result<Object> saveARole(@RequestBody ARoleVo vo,HttpServletRequest request) {
		return aRoleService.saveARole(vo); 
	}

	@Log(operation = "修改" , remark="【角色管理】修改角色" , type = "0")
	@RequestMapping({"/updateARole"})
	public Result<Object> updateARole(@RequestBody ARoleVo vo) { 
		return aRoleService.updateARole(vo); 
	}

	@Log(operation = "删除" , remark="【角色管理】删除角色" , type = "0")
	@RequestMapping({"/deleteARole"})
	public Result<Object> deleteARole(@RequestBody ARoleVo vo) { 
		return aRoleService.deleteARole(vo); 
	}
}
