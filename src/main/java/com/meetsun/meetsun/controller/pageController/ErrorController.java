package com.meetsun.meetsun.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	//error403页面
	@RequestMapping("/error403")
	public String error403() { 
		return "error/error403"; 
	}
	
	//error404页面
	@RequestMapping("/error404")
	public String error404() { 
		return "error/error404"; 
	}
	
	//error405页面
	@RequestMapping("/error405")
	public String error405() { 
		return "error/error405"; 
	}
	
	//error500页面
	@RequestMapping("/error500")
	public String error500() { 
		return "error/error500"; 
	}
	
	//暂无权限页面
	@RequestMapping({"/noLock"})
	public String noLock() { 
		return "error/no-lock"; 
	}
}
