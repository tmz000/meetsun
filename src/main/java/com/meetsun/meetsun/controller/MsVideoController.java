package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsVideoService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.MsVideoVo;

@RestController
@RequestMapping("/msVideo")
public class MsVideoController {
	@Autowired
	private MsVideoService msVideoService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getMsVideoList"})
	public Result<Object> getMsVideoList(@RequestBody MsVideoVo vo,HttpServletRequest request) { 
		return msVideoService.getMsVideoList(vo); 
	}

	@Log(operation = "新增" , remark="【视频】新增视频信息" , type = "0")
	@RequestMapping({"/saveMsVideo"})
	public Result<Object> saveMsVideo(@RequestParam("file") MultipartFile file,@RequestParam("vo") String vo1,HttpServletRequest request) {
		JSON json = JSONObject.parseObject(vo1);
		MsVideoVo vo = JSONObject.toJavaObject(json,MsVideoVo.class);
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(msUser.getRealName());
		vo.setFile(file);
		return msVideoService.saveMsVideo(vo); 
	}

	@Log(operation = "修改" , remark="【视频】修改视频信息" , type = "0")
	@RequestMapping({"/updateMsVideo"})
	public Result<Object> updateMsVideo(@RequestBody MsVideoVo vo) { 
		return msVideoService.updateMsVideo(vo); 
	}

	@Log(operation = "删除" , remark="【视频】删除视频信息" , type = "0")
	@RequestMapping({"/deleteMsVideo"})
	public Result<Object> deleteMsVideo(@RequestBody MsVideoVo vo) { 
		return msVideoService.deleteMsVideo(vo); 
	}
}
