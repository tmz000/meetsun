package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.service.LogInfoService;
import com.meetsun.meetsun.until.Result;

@RestController
@RequestMapping("/logInfo")
public class LogInfoController {
	@Autowired
	private LogInfoService logInfoService;
	
	@RequestMapping({"/getLogInfoList"})
	public Result<Object> getALogInfoList(@RequestBody LogInfo vo,HttpServletRequest request) { 
		return logInfoService.getLogInfoList(vo); 
	}
   
}
