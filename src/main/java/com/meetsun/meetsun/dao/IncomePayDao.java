package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.IncomePay;
import com.meetsun.meetsun.vo.IncomePayVo;

@Mapper
public interface IncomePayDao {
	/**
	 * 获取收支列表
	 * @param vo
	 * @return
	 */
	List<IncomePay> getIncomePayList(IncomePayVo vo);
	int getIncomePayListTotal(IncomePayVo vo);
	/**
	 * 新增收支信息
	 * @param vo
	 * @return
	 */
	int saveIncomePay(IncomePayVo vo);
	/**
	 * 修改收支信息
	 * @param vo
	 * @return
	 */
	int updateIncomePay(IncomePayVo vo);
	/**
	 * 删除收支信息
	 * @param vo
	 * @return
	 */
	int deleteIncomePay(IncomePayVo vo);
	/**
	 * 获取类型和数量
	 * @param vo
	 * @return
	 */
	List<IncomePay> getIncomePayListByStatus(IncomePayVo vo);
	/**
	 * 根据条件获取
	 * @param vo
	 * @return
	 */
	List<IncomePay> getIncomePayListByCreateTime(IncomePayVo vo);
}
