package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.MsCartDao;
import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsProjectTypeDao;
import com.meetsun.meetsun.dao.OrderSpDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsCart;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.MsProjectType;
import com.meetsun.meetsun.entity.OrderSp;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.MsCartService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsCartVo;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsProjectTypeVo;
import com.meetsun.meetsun.vo.OrderSpVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class MsCartServiceImpl implements MsCartService{
	
	@Autowired
	private MsCartDao msCartDao;
	@Autowired
	private MsCustomDao msCustomDao;
	@Autowired
	private ServiceProjectDao serviceprojectDao;
	@Autowired
	private MsProjectTypeDao msProjectTypeDao;
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private OrderSpDao orderSpDao;
	
	@Override
	public Result<Object> getMsCartList(MsCartVo vo) {
		List<MsCart> list = new ArrayList<>();
		if(vo.getSysId() == null) {
			for(String sysId:vo.getCheckSysId()) {
				vo.setSysId(sysId);
				List<MsCart> listmc = msCartDao.getMsCartList(vo);
				if(listmc != null && listmc.size() > 0) {
					MsCart mscart = listmc.get(0);
					ServiceProjectVo spvo = new ServiceProjectVo();
					spvo.setSysId(mscart.getProjectSysId());
					ServiceProject sp = serviceprojectDao.getServiceProjectList(spvo).get(0);
					MsProjectTypeVo mspvo = new MsProjectTypeVo();
					mspvo.setName(sp.getTypeName());
					MsProjectType msp = msProjectTypeDao.getMsProjectTypeList(mspvo).get(0);
					mscart.setType(msp.getType());
					mscart.setTypeName(msp.getName());
					list.add(mscart);
				}else {
					return Result.error(null);
				}
			}
		}else{
			list = msCartDao.getMsCartList(vo);
		}
		return Result.success(list);
	}
	@Override
	public Result<Object> getMsCartListBySpId(MsCartVo vo) {
		List<MsCart> list = new ArrayList<>();
		MsOrderVo ovo = new MsOrderVo();
		ovo.setSysId(vo.getOrderId());
		List<MsOrder> lio = msOrderDao.getMsOrderList(ovo);
		if(lio != null && lio.size() > 0) {
			for(String sysId:lio.get(0).getCartSysId().split(",")) {
				vo.setSysId(sysId);
				List<MsCart> listmc = msCartDao.getMsCartListBySpId(vo);
				if(listmc != null && listmc.size() > 0) {
					for(MsCart mscart : listmc) {
						OrderSpVo spvo = new OrderSpVo();
						spvo.setOrderId(vo.getOrderId());
						spvo.setSpId(mscart.getProjectSysId());
						for(OrderSp sp : orderSpDao.getOrderSpList(spvo)) {
							mscart.setOrderSp(sp);
						}
						list.add(mscart);
					}
				}else {
					return Result.error("暂无信息！");
				}
			}
		}
		return Result.success(list);
	}
	@Override
	public Result<Object> getMsCartListByOpendId(MsCartVo vo) {
		List<MsCart> list = msCartDao.getMsCartListByOpendId(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveMsCart(MsCartVo vo) {
		MsCustomVo vo1 = new MsCustomVo();
		vo1.setOpendId(vo.getOpendId());
		MsCustom custom = msCustomDao.getMsCustomByOpendId(vo1);
		ServiceProjectVo vo2 = new ServiceProjectVo();
		vo2.setSysId(vo.getProjectSysId());
		ServiceProject listService = serviceprojectDao.getServiceProjectList(vo2).get(0);
		MsProjectTypeVo mspvo = new MsProjectTypeVo();
		mspvo.setName(listService.getTypeName());
		MsProjectType msp = msProjectTypeDao.getMsProjectTypeList(mspvo).get(0);
		vo.setType(msp.getType());
		vo.setTypeName(msp.getName());
		vo.setSysId(Tools.getUUID());
		vo.setCustomName(custom.getUserName());
		vo.setSpName(listService.getName());
		if(vo.getCount() == null) {
			vo.setCount("1");
		}
		if(listService.getIsJoin().equals("1")) {
			vo.setPrice(listService.getAmount());
			vo.setAmount(String.valueOf(Integer.valueOf(vo.getCount())*Double.valueOf(vo.getPrice())));
		}else {
			vo.setPrice(listService.getPrice());
			vo.setAmount(String.valueOf(Integer.valueOf(vo.getCount())*Double.valueOf(vo.getPrice())));
		}
		vo.setHandPhoto(listService.getHandPhoto().split(",")[0]);
		vo.setProjectSysId(listService.getSysId());
		int flag = 0;
		//判断该用户购物车是否已经有未生成订单的此类商品，如果有数量和价格追加
		List<MsCart> listCart = msCartDao.getMsCartListByOpendId(vo);
		boolean isTrue = true;
		if(listCart != null && listCart.size() > 0) {
			for(MsCart cart:listCart) {
				if(cart.getProjectSysId().equals(listService.getSysId())) {
					vo.setCount(String.valueOf(Integer.valueOf(cart.getCount())+Integer.valueOf(vo.getCount())));
					vo.setAmount(String.valueOf(Double.valueOf(cart.getPrice())*(Integer.valueOf(vo.getCount()))));
					vo.setSysId(cart.getSysId());
					isTrue = false;
					break;
				}else{
					isTrue = true;
				}
			}
			if(isTrue) {
				flag = msCartDao.saveMsCart(vo);
			}else {
				flag = msCartDao.updateMsCart(vo);
			}
		}else {
			flag = msCartDao.saveMsCart(vo);
		}
		if (flag > 0) {
			return Result.success(vo.getSysId());
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsCart(MsCartVo vo) {
		MsCart cart = msCartDao.getMsCartListByOpendId(vo).get(0);
		if(vo.getType().equals("decrease")) {
			vo.setCount(String.valueOf(Integer.valueOf(cart.getCount())-1));
			vo.setAmount(String.valueOf(Double.valueOf(cart.getPrice())*(Integer.valueOf(cart.getCount())-1)));
		}else {
			vo.setCount(String.valueOf(Integer.valueOf(cart.getCount())+1));
			vo.setAmount(String.valueOf(Double.valueOf(cart.getPrice())*(Integer.valueOf(cart.getCount())+1)));
		}
		vo.setPrice(cart.getPrice());
		int flag = msCartDao.updateMsCart(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsCartByStatus(MsCartVo vo) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<String> strli = new ArrayList<String>();
		List<String> strli1 = new ArrayList<String>();
		strli1.add("1");strli1.add("0");
		String isSend=null;
		for(String sysId:vo.getCheckSysId()) {
			vo.setSysId(sysId);
			MsCart mscart = msCartDao.getMsCartList(vo).get(0);
			strli.add(mscart.getType());
			isSend = mscart.getType();
		}
		if(strli.containsAll(strli1)) {
			isSend = "2";
		}
		String cartSysId = null;
		for(String sysId:vo.getCheckSysId()) {
			vo.setSysId(sysId);
			MsCart mscart = msCartDao.getMsCartList(vo).get(0);
			ServiceProjectVo spvo = new ServiceProjectVo();
			spvo.setSysId(mscart.getProjectSysId());
			ServiceProject sp = serviceprojectDao.getServiceProjectList(spvo).get(0);
			MsProjectTypeVo mspvo = new MsProjectTypeVo();
			mspvo.setName(sp.getTypeName());
			MsProjectType msp = msProjectTypeDao.getMsProjectTypeList(mspvo).get(0);
			mscart.setType(msp.getType());
			msCartDao.updateMsCartByStatus(vo);
			if(cartSysId == null) {
				cartSysId = sysId;
			}else {
				cartSysId = cartSysId+","+sysId;
			}
		}
		MsCustomVo mc = new MsCustomVo();
		String opendId =  Common.getParam(request.getQueryString(),"opendId");
		mc.setOpendId(opendId);
		MsCustom custom = msCustomDao.getMsCustomByOpendId(mc);
		MsOrderVo ovo = new MsOrderVo();
		ovo.setOpendId(custom.getOpendId());
		String str = "";
		for (String string :vo.getProjectSys()){
			if(str == "") {
				str = string;
			}else {
				str=str+","+string;
			}
		}
		ovo.setProjectSysId(str);
		ovo.setOrderNo("DD"+Tools.getMMddHHmmssSSS());
		ovo.setSysId(Tools.getUUID());
		ovo.setCount(vo.getSumCount());
		ovo.setAmount(vo.getSumMoney());
		ovo.setTrueMoney(vo.getSumMoney());
		ovo.setUserName(custom.getUserName());
		ovo.setRemark(vo.getRemark());
		ovo.setType("0");
		ovo.setAddressId(vo.getAddressId());
		ovo.setStatus("1");
		ovo.setHandPhoto(Common.handPhoto);
		ovo.setIsSend(isSend);
		ovo.setCartSysId(cartSysId);
		int flag = msOrderDao.saveMsOrder(ovo);
		if (flag > 0) {
			for(String sid:vo.getProjectSys()) {
				OrderSpVo spvo = new OrderSpVo();
				spvo.setSysId(Tools.getUUID());
				spvo.setSpId(sid);
				spvo.setOrderId(ovo.getSysId());
				ServiceProjectVo svo = new ServiceProjectVo();
				svo.setSysId(sid);
				List<ServiceProject> splist = serviceprojectDao.getServiceProjectList(svo);
				for(ServiceProject sp:splist) {
					MsProjectTypeVo pvo = new MsProjectTypeVo();
					pvo.setName(sp.getTypeName());
					List<MsProjectType> plist = msProjectTypeDao.getMsProjectTypeList(pvo);
					for(MsProjectType pt : plist) {
						spvo.setType(pt.getType());
					}
				}
				orderSpDao.saveOrderSp(spvo);
			}
			return Result.success(ovo.getSysId());
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsCart(MsCartVo vo) {
		int flag = msCartDao.deleteMsCart(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
