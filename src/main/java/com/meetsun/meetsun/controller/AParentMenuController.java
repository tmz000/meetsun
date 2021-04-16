package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.AParentMenuService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.AParentMenuVo;

@RestController
@RequestMapping("/aParentMenu")
public class AParentMenuController {
	@Autowired
	private AParentMenuService aParentMenuService;
	
	@RequestMapping({"/getAParentMenuList"})
	public Result<Object> getAParentMenuList(@RequestBody AParentMenuVo vo,HttpServletRequest request) { 
		return aParentMenuService.getAParentMenuList(vo); 
	}
	
	@Log(operation = "新增" , remark="【父类菜单管理】新增父类菜单" , type = "0")
	@RequestMapping({"/saveAParentMenu"})
	public Result<Object> saveAParentMenu(@RequestBody AParentMenuVo vo,HttpServletRequest request) {
		return aParentMenuService.saveAParentMenu(vo); 
	}
   
	@Log(operation = "修改" , remark="【父类菜单管理】修改父类菜单" , type = "0")
	@RequestMapping({"/updateAParentMenu"})
	public Result<Object> updateAParentMenu(@RequestBody AParentMenuVo vo) { 
		return aParentMenuService.updateAParentMenu(vo); 
	}
   
	@Log(operation = "删除" , remark="【父类菜单管理】删除父类菜单" , type = "0")
	@RequestMapping({"/deleteAParentMenu"})
	public Result<Object> deleteAParentMenu(@RequestBody AParentMenuVo vo) { 
		return aParentMenuService.deleteAParentMenu(vo); 
	}
}
