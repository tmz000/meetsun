package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.MsOrderAddressDao;
import com.meetsun.meetsun.entity.MsOrderAddress;
import com.meetsun.meetsun.service.MsOrderAddressService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsOrderAddressVo;

@Service
public class MsOrderAddressServiceImpl implements MsOrderAddressService{
	
	@Autowired
	private MsOrderAddressDao msOrderAddressDao;
	
	
	@Override
	public Result<Object> getMsOrderAddressList(MsOrderAddressVo vo) {
		List<MsOrderAddress> list = new ArrayList<>();
		MsOrderAddressVo vo1= new MsOrderAddressVo();
		
		vo1.setOpendId(vo.getOpendId());
		if(vo.getSysId() != null && !vo.getSysId().equals("1")) {
			if(vo.getFlag() != null && vo.getFlag().equals("0")) {
				list = msOrderAddressDao.getMsOrderAddressList(vo);
			}else {
				vo1.setIsUse("0");
				msOrderAddressDao.updateMsOrderAddressByOpendId(vo1);
				vo1.setIsUse("1");
				vo1.setSysId(vo.getSysId());
				msOrderAddressDao.updateMsOrderAddressByIsUse(vo1);
				list = msOrderAddressDao.getMsOrderAddressList(vo);
			}
		}else if(vo.getSysId() != null && vo.getSysId().equals("1")){
			vo1.setIsUse("1");
			list = msOrderAddressDao.getMsOrderAddressList(vo1);
		}else {
			list = msOrderAddressDao.getMsOrderAddressList(vo);
		}
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msOrderAddressDao.getMsOrderAddressListTotal(vo));
		return result;
	}
	
	@Override
	public Result<Object> getMsOrderAddressListBySysId(MsOrderAddressVo vo) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<MsOrderAddress> list = msOrderAddressDao.getMsOrderAddressList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveMsOrderAddress(MsOrderAddressVo vo) {
		vo.setSysId(Tools.getUUID());
		String[] addre = vo.getAddress().split(" ");
		if(vo.getName().isEmpty()) {
			return Result.error("收件人不能为空");
		}
		if(vo.getMobile().isEmpty()) {
			return Result.error("手机号不能为空");
		}else if(!Common.phoneRegex(vo.getMobile())){
			return  Result.error("手机号格式不正确！");
		}
		if(addre.length == 0) {
			return Result.error("省市区地址不能为空");
		}
		MsOrderAddressVo mv = new MsOrderAddressVo();
		mv.setOpendId(vo.getOpendId());
		mv.setIsUse("0");
		msOrderAddressDao.updateMsOrderAddressByOpendId(mv);
		int flag = msOrderAddressDao.saveMsOrderAddress(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsOrderAddress(MsOrderAddressVo vo) {
		if(vo.getName().isEmpty()) {
			return Result.error("收件人不能为空");
		}
		if(vo.getMobile().isEmpty()) {
			return Result.error("手机号不能为空");
		}else if(!Common.phoneRegex(vo.getMobile())){
			return  Result.error("手机号格式不正确！");
		}
		if(vo.getAddress().split(" ").length == 0) {
			return Result.error("省市区地址不能为空");
		}
		int flag = msOrderAddressDao.updateMsOrderAddress(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsOrderAddressByIsUse(MsOrderAddressVo vo) {
		int flag = msOrderAddressDao.updateMsOrderAddressByIsUse(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsOrderAddress(MsOrderAddressVo vo) {
		int flag = msOrderAddressDao.deleteMsOrderAddress(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
