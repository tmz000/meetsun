package com.meetsun.meetsun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.meetsun.meetsun.entity.MsCart;
import com.meetsun.meetsun.vo.MsCartVo;

@Mapper
public interface MsCartDao {
	/**
	 * 获取购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsCart> getMsCartList(MsCartVo vo);
	/**
	 * 根据产品ID获取购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsCart> getMsCartListBySpId(MsCartVo vo);
	/**
	 * 根据opendId获取购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	List<MsCart> getMsCartListByOpendId(MsCartVo vo);
	/**
	 * 添加购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	int saveMsCart(MsCartVo vo);
	/**
	 * 修改购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCart(MsCartVo vo);
	/**
	 * 修改购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	int updateMsCartByStatus(MsCartVo vo);
	/**
	 * 删除购物车信息
	 * @param paramMsUserVo
	 * @return
	 */
	int deleteMsCart(MsCartVo vo);
}
