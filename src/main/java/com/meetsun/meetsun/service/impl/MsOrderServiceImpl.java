package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetsun.meetsun.dao.IncomePayDao;
import com.meetsun.meetsun.dao.MsCartDao;
import com.meetsun.meetsun.dao.MsCommentDao;
import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsOrderAddressDao;
import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsOrderLogDao;
import com.meetsun.meetsun.dao.MsProjectTypeDao;
import com.meetsun.meetsun.dao.OrderSpDao;
import com.meetsun.meetsun.dao.PaySureDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.MsOrderAddress;
import com.meetsun.meetsun.entity.MsProjectType;
import com.meetsun.meetsun.entity.PaySure;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.MsOrderService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.IncomePayVo;
import com.meetsun.meetsun.vo.MsCartVo;
import com.meetsun.meetsun.vo.MsCommentVo;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsOrderAddressVo;
import com.meetsun.meetsun.vo.MsOrderLogVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsProjectTypeVo;
import com.meetsun.meetsun.vo.OrderSpVo;
import com.meetsun.meetsun.vo.PaySureVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class MsOrderServiceImpl implements MsOrderService{
	
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private MsOrderLogDao msOrderLogDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private MsProjectTypeDao msProjectTypeDao;
	@Autowired
	private MsCartDao msCartDao;
	@Autowired
	private MsCommentDao msCommentDao;
	@Autowired
	private MsCustomDao msCustomDao;
	@Autowired
	private MsOrderAddressDao msOrderAddressDao;
	@Autowired
	private IncomePayDao incomePayDao;
	@Autowired
	private PaySureDao paySureDao;
	@Autowired
	private OrderSpDao orderSpDao;
	
	@Override
	public Result<Object> getMsOrderList(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		List<MsOrder> list1 = new ArrayList<MsOrder>();
		if(list != null && list.size() > 0) {
			for(MsOrder o : list) {
				MsOrderAddressVo mavo = new MsOrderAddressVo();
				if(o.getAddressId() != null && o.getAddressId() != "") {
					mavo.setSysId(o.getAddressId());
					List<MsOrderAddress> maLi = msOrderAddressDao.getMsOrderAddressList(mavo);
					if(maLi != null && maLi.size() > 0) {
						for(MsOrderAddress a : maLi) {
							o.setName(a.getName());
							o.setAddress(a.getAddress());
							o.setMobile(a.getMobile());
						}
					}
				}
				PaySureVo pv = new PaySureVo();
				pv.setOrderId(o.getSysId());
				List<PaySure> lp = paySureDao.getPaySureList(pv);
				if(lp != null && lp.size() > 0) {
					o.setIsPayPc(lp.get(0).getIsPayPc());
				}
				list1.add(o);
			}
		}
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msOrderDao.getMsOrderListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsOrder(MsOrderVo vo) {
		String orderNo = "DD"+Tools.getMMddHHmmssSSS();
		vo.setSysId(Tools.getUUID());
		vo.setOrderNo(orderNo);
		ServiceProjectVo spvo = new ServiceProjectVo();
		spvo.setSysId(vo.getSpId());
		ServiceProject sp = serviceProjectDao.getServiceProjectList(spvo).get(0);
		MsProjectTypeVo mspvo = new MsProjectTypeVo();
		mspvo.setName(sp.getTypeName());
		MsProjectType msp = msProjectTypeDao.getMsProjectTypeList(mspvo).get(0);
		vo.setIsSend(msp.getType());
		vo.setSpName(sp.getName());
		int flag = msOrderDao.saveMsOrder(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsOrder(MsOrderVo vo) {
		MsOrderVo mv = new MsOrderVo();
		mv.setSysId(vo.getSysId());
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		if(list != null && list.size() > 0) {
			MsCustomVo mcvo = new MsCustomVo();
			mcvo.setUserName(list.get(0).getUserName());
			List<MsCustom> lmc = msCustomDao.getMsCustomList(mcvo);
			if(lmc.get(0).getIntegral() != null && Integer.valueOf(lmc.get(0).getIntegral()) > 0) {
				return Result.error("该用户有可用积分，不予修改实际收取金额！");
			}
		}
		if(vo.getTrueMoney().equals(list.get(0).getTrueMoney())) {
			return Result.error("没有任何信息更改！");
		}
		vo.setTrueMoney(String.valueOf(Double.valueOf(vo.getTrueMoney())));
		int flag = msOrderDao.updateMsOrder(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsOrderByIsUse(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		int flag = 0;
		if(list != null && list.size() > 0) {
			vo.setIsUse("1");
			for(MsOrder od : list) {
				MsCartVo cvo = new MsCartVo();
				cvo.setSysId(od.getCartSysId());
				//msCartDao.deleteMsCart(cvo);
				if(od.getIsSend().equals("2")) {
					vo.setIsSend("0");
				}
				flag = msOrderDao.updateMsOrderByIsUse(vo);
			}
		}
		saveOrderLog(vo,"订单使用",flag);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsOrderByIsDel(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		int flag = 0;
		if(list != null && list.size() > 0) {
			for(MsOrder od : list) {
				if(od.getStatus().equals("1")) {
					MsCartVo cvo = new MsCartVo();
					cvo.setSysId(od.getCartSysId());
					msCartDao.deleteMsCart(cvo);
					flag = msOrderDao.deleteMsOrder(vo);
					if(flag > 0) {
						OrderSpVo spvo = new OrderSpVo();
						spvo.setOrderId(od.getSysId());
						orderSpDao.deleteOrderSp(spvo);
					}
				}else {
					flag = msOrderDao.updateMsOrderByIsDel(vo);
				}
			}
		}
		saveOrderLog(vo,"订单APP端删除",flag);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsOrderByIsShouhuo(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		int flag = 0;
		if(list != null && list.size() > 0) {
			for(MsOrder od : list) {
				MsCartVo cvo = new MsCartVo();
				cvo.setSysId(od.getCartSysId());
				//msCartDao.deleteMsCart(cvo);
				if(od.getIsSend().equals("2")) {
					vo.setIsSend("1");
				}
				flag = msOrderDao.updateMsOrderByIsShouhuo(vo);
			}
		}
		saveOrderLog(vo,"订单收货",flag);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	@Override
	public Result<Object> updateMsOrderByStatus(MsOrderVo vo) {
		if(vo.getPayType().equals("0")) {
			if(vo.getTrueMoney() != null && vo.getTrueMoney() != "" && vo.getIntegral() != null && vo.getIntegral() != "") {
				if(Double.valueOf(vo.getTrueMoney())-Double.valueOf(vo.getIntegral()) > 0) {
					Double trueMoney = Double.valueOf(vo.getTrueMoney())-Double.valueOf(vo.getIntegral());
					String freeMoney = String.valueOf(Math.round((10-Double.valueOf(vo.getDiscount())) * trueMoney/10));
					vo.setFreeMoney(freeMoney);
					vo.setDiscount(vo.getDiscount());
				}else {
					vo.setFreeMoney("0");
					vo.setDiscount("0");
				}
			}
		}else {
			vo.setFreeMoney("0");
			vo.setDiscount("0");
		}
		int flag = msOrderDao.updateMsOrderByStatus(vo);
		saveOrderLog(vo,"订单支付",flag);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	@Override
	@Transactional
	public Result<Object> updateMsOrderByFlag(MsOrderVo vo) {
		int flag = msOrderDao.updateMsOrderByFlag(vo);
		if (flag > 0) {
			List<MsOrder> order = msOrderDao.getMsOrderList(vo);
			if(order != null && order.size() > 0) {
				for(MsOrder orvo:order) {
					IncomePayVo invo = new IncomePayVo();
					invo.setAmount(orvo.getAmount());
					invo.setDiscount(orvo.getDiscount());
					invo.setPrice(orvo.getPrice());
					invo.setSpName(orvo.getSpName());
					if(orvo.getPayType().equals("0")) {
						invo.setTrueMoney(String.valueOf(Double.valueOf(orvo.getTrueMoney())));
					}else {
						invo.setTrueMoney(String.valueOf(Double.valueOf(orvo.getPayMoney())));
					}
					invo.setFreeMoney(orvo.getFreeMoney());
					invo.setUserName(orvo.getUserName());
					invo.setPrice(orvo.getPrice());
					invo.setSysId(Tools.getUUID());
					invo.setType("0");
					invo.setStatus(orvo.getStatus());
					invo.setInputType("0");
					invo.setPayType(orvo.getPayType());
					incomePayDao.saveIncomePay(invo);
				}
			}
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	@Override
	public Result<Object> updateMsOrderByIsPl(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderList(vo);
		int flag = msOrderDao.updateMsOrderByIsPl(vo);
		saveOrderLog(vo,"订单评论",flag);
		String projectSysId = null;
		if (flag > 0) {
			if(list != null && list.size() > 0) {
				for(MsOrder od:list) {
					projectSysId = od.getProjectSysId();
				}
			}
			if(projectSysId != null) {
				String[] sd = projectSysId.split(",");
				for(String spSysId:sd) {
					MsCommentVo mc = new MsCommentVo();
					mc.setSysId(Tools.getUUID());
					mc.setProjectSysId(spSysId);
					mc.setUserName(vo.getUserName());
					mc.setContent(vo.getContent());
					mc.setStarLv(vo.getStarLv());
					if(vo.getUserTx() == null || vo.getUserTx() == "") {
						mc.setUserTx("img/logo.jpg");
					}else {
						mc.setUserTx(vo.getUserTx());
					}
					if(Integer.valueOf(vo.getStarLv()) >3) {
						mc.setToExamine("1");
					}else {
						mc.setToExamine("0");
					}
					mc.setCustomSysId(vo.getCustomSysId());
					msCommentDao.saveMsComment(mc);
				}
			}
			return Result.success("success");
		}
		return Result.error("error");
	}
	
	@Override
	public Result<Object> deleteMsOrder(MsOrderVo vo) {
		int flag = msOrderDao.deleteMsOrder(vo);
		saveOrderLog(vo,"订单删除",flag);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> getMsOrderListByMonth(MsOrderVo vo) {
		List<MsOrder> list = msOrderDao.getMsOrderListByMonth(vo);
		List<MsOrder> list1 = new ArrayList<MsOrder>();
		for(MsOrder ms:list) {
			if(ms.getSum() != null) {
				list1.add(ms);
			}else {
				ms.setSum("0");
				list1.add(ms);
			}
		}
		return Result.success(list1);
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
