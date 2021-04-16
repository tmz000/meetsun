package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsOrderLogDao;
import com.meetsun.meetsun.dao.MsOrderWlDao;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.MsOrderWl;
import com.meetsun.meetsun.service.MsOrderWlService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsOrderLogVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsOrderWlVo;

@Service
public class MsOrderWlServiceImpl implements MsOrderWlService{
	
	@Autowired
	private MsOrderWlDao msOrderWlDao;
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private MsOrderLogDao msOrderLogDao;
	
	@Override
	public Result<Object> getMsOrderWlList(MsOrderWlVo vo) {
		List<MsOrderWl> list = msOrderWlDao.getMsOrderWlList(vo);
		List<MsOrderWl> list1 = new ArrayList<MsOrderWl>();
		if(list != null && list.size() > 0) {
			for(MsOrderWl mo:list ) {
				MsOrderVo o = new MsOrderVo();
				o.setSysId(mo.getOrderId());
				List<MsOrder> lio = msOrderDao.getMsOrderList(o);
				for(MsOrder m : lio) {
					mo.setOrderNo(m.getOrderNo());
				}
				list1.add(mo);
			}
		}
		return Result.success(list1);
	}

	@Override
	@Transactional
	public Result<Object> saveMsOrderWl(MsOrderWlVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = msOrderWlDao.saveMsOrderWl(vo);
		MsOrderVo ms = new MsOrderVo();
		ms.setSysId(vo.getOrderId());
		ms.setIsFh("1");
		ms.setOrderNo(vo.getOrderNo());
		saveOrderLog(ms,"订单发货",flag);
		if (flag > 0) {
			msOrderDao.updateMsOrder(ms);
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsOrderWl(MsOrderWlVo vo) {
		int flag = msOrderWlDao.updateMsOrderWl(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsOrderWl(MsOrderWlVo vo) {
		int flag = msOrderWlDao.deleteMsOrderWl(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	public void saveOrderLog(MsOrderVo mo,String content,Integer status) {
		List<MsOrder> list = msOrderDao.getMsOrderList(mo);
		if(list != null && list.size() > 0) {
			for(MsOrder m : list) {
				MsOrderLogVo vo = new MsOrderLogVo();
				vo.setSysId(Tools.getUUID());
				vo.setOrderNo(m.getOrderNo());
				vo.setUserName(m.getUserName());
				vo.setContent(content);
				vo.setStatus(String.valueOf(status));
				msOrderLogDao.saveMsOrderLog(vo);
			}
		}
	}
}
