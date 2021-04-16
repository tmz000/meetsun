package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.ARoleMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ARoleMenuVo;

@RestController
@RequestMapping("/aRoleMenu")
public class ARoleMenuController {
	@Autowired
	private ARoleMenuService aRoleMenuService;
	
	@RequestMapping({"/getARoleMenuList"})
	public Result<Object> getARoleMenuList(@RequestBody ARoleMenuVo vo,HttpServletRequest request) { 
		return aRoleMenuService.getARoleMenuList(vo); 
	}

	@Log(operation = "新增" , remark="【角色权限】新增角色权限" , type = "0")
	@RequestMapping({"/saveARoleMenu"})
	public Result<Object> saveARoleMenu(@RequestBody ARoleMenuVo vo,HttpServletRequest request) {
		return aRoleMenuService.saveARoleMenu(vo); 
	}

	@Log(operation = "修改" , remark="【角色权限】修改角色权限" , type = "0")
	@RequestMapping({"/updateARoleMenu"})
	public Result<Object> updateARoleMenu(@RequestBody ARoleMenuVo vo) { 
		return aRoleMenuService.updateARoleMenu(vo); 
	}

	@Log(operation = "删除" , remark="【角色权限】删除角色权限" , type = "0")
	@RequestMapping({"/deleteARoleMenu"})
	public Result<Object> deleteARoleMenu(@RequestBody ARoleMenuVo vo) { 
		return aRoleMenuService.deleteARoleMenu(vo); 
	}
}
