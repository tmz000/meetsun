package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.service.VideoCustomService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.VideoCustomVo;

@RestController
@RequestMapping("/VideoCustom")
public class VideoCustomController {
	@Autowired
	private VideoCustomService videoCustomService;
	
	@RequestMapping({"/getVideoCustomList"})
	public Result<Object> getVideoCustomList(@RequestBody VideoCustomVo vo) { 
		return videoCustomService.getVideoCustomList(vo); 
	}

	@Log(operation = "新增" , remark="【】")
	@RequestMapping({"/saveVideoCustom"})
	public Result<Object> saveVideoCustom(@RequestBody VideoCustomVo vo,HttpServletRequest request) {
		MsCustom msCustom = (MsCustom)request.getSession().getAttribute("msCustom");
		vo.setCustomSysId(msCustom.getSysId());
		return videoCustomService.saveVideoCustom(vo); 
	}

	@Log(operation = "修改" , remark="【】")
	@RequestMapping({"/updateVideoCustom"})
	public Result<Object> updateVideoCustom(@RequestBody VideoCustomVo vo) { 
		return videoCustomService.updateVideoCustom(vo); 
	}

	@Log(operation = "删除" , remark="【】")
	@RequestMapping({"/deleteVideoCustom"})
	public Result<Object> deleteVideoCustom(@RequestBody VideoCustomVo vo) { 
		return videoCustomService.deleteVideoCustom(vo); 
	}
}
