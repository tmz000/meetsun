package com.meetsun.meetsun.service.impl;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.service.ServiceProjectService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.ServiceProjectVo;

@Service
public class ServiceProjectServiceImpl implements ServiceProjectService{
	@Autowired
	private ServiceProjectDao serviceProjectDao;
   
	public Result<Object> getServiceProjectList(ServiceProjectVo vo) {
		List<ServiceProject> list = serviceProjectDao.getServiceProjectList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(serviceProjectDao.getServiceProjectListTotal(vo));
		return result;
	}
	
	public Result<Object> getServiceProjectListByTypeName(ServiceProjectVo vo) {
		List<ServiceProject> list = serviceProjectDao.getServiceProjectListByTypeName(vo);
		return Result.success(list);
	}
   
	public Result<Object> saveServiceProject(ServiceProjectVo vo) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if(isTrue(vo)) {
			String pt = (String) request.getSession().getAttribute("handPhoto");
			if(pt != null) {
				String[] handPhoto = pt.split(",");
				for(String hp:handPhoto) {
					Tools.deletePhotoAdrr(hp);
				}
			}
			return Result.error("已存在相同的项目名称！");
		}else {
			vo.setPhotoAdrr(Tools.savePhoto(vo.getDetailFile()));
			vo.setSysId(Tools.getUUID());
			int flag = serviceProjectDao.saveServiceProject(vo);
			if (flag > 0) {
				return Result.success("success");
			}
		}
		return Result.error("error");
	}
   
	public Result<Object> updateServiceProject(ServiceProjectVo vo) {
		if(isTrue(vo)) {
			return Result.error("已存在相同的项目名称！");
		}else {
			int flag = this.serviceProjectDao.updateServiceProject(vo);
			if (flag > 0) {
				return Result.success("success");
			}
		}
     	return Result.error("error");
	}
 
	public Result<Object> deleteServiceProject(ServiceProjectVo vo) {
		List<ServiceProject> list = serviceProjectDao.getServiceProjectList(vo);
		int flag = this.serviceProjectDao.deleteServiceProject(vo);
		String[] handPhoto = list.get(0).getHandPhoto().split(",");
		for(String hp:handPhoto) {
			Tools.deletePhotoAdrr(hp);
		}
		if (flag > 0) {
			return Tools.deletePhotoAdrr(list.get(0).getPhotoAdrr());
		}
		return Result.error("error");
	}
	
	public Boolean isTrue(ServiceProjectVo vo) {
		ServiceProjectVo vo1 = new ServiceProjectVo();
		vo1.setTypeName(vo.getTypeName());
		List<ServiceProject> list = serviceProjectDao.getServiceProjectList(vo1);
		boolean isb = false;
		if(list != null && list.size() > 0) {
			for(ServiceProject sp:list) {
				if(sp.getSysId().equals(vo.getSysId())) {
					isb = false;
					break;
				}else if(sp.getName().equals(vo.getName())) {
					isb = true;
					break;
				}
			}
		}
		return isb;
	}
 }
