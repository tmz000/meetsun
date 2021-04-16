package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.DateBill;
import com.meetsun.meetsun.vo.DateBillVo;

@Mapper
public interface DateBillDao {
	/**
	 * 获取日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<DateBill> getDateBillList(DateBillVo vo);
	/**
	 * 根据类型获取日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<DateBill> getDateBillListByType(DateBillVo vo);
	/**
	 * 根据年份获取日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<DateBill> getDateBillListByYear(DateBillVo vo);
	/**
	 * 添加日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveDateBill(DateBillVo vo);
	/**
	 * 修改日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateDateBill(DateBillVo vo);
	/**
	 * 删除日志账单信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteDateBill(DateBillVo vo);
}
