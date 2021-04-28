package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.PaySure;
import com.meetsun.meetsun.vo.PaySureVo;

@Mapper
public interface PaySureDao {
	/**
	 * 获取支付确认信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<PaySure> getPaySureList(PaySureVo vo);
	int getPaySureListTotal(PaySureVo vo);
	/**
	 * 添加支付确认信息
	 * @param paramMsUserVo
	 * @return
	 */
	int savePaySure(PaySureVo vo);
	/**
	 * 修改支付确认信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updatePaySure(PaySureVo vo);
	/**
	 * 删除支付确认信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deletePaySure(PaySureVo vo);
}
