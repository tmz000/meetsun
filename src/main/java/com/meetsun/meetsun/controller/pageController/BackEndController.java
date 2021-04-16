package com.meetsun.meetsun.controller.pageController;
 
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
 
 
@Controller
public class BackEndController{
	
	//后端登录
	@RequestMapping({"/"})
	public String login() { 
		return "back-end/login"; 
	}
	@RequestMapping({"/reset"})
	public String resetPass() { 
		return "back-end/reset"; 
	}
	//到访信息
	@RequestMapping({"/visit"})
	public String visit() { 
		return "back-end/visit/visit"; 
	}
	//到访信息详情
	@RequestMapping({"/visitDetail"})
	public String visitDetail() { 
		return "back-end/visit/visit-detail"; 
	}
	//退出系统
	@RequestMapping({"/outLogin"})
	public String outLogin(HttpSession session, SessionStatus sessionStatus) {
		session.invalidate();
	    sessionStatus.setComplete();
	    return "redirect:/";
	}
	//index
	@RequestMapping({"/index"})
	public String index() {
		return "back-end/index"; 
	}
	//主页
	@RequestMapping({"/home"})
	public String home() { 
		return "back-end/home"; 
	}
	//产品管理页面
	@RequestMapping({"/project"})
	public String product() { 
		return "back-end/serviceProject/project"; 
	}
	//分类管理页面
	@RequestMapping({"/projectType"})
	public String productType() { 
		return "back-end/serviceProject/projectType"; 
	}
	//评论页面
	@RequestMapping({"/msComment"})
	public String msComment() { 
		return "back-end/serviceProject/comment"; 
	}
	//用户管理页面
	@RequestMapping({"/msUser"})
	public String msUser() { 
		return "back-end/system/msUser"; 
	}
	//角色管理页面
	@RequestMapping({"/role"})
	public String role() { 
		return "back-end/system/role"; 
	}
	//角色权限页面
	@RequestMapping({"/roleMenu"})
	public String roleMenu() { 
		return "back-end/system/role-menu"; 
	}
	//菜单（权限）页面
	@RequestMapping({"/menu"})
	public String menu() { 
		return "back-end/system/menu"; 
	}
	//日志页面
	@RequestMapping({"/logInfo"})
	public String logInfo() { 
		return "back-end/system/log"; 
	}
	//用户详情页面
	@RequestMapping({"/oneDetail"})
	public String msUserOne() { 
		return "back-end/system/oneDetail"; 
	}
	//活动发布页面
	@RequestMapping({"/advert"})
	public String advert() { 
		return "back-end/baseData/advert"; 
	}
	//订单确认页面
	@RequestMapping({"/orderSure"})
	public String orderSure() { 
		return "back-end/order/order-sure"; 
	}
	//订单信息页面
	@RequestMapping({"/order"})
	public String order() { 
		return "back-end/order/order"; 
	}
	//订单物流页面
	@RequestMapping({"/orderWl"})
	public String orderWl() { 
		return "back-end/order/order-wl"; 
	}
	//订单日志页面
	@RequestMapping({"/orderLog"})
	public String orderLog() { 
		return "back-end/order/order-log"; 
	}
	//订单详情页面
	@RequestMapping({"/orderDetail"})
	public String orderDetail() { 
		return "back-end/order/order-detail"; 
	}
	//轮播页面
	@RequestMapping({"/msBroadcast"})
	public String msBroadcast() { 
		return "back-end/baseData/broadcast"; 
	}
	//视频页面
	@RequestMapping({"/msVideo"})
	public String msVideo() { 
		return "back-end/baseData/video"; 
	}
	//会员卡页面
	@RequestMapping({"/vipCard"})
	public String vipCard() { 
		return "back-end/baseData/vip-card"; 
	}
	//视频播放页面
	@RequestMapping({"/msVideoPlay"})
	public String msVideoPlay() { 
		return "back-end/baseData/video-play"; 
	}
	//收支概览
	@RequestMapping({"/incomePay"})
	public String incomePay() { 
		return "back-end/incomePay/income-pay"; 
	}
	//收入页面
	@RequestMapping({"/income"})
	public String income() { 
		return "back-end/incomePay/income"; 
	}
	//支出页面
	@RequestMapping({"/pay"})
	public String pay() { 
		return "back-end/incomePay/pay"; 
	}
	//详情页面
	@RequestMapping({"/detail"})
	public String detail() { 
		return "back-end/detail"; 
	}
	//客户页面
	@RequestMapping({"/msCustom"})
	public String msCustom() { 
		return "back-end/baseData/custom"; 
	}
	//客户消费记录页面
	@RequestMapping({"/customDetail"})
	public String customDetail() { 
		return "back-end/incomePay/customDetail"; 
	}
	//支付方式设置页面
	@RequestMapping({"/payPicture"})
	public String payPicture() { 
		return "back-end/system/pay-picture"; 
	}
	//日录账单页面(主)
	@RequestMapping({"/dateBill"})
	public String dateBill() { 
		return "back-end/commonTools/date_bill"; 
	}
	//日录账单页面(副)
	@RequestMapping({"/dateBillVice"})
	public String dateBillVice() { 
		return "back-end/commonTools/date_bill-vice"; 
	}
	//测试页面
	@RequestMapping({"/question"})
	public String question() { 
		return "back-end/baseData/question"; 
	}
	//测试页面
	@RequestMapping({"/test"})
	public String newhtml() { 
		return "back-end/baseData/test"; 
	}
	
 }
