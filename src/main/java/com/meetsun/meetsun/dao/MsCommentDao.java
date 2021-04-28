package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsComment;
import com.meetsun.meetsun.vo.MsCommentVo;

@Mapper
public interface MsCommentDao {
	/**
	 * 获取评论信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsComment> getMsCommentList(MsCommentVo vo);
	int getMsCommentListTotal(MsCommentVo vo);
	/**
	 * 根据星级获取评论信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsComment> getMsCommentListByStarLv(MsCommentVo vo);
	/**
	 * 添加评论信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsComment(MsCommentVo vo);
	/**
	 * 修改评论信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsComment(MsCommentVo vo);
	/**
	 * 删除评论信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsComment(MsCommentVo vo);
}
