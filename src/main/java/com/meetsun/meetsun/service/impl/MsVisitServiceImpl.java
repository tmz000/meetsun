package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsVisitDao;
import com.meetsun.meetsun.entity.MsVisit;
import com.meetsun.meetsun.service.MsVisitService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsVisitVo;

@Service
public class MsVisitServiceImpl implements MsVisitService{
	
	@Autowired
	private MsVisitDao msVisitDao;
	
	@Override
	public Result<Object> getMsVisitList(MsVisitVo vo) {
		List<MsVisit> list = msVisitDao.getMsVisitList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveMsVisit(MsVisitVo vo) {
		List<MsVisit> list = msVisitDao.getMsVisitList(vo);
		if(!Common.phoneRegex(vo.getPhone())) {
			return Result.error("手机号码格式不正确！");
		}
		if(list != null && list.size() > 0) {
			return Result.error("用户手机【"+vo.getPhone()+"】已存在！");
		}
		vo.setSysId(Tools.getUUID());
		vo.setVisitNo(Tools.getMMddHHmmssSSS());
		int flag = msVisitDao.saveMsVisit(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsVisit(MsVisitVo vo) {
		int flag = msVisitDao.updateMsVisit(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsVisit(MsVisitVo vo) {
		int flag = msVisitDao.deleteMsVisit(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
