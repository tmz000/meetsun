package com.meetsun.meetsun.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.CustomDetailDao;
import com.meetsun.meetsun.dao.IncomePayDao;
import com.meetsun.meetsun.dao.MsCommentDao;
import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsOrderDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.dao.VipCardDao;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.entity.VipCard;
import com.meetsun.meetsun.service.MsCustomService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.EnandeDecrypt;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.CustomDetailVo;
import com.meetsun.meetsun.vo.IncomePayVo;
import com.meetsun.meetsun.vo.MsCommentVo;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;
import com.meetsun.meetsun.vo.VipCardVo;

@Service
public class MsCustomServiceImpl implements MsCustomService{
	
	@Autowired
	private MsCustomDao msCustomDao;
	@Autowired
	private CustomDetailDao customDetailDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private MsOrderDao msOrderDao;
	@Autowired
	private MsCommentDao msCommentDao;
	@Autowired
	private MsUserDao msUserDao;
	@Autowired
	private VipCardDao vipCardDao;
	@Autowired
	private IncomePayDao incomePayDao;
	
	@Override
	public Result<Object> getMsCustomList(MsCustomVo vo) {
		List<MsCustom> list = msCustomDao.getMsCustomList(vo);
		List<MsCustom> list1 = new ArrayList<MsCustom>();
		try {
			if(list != null && list.size() > 0) {
				for(MsCustom ms:list) {
					String passWord = new String(EnandeDecrypt.decryptBASE64(ms.getPassWord()));
					ms.setPassWord(passWord);
					list1.add(ms);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(list1);
	}
	@Override
	public Result<Object> getMsCustomByName(MsCustomVo vo) {
		try {
			String opendId = vo.getOpendId();
			if(vo.getUserName() == null || vo.getUserName() == "") {
				return Result.error("请输入手机号或用户名！");
			}
			if(vo.getPassWord() == null || vo.getPassWord() == "") {
				return Result.error("请输入密码！");
			}
			MsCustomVo vo1 = new MsCustomVo();
			vo1.setUserName(vo.getUserName());
			List<MsCustom> list = msCustomDao.getMsCustomList(vo1);
			if(list != null && list.size() > 0) {
				if(!EnandeDecrypt.encryptBASE64(vo.getPassWord().getBytes()).equals(list.get(0).getPassWord())) {
					return Result.error("密码错误，请重试！");
				}else if(!vo.getUserName().equals(list.get(0).getUserName())){
					return Result.error("用户暂未注册，请注册！");
				}else {
					vo.setPassWord(EnandeDecrypt.encryptBASE64(vo.getPassWord().getBytes()));
					if(list.get(0).getOpendId() == null || !list.get(0).getOpendId().equals(opendId)) {
						MsCustomVo vo2 = new MsCustomVo();
						vo2.setOpendId(opendId);
						vo2.setSysId(list.get(0).getSysId());
						msCustomDao.updateMsCustomByOpendId(vo2);
					}
					return Result.success(list);
				}
			}else {
				return Result.error("该用户暂未注册，请注册！");
			}
		}catch (Exception e) {
			return Result.error("系统异常，请联系管理员！");
		}
	}

	@Override
	public Result<Object> saveMsCustom(MsCustomVo vo) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			vo.setSysId(Tools.getUUID());
			List<MsCustom> list = null;
			List<MsCustom> list1 = null;
			List<MsCustom> list2 = null;
			if(vo.getUserName() != null && vo.getUserName() != "") {
				MsCustomVo msVo = new MsCustomVo();
				msVo.setUserName(vo.getUserName());
				list =  msCustomDao.getMsCustomList(msVo);
			}
			if(vo.getCardNo() != null && vo.getCardNo() != "") {
				MsCustomVo msVo1 = new MsCustomVo();
				msVo1.setCardNo(vo.getCardNo());
				list1 = msCustomDao.getMsCustomList(msVo1);
			}
			if(vo.getIdCard() != null && vo.getIdCard() != "") {
				MsCustomVo msVo2 = new MsCustomVo();
				msVo2.setIdCard(vo.getIdCard());
				list2 =  msCustomDao.getMsCustomList(msVo2);
			}
			if(vo.getPassWord() == null) {
				vo.setPassWord(EnandeDecrypt.encryptBASE64(vo.getUserName().getBytes()));
			}else {
				vo.setPassWord(EnandeDecrypt.encryptBASE64(vo.getPassWord().getBytes()));
			}
			if(list != null && list.size() > 0) {
				return Result.error("手机号或用户名已注册！");
			}if(list1 != null && list1.size() > 0) {
				return Result.error("会员卡号已经被绑定！");
			}if(list2 != null && list2.size() > 0) {
				return Result.error("身份证号已经被绑定！");
			}else {
				if(vo.getCardMoney() != null) {
					if(vo.getCardMoney().equals("")) {
						vo.setCardMoney("0");
					}
				}
				String param = request.getQueryString();
				if(param != null ) {
					vo.setOpendId(Common.getParam(request.getQueryString(),"opendId"));
				}else {
					vo.setOpendId(null);
				}
				int flag = msCustomDao.saveMsCustom(vo);
				if (flag > 0) {
					MsCustomVo msVo = new MsCustomVo();
					msVo.setUserName(vo.getUserName());
					if(vo.getCardNo() != null && !vo.getCardNo().equals("")) {
						updateVipCard(vo);
					}
					return Result.success("success");
				}
				return Result.error("error");
			}
		}catch (Exception e) {
		}
		return null;
	}

	@Override
	public Result<Object> updateMsCustom(MsCustomVo vo) {
		MsCustomVo msVo = new MsCustomVo();
		msVo.setCardNo(vo.getCardNo());
		MsCustomVo msVo1 = new MsCustomVo();
		msVo1.setIdCard(vo.getIdCard());
		List<MsCustom> list = msCustomDao.getMsCustomList(msVo);
		List<MsCustom> list1 = msCustomDao.getMsCustomList(msVo1);
		if(list != null && list.size() > 0) {
			if(!vo.getSysId().equals(list.get(0).getSysId())) {
				return Result.error("会员卡已经被绑定！");
			}else if(!vo.getSysId().equals(list1.get(0).getSysId())) {
				return Result.error("身份证号已经被绑定！");
			}
		}else {
			int flag = msCustomDao.updateMsCustom(vo);
	     	if (flag > 0) {
	     		updateVipCard(vo);
	     		return Result.success("success");
	     	}
	     	return Result.error("error");
		}
		return Result.error("没有任何信息改变");
	}
	
	@Override
	public Result<Object> updateMsCustomBySysId(MsCustomVo vo) {
		List<MsCustom> list = (List<MsCustom>) msCustomDao.getMsCustomList(vo);
		if(vo.getPicture() != null && vo.getPicture().split(",")[0].equals("false")) {
			return Result.error("不支持"+vo.getPicture().split(",")[1]+"的文件类型");
		}else {
			int flag = msCustomDao.updateMsCustomBySysId(vo);
			if (flag > 0) {
				if(list != null && list.size() > 0) {
					for(MsCustom ms:list) {
						Tools.deletePhotoAdrr(ms.getPicture());
					}
				}
				MsCommentVo msv = new MsCommentVo();
				msv.setUserTx(vo.getPicture());
				msv.setCustomSysId(vo.getSysId());
				msCommentDao.updateMsComment(msv);
				return Result.success("success");
			}
			return Result.error("error");
		}
	}
	
	@Override
	public Result<Object> deleteMsCustom(MsCustomVo vo) {
		List<MsCustom> list = msCustomDao.getMsCustomList(vo);
		int flag = msCustomDao.deleteMsCustom(vo);
		if (flag > 0) {
			VipCardVo vo1 =  new VipCardVo();
			vo1.setCardNo(list.get(0).getCardNo());
			vipCardDao.deleteVipCard(vo1);
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	@Transactional
	public Result<Object> updateMsCustomByCardMoney(MsCustomVo vo) {
		if(vo.getSpId() != null && vo.getSpId() != "") {
			String[] spId = vo.getSpId().split(",");
			String spName = null;
			if(spId != null) {
				for(String sysId:spId) {
					ServiceProjectVo spvo = new ServiceProjectVo();
					spvo.setSysId(sysId);
					List<ServiceProject> li = serviceProjectDao.getServiceProjectList(spvo);
					if(spName == null) {
						spName = li.get(0).getName();
					}else {
						spName = spName +","+ li.get(0).getName();
					}
				}
			}
			vo.setSpName(spName);
		}
		MsCustomVo msVo = new MsCustomVo();
		msVo.setSysId(vo.getSysId());
		//flag 1消费，0充值
		List<MsCustom> list = msCustomDao.getMsCustomList(msVo);
		if(vo.getFlag().equals("1")) {
			if(vo.getPayType().equals("0")) {
				if(list != null && list.size() > 0) {
					if((Double.valueOf(list.get(0).getCardMoney()) - Double.valueOf(vo.getCardMoney())) >= 0 || Double.valueOf(list.get(0).getCardMoney()) - Double.valueOf(vo.getIntegralTrue()) >= 0) {
						if(vo.getIntegralFlag().equals("0")) {
							return returnFlag(vo);
						}else {
							if(!vo.getIsUseJf()) {
								return returnFlag(vo);
							}else {
								//判断积分是否大于可用积分
								if(!list.get(0).getIntegral().equals("0")) {
									if((Double.valueOf(list.get(0).getIntegral()) - Double.valueOf(vo.getIntegral())) >= 0){//开启积分兑换时，没有积分可以用
										if(Double.valueOf(vo.getCardMoney())/1 - Double.valueOf(vo.getIntegral()) > 0 ) {
											vo.setIntegral("-"+vo.getIntegral());
											if(vo.getIntegralTrue().equals("0")) {
												vo.setCardMoney("0");
											}else {
												//vo.setCardMoney("-"+vo.getIntegralTrue());
												vo.setCardMoney("-"+getVipCard(vo));
											}
										}else {
											vo.setIntegral("-"+vo.getCardMoney());
											if(vo.getIntegralTrue().equals("0")) {
												vo.setCardMoney("0");
											}else {
												vo.setCardMoney("-"+vo.getIntegralTrue());
											}
										}
										int flag = msCustomDao.updateMsCustomByCardMoney(vo);
										if (flag > 0) {
											int flag1 = saveCustomDetail(vo);
											if (flag1 > 0) {
												updateVipCardMoney(vo);
												//签到页面【去参与】成功消费获取相应积分
												if(vo.getIsJoin()) {
													isJoin(vo);
												}
												updateServiceProject(vo);
												return Result.success("success");
											}
										}
									}else {
										return Result.error("可用积分不足，请重新输入！");
									}
								}else{
									return returnFlag(vo);
								} 
							}
						}
					}else {
						return Result.error("卡余额不足，请充值！");
					}
				}
			}else {
				payForType(vo);
			}
		}else {
			if(list.get(0).getCardNo() == null || list.get(0).getCardNo().equals("") || list.get(0).getCardNo().isEmpty()) {
				return Result.error("请先绑定会员卡，在进行充值！");
			}else {
				vo.setIntegral("0");
				int flag = msCustomDao.updateMsCustomByCardMoney(vo);
				if (flag > 0) {
					int flag1 = saveCustomDetail(vo);
					if (flag1 > 0) {
						updateVipCardMoney(vo);
						IncomePayVo invo = new IncomePayVo();
						invo.setAmount("0");
						invo.setTrueMoney(String.valueOf(Double.valueOf(vo.getCardMoney())));
						invo.setUserName(vo.getUserName());
						invo.setSysId(Tools.getUUID());
						invo.setType("1");
						invo.setInputType("1");
						invo.setStatus("0");
						invo.setDiscount("0");
						invo.setFreeMoney(vo.getFreeMoney());
						invo.setPayType(vo.getPayType());
						incomePayDao.saveIncomePay(invo);
						return Result.success("success");
					}
				}
			}
		}
		return Result.error("error");
	}
	
	public int saveCustomDetail(MsCustomVo vo) {
		CustomDetailVo co = new CustomDetailVo();
		co.setSysId(Tools.getUUID());
		co.setAdminName(getUserName());
		co.setCardNo(vo.getCardNo());
		co.setFlag(vo.getFlag());
		if(vo.getPayMoney() != null && vo.getPayMoney() != "") {
			co.setPayMoney(vo.getPayMoney());
		}else {
			co.setPayMoney("0");
		}
		co.setPayType(vo.getPayType());
		if(vo.getFlag().equals("0")) {
			co.setSpName("充值");
		}else if(vo.getFlag().equals("2")){
			co.setSpName("绑卡");
		}else {
			co.setSpName(vo.getSpName());
		}
		co.setUserName(vo.getUserName());
		if(vo.getIntegralFlag() != null && vo.getIntegralFlag().equals("1")) {
			if(vo.getIsUseJf()) {
				co.setIntegral(vo.getIntegral());
				if(!vo.getIntegralTrue().equals("") && vo.getIntegralTrue() != null) {
					if(vo.getIntegralTrue().equals("0")) {
						co.setCardMoney(vo.getIntegralTrue());
					}else {
						//co.setCardMoney("-"+vo.getIntegralTrue());
						co.setCardMoney("-"+getVipCard(vo));
					}
				}else {
					co.setCardMoney(vo.getCardMoney());
				}
			}else {
				co.setIntegral(vo.getIntegral());
				co.setCardMoney(vo.getCardMoney());
			}
		}else {
			co.setIntegral(vo.getIntegral());
			co.setCardMoney(vo.getCardMoney());
		}
		int flag1 = customDetailDao.saveCustomDetail(co);
		if(flag1 > 0) {
			MsOrderVo ovo = new MsOrderVo();
			ovo.setSysId(vo.getOrderSysId());
			ovo.setIntegral(vo.getIntegral());
			if(Double.valueOf(vo.getCardMoney())<0) {
				ovo.setTrueMoney(String.valueOf(Double.valueOf(vo.getCardMoney().replace("-", ""))));
			}else {
				ovo.setTrueMoney(vo.getCardMoney());
			}
			if(vo.getIntegral() != null && Double.valueOf(vo.getIntegral()) < 0) {
				ovo.setIntegral(vo.getIntegral().replace("-", ""));
			}else {
				ovo.setIntegral("0");
			}
			msOrderDao.updateMsOrderByJf(ovo);
		}
		return flag1;
	}
	
	//根据token获取用户名
	public String getUserName() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = Common.getParam(request.getQueryString(),"token");
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		if(token != null) {
			MsUserVo mvo =new MsUserVo();
			mvo.setSysId(token);
			List<MsUser> msUser = msUserDao.getMsUser(mvo);
			return msUser.get(0).getUserName();
		}else {
			MsCustomVo mcvo =new MsCustomVo();
			mcvo.setOpendId(opendId);
			List<MsCustom> msCustom = msCustomDao.getMsCustomList(mcvo);
			return msCustom.get(0).getUserName();
		}
	}
	//使用非会员支付
	public Result payForType(MsCustomVo vo) {
		//String integral = String.valueOf(Math.round(Double.valueOf(vo.getCardMoney())/10));
		vo.setIntegral("0");
		vo.setPayMoney("-"+vo.getCardMoney());
		vo.setCardMoney("0");
		int flag = msCustomDao.updateMsCustomByCardMoney(vo);
		if (flag > 0) {
			vo.setIntegralFlag("0");
			int flag1 = saveCustomDetail(vo);
			if (flag1 > 0) {
				//签到页面【去参与】成功消费获取相应积分
				if(vo.getIsJoin()) {
					isJoin(vo);
				}
				updateServiceProject(vo);
				return Result.success("success");
			}
		}
		return Result.success("error");
	}
	//签到页面【去参与】成功消费获取相应积分
	public void isJoin(MsCustomVo vo) {
		Integer integral = 20;
		MsCustomVo mvo = new MsCustomVo();
		mvo.setSysId(vo.getSysId());
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mvo);
		mvo.setIntegral(String.valueOf(integral+Integer.valueOf(ms.getIntegral())));
		int flag = msCustomDao.updateMsCustomBySysId(mvo);
		if(flag > 0) {
			CustomDetailVo co = new CustomDetailVo();
			co.setSpName("活动参与获得积分");
			co.setCardMoney("0");
			co.setIntegral(String.valueOf(integral));
			co.setSysId(Tools.getUUID());
			co.setAdminName(getUserName());
			co.setCardNo(vo.getCardNo());
			co.setFlag("3");
			co.setPayMoney("0");
			co.setPayType(vo.getPayType());
			co.setUserName(vo.getUserName());
			customDetailDao.saveCustomDetail(co);
		}
	}
	//不使用积分抵扣直接支付(后端消费支付)
	public Result returnFlag(MsCustomVo vo) {
		vo.setCardMoney("-"+getVipCard(vo));
		String integral = String.valueOf(Math.round(Double.valueOf(getVipCard(vo))/10));
		vo.setIntegral(integral);
		vo.setPayMoney("0");
		int flag = msCustomDao.updateMsCustomByCardMoney(vo);
		if (flag > 0) {
			int flag1 = saveCustomDetail(vo);
			if (flag1 > 0) {
				updateVipCardMoney(vo);
				//签到页面【去参与】成功消费获取相应积分
				if(vo.getIsJoin()) {
					isJoin(vo);
				}
				updateServiceProject(vo);
				return Result.success("success");
			}
		}
		return Result.success("error");
	}
	//支付成功之后产品购买次数+1
	public void updateServiceProject(MsCustomVo vo) {
		String[] spId = vo.getSpId().split(",");
		for(String sysId:spId) {
			ServiceProjectVo svo = new ServiceProjectVo();
			svo.setSysId(sysId);
			svo.setBuyNum("1");
			serviceProjectDao.updateServiceProjectBySysId(svo);
		}
	}
	//支付成功之后修改会员卡余额
	public void updateVipCardMoney(MsCustomVo vo) {
		MsCustomVo mvo = new MsCustomVo();
		mvo.setSysId(vo.getSysId());
		List<MsCustom> list1 = msCustomDao.getMsCustomList(mvo);
		VipCardVo vo1 = new VipCardVo();
		vo1.setCardMoney(list1.get(0).getCardMoney());
		vo1.setCardNo(list1.get(0).getCardNo());
		vipCardDao.updateVipCard(vo1);
	}
	//使用会员卡支付享受相应折扣
	public String getVipCard(MsCustomVo vo) {
		VipCardVo vc = new VipCardVo();
		vc.setCardNo(vo.getCardNo());
		List<VipCard> list = vipCardDao.getVipCardList(vc);
		//String freeMoney = String.valueOf(Math.round((10-Double.valueOf(list.get(0).getDiscount())) * Double.valueOf(vo.getIntegralTrue())/10));
		String cardMoney = String.valueOf(Math.round(Double.valueOf(list.get(0).getDiscount()) * Double.valueOf(vo.getIntegralTrue())/10));
		return cardMoney;
	}
	//绑定会员更改会员卡状态
	public void updateVipCard(MsCustomVo vo) {
		VipCardVo vv = new VipCardVo();
		vv.setCardNo(vo.getCardNo());
		vv.setFlag("1");
		vipCardDao.updateVipCard(vv);
		vo.setFlag("2");
		vo.setIntegral("0");
		vo.setPayType("0");
		saveCustomDetail(vo);
	}
}
