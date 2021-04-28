package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsOrderAddress;
import com.meetsun.meetsun.vo.MsOrderAddressVo;

@Mapper
public interface MsOrderAddressDao {
	/**
	 * 获取地址信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsOrderAddress> getMsOrderAddressList(MsOrderAddressVo vo);
	int getMsOrderAddressListTotal(MsOrderAddressVo vo);
	/**
	 * 添加地址信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsOrderAddress(MsOrderAddressVo vo);
	/**
	 * 修改地址信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsOrderAddress(MsOrderAddressVo vo);
	/**
	 * 根据主键修改是否默认
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsOrderAddressByIsUse(MsOrderAddressVo vo);
	/**
	 * 根据opendId修改是否默认
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsOrderAddressByOpendId(MsOrderAddressVo vo);
	/**
	 * 删除地址信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsOrderAddress(MsOrderAddressVo vo);
}
