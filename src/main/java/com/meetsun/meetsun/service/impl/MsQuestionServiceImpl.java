package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsQuestionDao;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsQuestion;
import com.meetsun.meetsun.service.MsQuestionService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsQuestionVo;

@Service
public class MsQuestionServiceImpl implements MsQuestionService{
	
	@Autowired
	private MsQuestionDao msQuestionDao;
	@Autowired
	private MsCustomDao msCustomDao;
	
	@Override
	public Result<Object> getMsQuestionList(MsQuestionVo vo) {
		List<MsQuestion> list = msQuestionDao.getMsQuestionList(vo);
		List<MsQuestion> list1 = new ArrayList<MsQuestion>();
		for(MsQuestion mq:list) {
			MsCustomVo mvo = new MsCustomVo();
			mvo.setSysId(mq.getCustomId());
			MsCustom mc = msCustomDao.getMsCustomByOpendId(mvo);
			mq.setUserName(mc.getUserName());
			list1.add(mq);
		}
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list1);
		result.setTotal(msQuestionDao.getMsQuestionListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsQuestion(MsQuestionVo vo) {
		vo.setSysId(Tools.getUUID());
		if(vo.getContact() != null && vo.getContact() != "") {
			if(!Common.phoneRegex(vo.getContact())) {
				if(!Common.checkEmail(vo.getContact())) {
					if(!Common.isPhone(vo.getContact())) {
						return Result.error("请填写正确的邮箱或者电话联系方式！");
					}
				}
			}
		}
		int flag = msQuestionDao.saveMsQuestion(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsQuestion(MsQuestionVo vo) {
		int flag = msQuestionDao.updateMsQuestion(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsQuestion(MsQuestionVo vo) {
		int flag = msQuestionDao.deleteMsQuestion(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
