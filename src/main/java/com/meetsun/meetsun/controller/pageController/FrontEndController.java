package com.meetsun.meetsun.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontEndController {
	//前端登录
	@RequestMapping("/frontEndLogin")
	public String login() {
		return "front-end/login";
	}
	//注册页面
	@RequestMapping("/register")
	public String register() {
		return "front-end/register";
	}
	
	//index页面
	@RequestMapping("/frontEndIndex")
	public String index() {
		return "front-end/index";
	}
	//根据分类获取产品列表页面
	@RequestMapping("/frontEndTypeList")
	public String frontEndTypeList() {
		return "front-end/base/list";
	}
	//获取产品详情页面
	@RequestMapping("/frontEndAdvertDetail")
	public String frontEndAdvertDetail() {
		return "front-end/base/advert_detail";
	}
	//获取产品详情页面
	@RequestMapping("/frontEndDetail")
	public String frontEndDetail() {
		return "front-end/base/detail";
	}
	//购物车页面
	@RequestMapping("/frontEndCart")
	public String frontEndCart() {
		return "front-end/base/cart";
	}
	//我的页面
	@RequestMapping("/frontEndMyIndex")
	public String frontEndMyIndex() {
		return "front-end/my/my-index";
	}
	//订单结算页面
	@RequestMapping("/frontEndOrder")
	public String frontEndOrder() {
		return "front-end/base/order";
	}
	//新增收货地址页面
	@RequestMapping("/frontEndOrderAddress")
	public String frontEndOrderAddress() {
		return "front-end/base/address-form";
	}
	//收货地址管理页面
	@RequestMapping("/frontEndAddress")
	public String frontEndAddress() {
		return "front-end/base/address";
	}
	//付款页面
	@RequestMapping("/frontEndPay")
	public String frontEndPay() {
		return "front-end/base/pay";
	}
	//订单管理页面
	@RequestMapping("/frontEndMyOrder")
	public String frontEndMyOrder() {
		return "front-end/my/my-order";
	}
	//订单详情页面
	@RequestMapping("/frontEndMyOrderDetail")
	public String frontEndMyOrderDetail() {
		return "front-end/my/my-order-detail";
	}
	//个人信息页面
	@RequestMapping("/frontEndMyDetail")
	public String frontEndMyDetail() {
		return "front-end/my/my-detail";
	}
	//我的会员卡页面
	@RequestMapping("/frontEndMyVipCard")
	public String frontEndMyVipCard() {
		return "front-end/my/my-vip-card";
	}
	//会员记录页面
	@RequestMapping("/frontEndMyVip")
	public String frontEndMyVip() {
		return "front-end/my/my-vip";
	}
	//会员详情页面
	@RequestMapping("/frontEndMyVipDetail")
	public String frontEndMyVipDetail() {
		return "front-end/my/my-vip-detail";
	}
	//待支付页面
	@RequestMapping("/frontEndMyOrderPay")
	public String frontEndMyOrderPay() {
		return "front-end/my/my-order-pay";
	}
	//待收货页面
	@RequestMapping("/frontEndMyOrDelivery")
	public String frontEndMyOrDelivery() {
		return "front-end/my/my-order-delivery";
	}
	//待收货页面
	@RequestMapping("/frontEndMyOrIsUse")
	public String frontEndMyOrIsUse() {
		return "front-end/my/my-order-isUse";
	}
	//待评论页面
	@RequestMapping("/frontEndMyOrPl")
	public String frontEndMyOrPl() {
		return "front-end/my/my-order-pl";
	}
	//退款售后页面
	@RequestMapping("/frontEndMyOrEmpty")
	public String frontEndMyOrEmpty() {
		return "front-end/my/my-order-empty";
	}
	//客服页面
	@RequestMapping("/frontEndCustomerservice")
	public String frontEndCustomerservice() {
		return "front-end/base/customerservice";
	}
	//问题反馈页面
	@RequestMapping("/frontEndQuestion")
	public String frontEndQuestion() {
		return "front-end/base/question";
	}
	//问题反馈填写页面
	@RequestMapping("/frontEndMyQuestion")
	public String frontEndMyQuestion() {
		return "front-end/my/my-question";
	}
	//问题反馈列表
	@RequestMapping("/frontEndQuestionList")
	public String frontEndQuestionList() {
		return "front-end/my/my-question-list";
	}
	//视频页面
	@RequestMapping("/frontEndMsVideo")
	public String frontEndMsVideo() {
		return "front-end/base/video";
	}
	//签到页面
	@RequestMapping("/frontEndMySign")
	public String frontEndMySign() {
		return "front-end/my/my-sign";
	}
	//我的签到详情页面
	@RequestMapping("/frontEndMySignDetail")
	public String frontEndMySignDetail() {
		return "front-end/my/my-sign-detail";
	}
}
