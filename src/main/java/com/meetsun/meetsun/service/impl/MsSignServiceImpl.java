package com.meetsun.meetsun.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsSignDao;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsSign;
import com.meetsun.meetsun.service.MsSignService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsSignVo;

@Service
public class MsSignServiceImpl implements MsSignService{
	
	@Autowired
	private MsSignDao msSignDao;
	@Autowired
	private MsCustomDao msCustomDao;
	
	@Override
	public Result<Object> getMsSignList(MsSignVo vo) {
		List<MsSign> list = msSignDao.getMsSignList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msSignDao.getMsSignListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsSign(MsSignVo vo) {
		List<MsSign> list = msSignDao.getMsSignList(vo);
		vo.setSysId(Tools.getUUID());
		vo.setSignDate(String.valueOf(Tools.getCalendar(Tools.get10DateTimes() + " 00:00:00")));
		if(list != null && list.size() > 0) {
			String d1 = list.get(0).getCreateTime().split(" ")[0] + " 00:00:00";
			String d2 = Tools.get10DateTimes().split(" ")[0] + " 00:00:00";
			Integer d3 = 0;
			try {
				d3 = Tools.differentDaysByMillisecond(Tools.getDate(d1), Tools.getDate(d2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(d3 != 1) {
				vo.setContinueSign("1");
				vo.setIntegral("1");
				if(d3 > 6) {
					vo.setJfCount("1");
				}else {
					vo.setJfCount(String.valueOf(Integer.valueOf(list.get(0).getJfCount())+1));
				}
			}else {
				vo.setContinueSign(String.valueOf(Integer.valueOf(list.get(0).getContinueSign())+1));
				vo.setJfCount(String.valueOf(Integer.valueOf(list.get(0).getJfCount())+1));
				vo.setIntegral("1");
				//连续签到7天，第7天多+5分
				if(Integer.valueOf(vo.getContinueSign())%7 == 0) {
					vo.setJfCount(String.valueOf(Integer.valueOf(vo.getJfCount())+5));
					vo.setIntegral("6");
				}
			}
			vo.setCount(String.valueOf(Integer.valueOf(list.get(0).getCount())+1));
		}else {
			vo.setContinueSign("1");
			vo.setCount("1");
			vo.setJfCount("1");
		}
		int flag = msSignDao.saveMsSign(vo);
		if (flag > 0) {
			MsCustomVo mvo = new MsCustomVo();
			mvo.setSysId(vo.getUserId());
			MsCustom ms = msCustomDao.getMsCustomByOpendId(mvo);
			mvo.setIntegral(String.valueOf(Integer.valueOf(ms.getIntegral())+Integer.valueOf(vo.getIntegral())));
			msCustomDao.updateMsCustomBySysId(mvo);
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsSign(MsSignVo vo) {
		int flag = msSignDao.updateMsSign(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsSign(MsSignVo vo) {
		int flag = msSignDao.deleteMsSign(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
