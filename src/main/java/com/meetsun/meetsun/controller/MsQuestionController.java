package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.MsQuestionService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsQuestionVo;

@RestController
@RequestMapping("/msQuestion")
public class MsQuestionController {
	@Autowired
	private MsQuestionService msQuestionService;
	
	@RequestMapping({"/getMsQuestionList"})
	public Result<Object> getMsQuestionList(@RequestBody MsQuestionVo vo,HttpServletRequest request) { 
		return msQuestionService.getMsQuestionList(vo); 
	}
	
	@Log(operation = "新增" , remark="【反馈信息】新增反馈信息" , type = "0")
	@RequestMapping({"/saveMsQuestion"})
	public Result<Object> saveMsQuestion(@RequestBody MsQuestionVo vo,HttpServletRequest request) {
		return msQuestionService.saveMsQuestion(vo); 
	}
   
	@Log(operation = "修改" , remark="【反馈信息】修改反馈信息" , type = "0")
	@RequestMapping({"/updateMsQuestion"})
	public Result<Object> updateMsQuestion(@RequestBody MsQuestionVo vo) { 
		return msQuestionService.updateMsQuestion(vo); 
	}
   
	@Log(operation = "删除" , remark="【反馈信息】删除反馈信息" , type = "0")
	@RequestMapping({"/deleteMsQuestion"})
	public Result<Object> deleteMsQuestion(@RequestBody MsQuestionVo vo) { 
		return msQuestionService.deleteMsQuestion(vo); 
	}
}
