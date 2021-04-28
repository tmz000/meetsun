package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.FollowUpDao;
import com.meetsun.meetsun.entity.FollowUp;
import com.meetsun.meetsun.service.FollowUpService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.FollowUpVo;

@Service
public class FollowUpServiceImpl implements FollowUpService{
	
	@Autowired
	private FollowUpDao followUpDao;
	
	@Override
	public Result<Object> getFollowUpList(FollowUpVo vo) {
		List<FollowUp> list = followUpDao.getFollowUpList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(followUpDao.getFollowUpListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveFollowUp(FollowUpVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = followUpDao.saveFollowUp(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateFollowUp(FollowUpVo vo) {
		int flag = followUpDao.updateFollowUp(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteFollowUp(FollowUpVo vo) {
		int flag = followUpDao.deleteFollowUp(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
