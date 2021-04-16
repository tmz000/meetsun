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
import com.meetsun.meetsun.service.PayPictureService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.PayPictureVo;

@RestController
@RequestMapping("/payPicture")
public class PayPictureController {
	@Autowired
	private PayPictureService payPictureService;
	
	@RequestMapping({"/getPayPictureList"})
	public Result<Object> getPayPictureList(@RequestBody PayPictureVo vo,HttpServletRequest request) { 
		return payPictureService.getPayPictureList(vo); 
	}
	
	@Log(operation = "新增" , remark="【支付图片】新增支付图片" , type = "0")
	@RequestMapping({"/savePayPicture"})
	public Result<Object> savePayPicture(@RequestParam("file") MultipartFile file,@RequestParam("vo") String vo1,HttpServletRequest request) {
		JSON json = JSONObject.parseObject(vo1);
		PayPictureVo vo = JSONObject.toJavaObject(json,PayPictureVo.class);
		vo.setFile(file);
//		String token = Common.getParam(request.getQueryString(),"token");
//		MsUserVo mvo =new MsUserVo();
//		mvo.setSysId(token);
//		MsUser us = msUserDao.getMsUser(mvo).get(0);
//		vo.setUserName(us.getRealName());
//		vo.setFile(file);
//		vo.setRealPath((String)request.getSession().getServletContext().getRealPath("/"));
		return payPictureService.savePayPicture(vo); 
	}
   
	@Log(operation = "修改" , remark="【支付图片】修改支付图片" , type = "0")
	@RequestMapping({"/updatePayPicture"})
	public Result<Object> updatePayPicture(@RequestBody PayPictureVo vo) { 
		return payPictureService.updatePayPicture(vo); 
	}
   
	@Log(operation = "删除" , remark="【支付图片】删除支付图片" , type = "0")
	@RequestMapping({"/deletePayPicture"})
	public Result<Object> deletePayPicture(@RequestBody PayPictureVo vo) { 
		return payPictureService.deletePayPicture(vo); 
	}
}
