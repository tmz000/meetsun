package com.meetsun.meetsun.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.service.OrderSpService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.vo.OrderSpVo;

@RestController
@RequestMapping("/orderSp")
public class OrderSpController {
	@Autowired
	private OrderSpService orderSpService;
	
	@RequestMapping({"/getOrderSpList"})
	public Result<Object> getOrderSpList(@RequestBody OrderSpVo vo,HttpServletRequest request) { 
		return orderSpService.getOrderSpList(vo); 
	}
	
	@Log(operation = "新增" , remark="【订单产品关联】新增订单产品关联" , type = "0")
	@RequestMapping({"/saveOrderSp"})
	public Result<Object> saveOrderSp(@RequestBody OrderSpVo vo,HttpServletRequest request) {
		return orderSpService.saveOrderSp(vo); 
	}
   
	@Log(operation = "修改" , remark="【订单产品关联】修改订单产品关联" , type = "0")
	@RequestMapping({"/updateOrderSp"})
	public Result<Object> updateOrderSp(@RequestBody OrderSpVo vo) { 
		return orderSpService.updateOrderSp(vo); 
	}
   
	@Log(operation = "删除" , remark="【订单产品关联】删除订单产品关联" , type = "0")
	@RequestMapping({"/deleteOrderSp"})
	public Result<Object> deleteOrderSp(@RequestBody OrderSpVo vo) { 
		return orderSpService.deleteOrderSp(vo); 
	}
}
