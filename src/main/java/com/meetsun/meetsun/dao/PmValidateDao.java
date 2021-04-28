package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.PmValidate;
import com.meetsun.meetsun.vo.PmValidateVo;

@Mapper
public interface PmValidateDao {
	/**
	 * 密码重置（邮箱）
	 * @param validate
	 * @return
	 */
    int savePmValidat(PmValidateVo validate);
    int updatePmValidat(PmValidateVo validate);
    List<PmValidate> getPmValidateList(PmValidateVo vo);
    int getPmValidateListTotal(PmValidateVo vo);
}
