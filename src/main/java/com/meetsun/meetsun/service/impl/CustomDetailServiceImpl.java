package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.CustomDetailDao;
import com.meetsun.meetsun.entity.CustomDetail;
import com.meetsun.meetsun.service.CustomDetailService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.CustomDetailVo;

@Service
public class CustomDetailServiceImpl implements CustomDetailService{
	
	@Autowired
	private CustomDetailDao customDetailDao;
	
	@Override
	public Result<Object> getCustomDetailList(CustomDetailVo vo) {
		List<CustomDetail> list = customDetailDao.getCustomDetailList(vo);
		return Result.success(list);
	}
	
	@Override
	public Result<Object> getCustomDetailListByMonth(CustomDetailVo vo) {
		List<CustomDetail> list = customDetailDao.getCustomDetailListByMonth(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveCustomDetail(CustomDetailVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = customDetailDao.saveCustomDetail(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateCustomDetail(CustomDetailVo vo) {
		int flag = customDetailDao.updateCustomDetail(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteCustomDetail(CustomDetailVo vo) {
		int flag = customDetailDao.deleteCustomDetail(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
