package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsProjectType;
import com.meetsun.meetsun.vo.MsProjectTypeVo;

@Mapper
public interface MsProjectTypeDao {
	/**
	 * 获取项目类型
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsProjectType> getMsProjectTypeList(MsProjectTypeVo vo);
	/**
	 * 添加项目类型
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsProjectType(MsProjectTypeVo vo);
	/**
	 * 修改项目类型
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsProjectType(MsProjectTypeVo vo);
	/**
	 * 删除项目类型
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsProjectType(MsProjectTypeVo vo);
}
