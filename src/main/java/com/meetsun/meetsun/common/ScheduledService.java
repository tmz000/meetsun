package com.meetsun.meetsun.common;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meetsun.meetsun.dao.IncomePayDao;
import com.meetsun.meetsun.dao.LogInfoDao;
import com.meetsun.meetsun.dao.MsAdvertDao;
import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsOrderLogDao;
import com.meetsun.meetsun.dao.PaySureDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.entity.MsAdvert;
import com.meetsun.meetsun.entity.MsOrder;
import com.meetsun.meetsun.entity.PaySure;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.IncomePayVo;
import com.meetsun.meetsun.vo.MsAdvertVo;
import com.meetsun.meetsun.vo.MsOrderLogVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.PaySureVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledService {
	
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private IncomePayDao incomePayDao;
	@Autowired
	private MsAdvertDao msAdvertDao;
	@Autowired
	private LogInfoDao logInfoDao;
	@Autowired
	private MsOrderLogDao msOrderLogDao;
	@Autowired
	private PaySureDao paySureDao;
	
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	
	@Scheduled(cron = "0 30 23 * * ? ") //每天晚上11点半执行
	//@Scheduled(cron = "0 0 0 */1 * ?") //每天凌晨执行
	//@Scheduled(cron = "15 0 0 15 * ? ") //每个月15日凌晨执行
	//@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
		orderScheduled(); 
		advertScheduled();
		delLogScheduled();
	}
	/**
	 * 订单入账定时任务
	 */
	public void orderScheduled() {
		log.info("=====>>>>>入账开始  {}",Tools.get19DateTimes());
		PaySureVo pv =new PaySureVo();
		pv.setIsPayPc("1");
		List<PaySure> pl = paySureDao.getPaySureList(pv);
		if(pl != null && pl.size() > 0 ) {
			for(PaySure ps : pl) {
				MsOrderVo vo = new MsOrderVo();
				vo.setSysId(ps.getOrderId());
				vo.setStatus("0");
				vo.setFlag("1");
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
						int flag = incomePayDao.saveIncomePay(invo);
						if(flag > 0) {
							MsOrderVo vo1 = new MsOrderVo();
							vo1.setSysId(orvo.getSysId());
							vo1.setFlag("0");
							msOrderDao.updateMsOrderByFlag(vo1);
						}
					}
				}
			}
		}else {
			log.info("=====>>>>>暂无入账订单 {}",Tools.get19DateTimes());
		}
		log.info("=====>>>>>入账结束  {}",Tools.get19DateTimes());
	}
	/**
	 * 活动广告定时任务
	 */
	public void advertScheduled(){
		log.info("=====>>>>>检查活动是否到期开始  {}",Tools.get19DateTimes());
		MsAdvertVo vo = new MsAdvertVo();
		vo.setStatus("0");
		List<MsAdvert> advert = msAdvertDao.getMsAdvertList(vo);
		if(advert != null && advert.size() > 0) {
			for(MsAdvert msadt:advert) {
				Integer toDate = Integer.valueOf(msadt.getToDate().replace("-", ""));
				if(toDate < Integer.valueOf(Tools.get10DateTimes().replace("-", ""))) {
					MsAdvertVo vo1 = new MsAdvertVo();
					vo1.setStatus("1");
					vo1.setSysId(msadt.getSysId());
					msAdvertDao.updateMsAdvertStatus(vo1);
					ServiceProjectVo vo2 = new ServiceProjectVo();
					vo2.setIsJoin("0");
					vo2.setSysId(advert.get(0).getSpId());
					serviceProjectDao.updateServiceProjectBySysId(vo2);
				}
			}
		}else {
			log.info("=====>>>>>暂无活动发布 {}",Tools.get19DateTimes());
		}
		log.info("=====>>>>>检查活动是否到期结束  {}",Tools.get19DateTimes());
	}
	public void delLogScheduled() {
		 //系统日志删除定时任务
		log.info("=====>>>>>系统日志删除开始  {}",Tools.get19DateTimes());
		logInfoDao.deleteLogInfoByDay(new LogInfo());
		log.info("=====>>>>>系统日志删除结束  {}",Tools.get19DateTimes());
		
		// 订单日志删除定时任务
		log.info("=====>>>>>订单日志删除开始  {}",Tools.get19DateTimes());
		msOrderLogDao.deleteMsOrderLogByDay(new MsOrderLogVo());
		log.info("=====>>>>>订单日志删除结束  {}",Tools.get19DateTimes());
	}
}
