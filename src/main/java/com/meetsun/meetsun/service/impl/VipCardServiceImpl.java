package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.VipCardDao;
import com.meetsun.meetsun.entity.VipCard;
import com.meetsun.meetsun.service.VipCardService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.VipCardVo;

@Service
public class VipCardServiceImpl implements VipCardService{
	
	@Autowired
	private VipCardDao vipCardDao;
	
	@Override
	public Result<Object> getVipCardList(VipCardVo vo) {
		List<VipCard> list = vipCardDao.getVipCardList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveVipCard(VipCardVo vo) {
		int flag = 0;
		if(vo.getNumber() != null && vo.getNumber() > 0) {
			if(vo.getNumber() <= 50) {
				for(int i=0;i<vo.getNumber();i++) {
					VipCardVo vo1 = new VipCardVo();
					vo1.setSysId(Tools.getUUID());
					vo1.setCardNo("MS"+Tools.getMMddHHmmssSSS());
					vo1.setFlag(vo.getFlag());
					vo1.setUserName(vo.getUserName());
					vo1.setCardMoney(vo.getCardMoney());
					vo1.setDiscount(vo.getDiscount());
					flag = vipCardDao.saveVipCard(vo1);
					if (flag == 0) {
						break;
					}
				}
			}else {
				return Result.error("单次批量新增会员卡数量不能超过50张！");
			}
		}
		if(flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateVipCard(VipCardVo vo) {
		int flag = vipCardDao.updateVipCard(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteVipCard(VipCardVo vo) {
		List<VipCard> list = vipCardDao.getVipCardList(vo);
		if(list != null && list.size() > 0) {
			if(list.get(0).getFlag().equals("1")) {
				return Result.error("会员卡"+list.get(0).getCardNo()+"已经被绑定，请先取消绑定！");
			}else {
				int flag = vipCardDao.deleteVipCard(vo);
				if (flag > 0) {
					return Result.success("success");
				}
			}
		}
		return Result.error("error");
	}
}
