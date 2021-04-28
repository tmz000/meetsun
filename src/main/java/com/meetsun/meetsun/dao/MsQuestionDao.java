package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsQuestion;
import com.meetsun.meetsun.vo.MsQuestionVo;

@Mapper
public interface MsQuestionDao {
	/**
	 * 获取反馈信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsQuestion> getMsQuestionList(MsQuestionVo vo);
	int getMsQuestionListTotal(MsQuestionVo vo);
	/**
	 * 添加反馈信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsQuestion(MsQuestionVo vo);
	/**
	 * 修改反馈信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsQuestion(MsQuestionVo vo);
	/**
	 * 删除反馈信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsQuestion(MsQuestionVo vo);
}
