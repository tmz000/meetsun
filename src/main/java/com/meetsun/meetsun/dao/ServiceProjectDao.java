package com.meetsun.meetsun.dao;

import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.vo.ServiceProjectVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceProjectDao {
	/**
	 * 查询服务项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<ServiceProject> getServiceProjectList(ServiceProjectVo paramServiceProjectVo);
	/**
	 * 查询每个类别的最近发布的一个项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<ServiceProject> getServiceProjectListByTypeName(ServiceProjectVo paramServiceProjectVo);
	/**
	 * 添加项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveServiceProject(ServiceProjectVo paramServiceProjectVo);
	/**
	 * 更新项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateServiceProject(ServiceProjectVo paramServiceProjectVo);
	/**
	 * 发布活动之后更新项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateServiceProjectBySysId(ServiceProjectVo paramServiceProjectVo);
	/**
	 * app点击查看项目更新clickNum
	 * @param paramMsUserVo
	 * @return
	 */
	int updateServiceProjectClickNumBySysId(ServiceProjectVo paramServiceProjectVo);
	/**
	 * 删除项目信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteServiceProject(ServiceProjectVo paramServiceProjectVo);
}
