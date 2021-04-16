package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.VipCardService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.VipCardVo;

@RestController
@RequestMapping("/vipCard")
public class VipCardController {
	@Autowired
	private VipCardService vipCardService;
	@Autowired
	private MsUserDao msUserDao;
	
	@RequestMapping({"/getVipCardList"})
	public Result<Object> getVipCardList(@RequestBody VipCardVo vo,HttpServletRequest request) { 
		return vipCardService.getVipCardList(vo); 
	}

	@Log(operation = "新增" , remark="【会员卡】新增会员卡信息" , type = "0")
	@RequestMapping({"/saveVipCard"})
	public Result<Object> saveVipCard(@RequestBody VipCardVo vo,HttpServletRequest request) {
		String token = Common.getParam(request.getQueryString(),"token");
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser msUser = msUserDao.getMsUser(mvo).get(0);
		vo.setUserName(msUser.getRealName());
		return vipCardService.saveVipCard(vo); 
	}

	@Log(operation = "修改" , remark="【会员卡】修改会员卡信息" , type = "0")
	@RequestMapping({"/updateVipCard"})
	public Result<Object> updateVipCard(@RequestBody VipCardVo vo) { 
		return vipCardService.updateVipCard(vo); 
	}

	@Log(operation = "删除" , remark="【会员卡】删除会员卡信息" , type = "0")
	@RequestMapping({"/deleteVipCard"})
	public Result<Object> deleteVipCard(@RequestBody VipCardVo vo) { 
		return vipCardService.deleteVipCard(vo); 
	}
}
