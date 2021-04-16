package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.ServiceProjectVo;

public interface ServiceProjectService {
	Result<Object> getServiceProjectList(ServiceProjectVo paramServiceProjectVo);
	Result<Object> getServiceProjectListByTypeName(ServiceProjectVo paramServiceProjectVo);
	Result<Object> saveServiceProject(ServiceProjectVo paramServiceProjectVo);
	Result<Object> updateServiceProject(ServiceProjectVo paramServiceProjectVo);
	Result<Object> deleteServiceProject(ServiceProjectVo paramServiceProjectVo);
}
