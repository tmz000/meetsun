package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsBroadcastDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsBroadcast;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.MsBroadcastService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsBroadcastVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class MsBroadcastServiceImpl implements MsBroadcastService{
	
	@Autowired
	private MsBroadcastDao msBroadcastDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	
	@Override
	public Result<Object> getMsBroadcastList(MsBroadcastVo vo) {
		List<MsBroadcast> list = msBroadcastDao.getMsBroadcastList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msBroadcastDao.getMsBroadcastListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsBroadcast(MsBroadcastVo vo) {
		MsBroadcastVo vo1 = new MsBroadcastVo();
		vo1.setStatus("0");
		List<MsBroadcast> list = msBroadcastDao.getMsBroadcastList(vo);
		if(list != null && list.size() < 5) {
			ServiceProjectVo spvo = new ServiceProjectVo();
			spvo.setSysId(vo.getSpId());
			List<ServiceProject> lisp = serviceProjectDao.getServiceProjectList(spvo);
			if(lisp != null && lisp.size() > 0) {
				for(ServiceProject sp:lisp) {
					vo.setSpName(sp.getName());
				}
			}
			try {
				vo.setPhotoAdrr(Tools.savePhoto(vo.getFile()));
				vo.setSysId(Tools.getUUID());
				int flag = msBroadcastDao.saveMsBroadcast(vo);
				if(flag > 0) {
					return Result.success("success");
				}
			}catch (Exception e) {
				return Result.error("error");
			}
		}else {
			return Result.error("已存在5个轮播图，请先禁用在上传！");
		}
		return null;
	}

	@Override
	public Result<Object> updateMsBroadcast(MsBroadcastVo vo) {
		MsBroadcastVo vo1 = new MsBroadcastVo();
		vo1.setStatus("0");
		List<MsBroadcast> list = msBroadcastDao.getMsBroadcastList(vo);
		if(list != null && list.size() < 5) {
			ServiceProjectVo spvo = new ServiceProjectVo();
			List<ServiceProject> lisp = serviceProjectDao.getServiceProjectList(spvo);
			if(list != null && list.size() > 0) {
				for(ServiceProject sp:lisp) {
					vo.setSpName(sp.getName());
				}
			}
			int flag = msBroadcastDao.updateMsBroadcast(vo);
	     	if (flag > 0) {
	     		return Result.success("success");
	     	}
		}else {
			return Result.error("已存在5个轮播图，请先禁用在启用！");
		}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsBroadcast(MsBroadcastVo vo) {
		List<MsBroadcast> list = msBroadcastDao.getMsBroadcastList(vo);
		int flag = msBroadcastDao.deleteMsBroadcast(vo);
		if (flag > 0) {
			return Tools.deletePhotoAdrr(list.get(0).getPhotoAdrr());
		}
		return Result.error("error");
	}
}
