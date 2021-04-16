package com.meetsun.meetsun.service;

import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.MsProjectTypeVo;

public interface MsProjectTypeService {
	Result<Object> getMsProjectTypeList(MsProjectTypeVo vo);
	Result<Object> saveMsProjectType(MsProjectTypeVo vo);
	Result<Object> updateMsProjectType(MsProjectTypeVo vo);
	Result<Object> deleteMsProjectType(MsProjectTypeVo vo);
}
