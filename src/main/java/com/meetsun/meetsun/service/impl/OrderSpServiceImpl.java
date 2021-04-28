package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsProjectTypeDao;
import com.meetsun.meetsun.dao.OrderSpDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.MsProjectType;
import com.meetsun.meetsun.entity.OrderSp;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.OrderSpService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsProjectTypeVo;
import com.meetsun.meetsun.vo.OrderSpVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class OrderSpServiceImpl implements OrderSpService{
	
	@Autowired
	private OrderSpDao orderSpDao;
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private MsProjectTypeDao msProjectTypeDao;
	
	@Override
	public Result<Object> getOrderSpList(OrderSpVo vo) {
		List<OrderSp> listsp = orderSpDao.getOrderSpList(vo);
//		if(listsp == null || listsp.size() == 0) {
//			MsOrderVo mvo = new MsOrderVo();
//			mvo.setSysId(vo.getOrderId());
//			List<MsOrder> mslist = msOrderDao.getMsOrderList(mvo);
//			String[] spId = null;
//			spId = mslist.get(0).getProjectSysId().split(",");
//			if(spId != null && spId.length > 0 ) {
//				for(String sid:spId) {
//					vo.setSysId(Tools.getUUID());
//					vo.setSpId(sid);
//					ServiceProjectVo svo = new ServiceProjectVo();
//					svo.setSysId(sid);
//					List<ServiceProject> splist = serviceProjectDao.getServiceProjectList(svo);
//					for(ServiceProject sp:splist) {
//						MsProjectTypeVo pvo = new MsProjectTypeVo();
//						pvo.setName(sp.getTypeName());
//						List<MsProjectType> plist = msProjectTypeDao.getMsProjectTypeList(pvo);
//						for(MsProjectType pt : plist) {
//							vo.setType(pt.getType());
//						}
//					}
//					orderSpDao.saveOrderSp(vo);
//				}
//				OrderSpVo vo1 = new OrderSpVo();
//				vo1.setOrderId(vo.getOrderId());
//				listsp = orderSpDao.getOrderSpList(vo1);
//			}
//		}
		List<OrderSp> list1 = new ArrayList<OrderSp>();
		for(OrderSp sp : listsp) {
			MsOrderVo mvo = new MsOrderVo();
			mvo.setSysId(sp.getOrderId());
			List<MsOrder> mslist = msOrderDao.getMsOrderList(mvo);
			sp.setOrderNo(mslist.get(0).getOrderNo());
			ServiceProjectVo svo = new ServiceProjectVo();
			svo.setSysId(sp.getSpId());
			List<ServiceProject> splist = serviceProjectDao.getServiceProjectList(svo);
			sp.setServiceProject(splist.get(0));
			list1.add(sp);
		}
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list1);
		result.setTotal(orderSpDao.getOrderSpListTotal(vo));
		return result;
		
	}

	@Override
	public Result<Object> saveOrderSp(OrderSpVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = orderSpDao.saveOrderSp(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateOrderSp(OrderSpVo vo) {
		int flag = orderSpDao.updateOrderSp(vo);
     	if (flag > 0) {
     		List<OrderSp> listsp = orderSpDao.getOrderSpList(vo);
     		for(OrderSp sp : listsp) {
 				MsOrderVo mvo = new MsOrderVo();
 				for(OrderSp os : listsp) {
 					if(os.getType().equals("0")) {
 						mvo.setIsShouhuo("1");
 					}else {
 						mvo.setIsUse("1");
 					}
 					mvo.setSysId(os.getOrderId());
 				}
 				msOrderDao.updateMsOrder(mvo);
     		}
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteOrderSp(OrderSpVo vo) {
		int flag = orderSpDao.deleteOrderSp(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
