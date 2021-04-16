package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsProjectTypeDao;
import com.meetsun.meetsun.entity.MsProjectType;
import com.meetsun.meetsun.service.MsProjectTypeService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsProjectTypeVo;

@Service
public class MsProjectTypeServiceImpl implements MsProjectTypeService{
	
	@Autowired
	private MsProjectTypeDao MsProjectTypeDao;
	
	@Override
	public Result<Object> getMsProjectTypeList(MsProjectTypeVo vo) {
		List<MsProjectType> list = MsProjectTypeDao.getMsProjectTypeList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveMsProjectType(MsProjectTypeVo vo) {
		vo.setSysId(Tools.getUUID());
		MsProjectTypeVo vo1 = new MsProjectTypeVo();
		List<MsProjectType> list = MsProjectTypeDao.getMsProjectTypeList(vo1);
		int flag = 0;
		boolean isTrue = true;
		if(list != null && list.size() > 0) {
			for(MsProjectType msp:list) {
				if(msp.getName().equals(vo.getName())) {
					isTrue = false;
					break;
				}else {
					isTrue = true;
				}
			}
			if(isTrue) {
				flag = MsProjectTypeDao.saveMsProjectType(vo);
			}else {
				return Result.error("已存在相同类别！");
			}
		}else {
			flag = MsProjectTypeDao.saveMsProjectType(vo);
		}
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateMsProjectType(MsProjectTypeVo vo) {
		int flag = MsProjectTypeDao.updateMsProjectType(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsProjectType(MsProjectTypeVo vo) {
		int flag = MsProjectTypeDao.deleteMsProjectType(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
}
