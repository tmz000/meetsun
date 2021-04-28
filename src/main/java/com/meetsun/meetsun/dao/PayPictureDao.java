package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.PayPicture;
import com.meetsun.meetsun.vo.PayPictureVo;

@Mapper
public interface PayPictureDao {
	/**
	 * 获取支付图片信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<PayPicture> getPayPictureList(PayPictureVo vo);
	int getPayPictureListTotal(PayPictureVo vo);
	/**
	 * 添加支付图片信息
	 * @param paramMsUserVo
	 * @return
	 */
	int savePayPicture(PayPictureVo vo);
	/**
	 * 修改支付图片信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updatePayPicture(PayPictureVo vo);
	/**
	 * 删除支付图片信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deletePayPicture(PayPictureVo vo);
}
