package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.AMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AMenuVo;

@RestController
@RequestMapping("/aMenu")
public class AMenuController {
	@Autowired
	private AMenuService aMenuService;
	
	@RequestMapping({"/getAMenuList"})
	public Result<Object> getAMenuList(@RequestBody AMenuVo vo,HttpServletRequest request) { 
		return aMenuService.getAMenuList(vo); 
	}
	
	@Log(operation = "新增" , remark="【菜单管理】新增菜单" , type = "0")
	@RequestMapping({"/saveAMenu"})
	public Result<Object> saveAMenu(@RequestBody AMenuVo vo,HttpServletRequest request) {
		return aMenuService.saveAMenu(vo); 
	}
   
	@Log(operation = "修改" , remark="【菜单管理】修改菜单" , type = "0")
	@RequestMapping({"/updateAMenu"})
	public Result<Object> updateAMenu(@RequestBody AMenuVo vo) { 
		return aMenuService.updateAMenu(vo); 
	}
   
	@Log(operation = "删除" , remark="【菜单管理】删除菜单" , type = "0")
	@RequestMapping({"/deleteAMenu"})
	public Result<Object> deleteAMenu(@RequestBody AMenuVo vo) { 
		return aMenuService.deleteAMenu(vo); 
	}
}
