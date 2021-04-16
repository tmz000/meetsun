package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsCommentDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsComment;
import com.meetsun.meetsun.service.MsCommentService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsCommentVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class MsCommentServiceImpl implements MsCommentService{
	
	@Autowired
	private MsCommentDao msCommentDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	
	@Override
	public Result<Object> getMsCommentList(MsCommentVo vo) {
		List<MsComment> list = msCommentDao.getMsCommentList(vo);
		List<MsComment> list1 = new ArrayList<MsComment>();
		if(list != null && list.size() > 0) {
			for(MsComment ms : list) {
				ServiceProjectVo sv = new ServiceProjectVo();
				sv.setSysId(ms.getProjectSysId());
				ms.setSpName(serviceProjectDao.getServiceProjectList(sv).get(0).getName());
				list1.add(ms);
			}
		}
		return Result.success(list1);
	}
	
	@Override
	public Result<Object> getMsCommentListByStarLv(MsCommentVo vo) {
		List<MsComment> list = msCommentDao.getMsCommentListByStarLv(vo);
		return Result.success(list);
	}
	
	@Override
	public Result<Object> saveMsComment(MsCommentVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = msCommentDao.saveMsComment(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsComment(MsCommentVo vo) {
		int flag = msCommentDao.updateMsComment(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsComment(MsCommentVo vo) {
		int flag = msCommentDao.deleteMsComment(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
