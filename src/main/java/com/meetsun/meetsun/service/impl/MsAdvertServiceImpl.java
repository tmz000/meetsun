package com.meetsun.meetsun.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetsun.meetsun.dao.MsAdvertDao;
import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsAdvert;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.MsAdvertService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsAdvertVo;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class MsAdvertServiceImpl implements MsAdvertService{
	
	@Autowired
	private MsAdvertDao msAdvertDao;
	
	@Autowired
	private MsCustomDao msCustomDao;
	
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	
	@Override
	public Result<Object> getMsAdvertList(MsAdvertVo vo) {
		List<MsAdvert> list = msAdvertDao.getMsAdvertList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msAdvertDao.getMsAdvertListTotal(vo));
		return result;
	}

	@Override
	@Transactional
	public Result<Object> saveMsAdvert(MsAdvertVo vo) {
		try {
			vo.setPhotoAdrr(Tools.savePhoto(vo.getFile()));
			vo.setSysId(Tools.getUUID());
			MsCustomVo co = new MsCustomVo();
			co.setIntegralFlag("1");
			if(vo.getType().equals("2")) {
				MsAdvertVo vo1 = new MsAdvertVo();
				vo1.setType("2");
				List<MsAdvert> list = msAdvertDao.getMsAdvertList(vo1);
				Boolean isTrue = false;
				if(list != null && list.size() > 0) {
					for(MsAdvert msadt:list) {
						Integer toDate = Integer.valueOf(msadt.getToDate().replace("-", ""));
						if(msadt.getStatus().equals("0")) {
							isTrue = true;
							break;
						}
					}
				}
				if(isTrue) {
					return Result.error("已存在未到期积分抵现活动！");
				}else {
					int flag = msAdvertDao.saveMsAdvert(vo);
					int flag1 = msCustomDao.updateMsCustomByIntegralFlag(co);
					if(flag1 > 0 && flag > 0) {
						return Result.success("success");
					}
				}
			}else {
				MsAdvertVo msvo = new MsAdvertVo();
				msvo.setType(vo.getType());
				msvo.setStatus("0");
				List<MsAdvert> list = msAdvertDao.getMsAdvertList(msvo);
				boolean isTrue = true;
				for(MsAdvert vt:list) {
					if(vo.getSpId() != null) {
						if(vt.getSpId().equals(vo.getSpId())) {
							if(vt.getStatus().equals("0")) {
								isTrue = false;
							}else {
								isTrue = true;
							}
						}
					}else {
						if(list.size() > 0) {
							isTrue = false;
						}else {
							isTrue = true;
						}
					}
				}
				if(!isTrue) {
					return Result.error("已存在未到期的相同活动！");
				}else {
					ServiceProjectVo spvo = new ServiceProjectVo();
					spvo.setSysId(vo.getSpId());
					ServiceProject sp = serviceProjectDao.getServiceProjectList(spvo).get(0);
					if(vo.getType().equals("1")) {
						vo.setSpName("全部");
					}else {
						vo.setSpName(sp.getName());
						ServiceProjectVo vo2 = new ServiceProjectVo();
						vo2.setSysId(vo.getSpId());
						vo2.setDiscount(vo.getDiscount());
						vo2.setAmount(vo.getAmount());
						vo2.setIsJoin("1");
						serviceProjectDao.updateServiceProjectBySysId(vo2);
					}
					int flag = msAdvertDao.saveMsAdvert(vo);
					if(flag > 0) {
						return Result.success("success");
					}
				}
			}
		}catch (Exception e) {
			return Result.error("error");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsAdvert(MsAdvertVo vo) {
		MsAdvertVo vo1 = new MsAdvertVo();
		List<MsAdvert> list = msAdvertDao.getMsAdvertList(vo1);
		boolean isTrue = true;
		if(list != null && list.size() > 0) {
			if(list.get(0).getType().equals("0")) {
				for(MsAdvert mt:list) {
					if(mt.getSpName().equals(vo.getSpName())) {
						if(mt.getStatus().equals("0")) {
							isTrue = false;
						}else {
							isTrue = true;
						}
					}
				}
				if(!isTrue) {
					return Result.error("已存在未到期的相同活动！");
				}else {
					int flag = msAdvertDao.updateMsAdvert(vo);
					if(vo.getStatus().equals("0")) {
						ServiceProjectVo sv = new ServiceProjectVo();
						sv.setIsJoin("1");
						sv.setSysId(vo.getSpId());
						serviceProjectDao.updateServiceProjectBySysId(sv);
					}
			     	if (flag > 0) {
			     		return Result.success("success");
			     	}
				}
			}else {
				int flag = msAdvertDao.updateMsAdvert(vo);
		     	if (flag > 0) {
		     		return Result.success("success");
		     	}
			}
			
		}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsAdvert(MsAdvertVo vo) {
		List<MsAdvert> list = msAdvertDao.getMsAdvertList(vo);
		MsCustomVo co = new MsCustomVo();
		co.setIntegralFlag("0");
		if(list.get(0).getType().equals("2")) {
			int flag1 = msCustomDao.updateMsCustomByIntegralFlag(co);
			if(flag1 > 0) {
				Tools.deletePhotoAdrr(list.get(0).getPhotoAdrr());
				int flag = msAdvertDao.deleteMsAdvert(vo);
				if(flag1 > 0) {
					return Result.success("success"); 
				}
			}
		}else {
			return deleteMsAdvertByType(list,vo);
		}
		return Result.error("error");
	}
	
	@Transactional
	public Result<Object> deleteMsAdvertByType(List<MsAdvert> list,MsAdvertVo vo) {
		String filePath = Common.realPath+list.get(0).getPhotoAdrr();
		File file = new File(filePath);
		try {
		    boolean f = file.delete(); // 删除照片
		    if (f) {
		    	ServiceProjectVo vo2 = new ServiceProjectVo();
				vo2.setName(list.get(0).getSpName());
				vo2.setSysId(list.get(0).getSpId());
				vo2.setIsJoin("0");
				int flag1 = serviceProjectDao.updateServiceProjectBySysId(vo2);
		    	int flag = msAdvertDao.deleteMsAdvert(vo);
		    	if(flag > 0 && flag1 > 0) {
		    		return Result.success("success");
		    	}
		    } else {
		    	return Result.error("图片不存在或其他异常！");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    return Result.error("图片不存在或其他异常！");
		}
		return Result.error("error");
	}
}
