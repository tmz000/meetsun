package com.meetsun.meetsun.controller.pageController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsAdvertDao;
import com.meetsun.meetsun.dao.MsBroadcastDao;
import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.service.CustomDetailService;
import com.meetsun.meetsun.service.MsAdvertService;
import com.meetsun.meetsun.service.MsBroadcastService;
import com.meetsun.meetsun.service.MsCartService;
import com.meetsun.meetsun.service.MsCommentService;
import com.meetsun.meetsun.service.MsCustomService;
import com.meetsun.meetsun.service.MsOrderAddressService;
import com.meetsun.meetsun.service.MsOrderService;
import com.meetsun.meetsun.service.MsProjectTypeService;
import com.meetsun.meetsun.service.MsQuestionService;
import com.meetsun.meetsun.service.MsSignService;
import com.meetsun.meetsun.service.MsUserService;
import com.meetsun.meetsun.service.MsVideoService;
import com.meetsun.meetsun.service.PayPictureService;
import com.meetsun.meetsun.service.PaySureService;
import com.meetsun.meetsun.service.ServiceProjectService;
import com.meetsun.meetsun.service.VideoCustomService;
import com.meetsun.meetsun.service.VipCardService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.EnandeDecrypt;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.CustomDetailVo;
import com.meetsun.meetsun.vo.MsAdvertVo;
import com.meetsun.meetsun.vo.MsBroadcastVo;
import com.meetsun.meetsun.vo.MsCartVo;
import com.meetsun.meetsun.vo.MsCommentVo;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsOrderAddressVo;
import com.meetsun.meetsun.vo.MsOrderVo;
import com.meetsun.meetsun.vo.MsProjectTypeVo;
import com.meetsun.meetsun.vo.MsQuestionVo;
import com.meetsun.meetsun.vo.MsSignVo;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.MsVideoVo;
import com.meetsun.meetsun.vo.PayPictureVo;
import com.meetsun.meetsun.vo.PaySureVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;
import com.meetsun.meetsun.vo.VideoCustomVo;
import com.meetsun.meetsun.vo.VipCardVo;

@RestController
@RequestMapping("/frontEnd")
public class FrontEndPageController {

	@Autowired
	private ServiceProjectService serviceProjectService;
	@Autowired
	private MsProjectTypeService msProjectTypeService;
	@Autowired
	private MsBroadcastService msBroadcastService;
	@Autowired
	private MsAdvertService msAdvertService;
	@Autowired
	private MsCartService msCartService;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private MsOrderAddressService msOrderAddressService;
	@Autowired
	private MsOrderService msOrderService;
	@Autowired
	private MsCustomService msCustomService;
	@Autowired
	private CustomDetailService customDetailService;
	@Autowired
	private MsCommentService msCommentService;
	@Autowired
	private MsVideoService msVideoService;
	@Autowired
	private VideoCustomService videoCustomService;
	@Autowired
	private MsCustomDao msCustomDao;
	@Autowired
	private MsBroadcastDao msBroadcastDao;	
	@Autowired
	private MsAdvertDao msAdvertDao;	
	@Autowired
	private MsQuestionService msQuestionService;	
	@Autowired
	private VipCardService vipCardService;	
	@Autowired
	private MsUserService msUserService;	
	@Autowired
	private PaySureService paySureService;
	@Autowired
	private PayPictureService payPictureService;
	@Autowired
	private MsSignService msSignService;
	
	//前端获取项目列表
	@RequestMapping("/frontEndGetServiceProjectList")
	public Result<Object> getServiceProjectList(@RequestBody ServiceProjectVo vo) { 
		return serviceProjectService.getServiceProjectList(vo); 
	}
	//查询每个类别的最近发布的一个项目信息
	@RequestMapping("/frontEndGetServiceProjectListByTypeName")
	public Result<Object> getServiceProjectListByTypeName(@RequestBody ServiceProjectVo vo,HttpServletRequest request) { 
		return serviceProjectService.getServiceProjectListByTypeName(vo); 
	}
	//获取分类列表
	@RequestMapping("/frontEndGetMsProjectTypeList")
	public Result<Object> getMsProjectTypeList(@RequestBody MsProjectTypeVo vo) { 
		return msProjectTypeService.getMsProjectTypeList(vo); 
	}
	//获取轮播图
	@RequestMapping("/frontEndGetMsBroadcastList")
	public Result<Object> getMsBroadcastList(@RequestBody MsBroadcastVo vo) { 
		return msBroadcastService.getMsBroadcastList(vo); 
	}
	//获取广告的主图
	@RequestMapping("/frontEndGetMsAdvertList")
	public Result<Object> getMsAdvertList(@RequestBody MsAdvertVo vo) { 
		return msAdvertService.getMsAdvertList(vo); 
	}
	//点击广告的轮播图增加一次点击次数
	@RequestMapping("/frontEndUpdateMsAdvert")
	public Result<Object> updateMsAdvert(@RequestBody MsAdvertVo vo) { 
		int flag = msAdvertDao.updateMsAdvertStatus(vo);
		if(flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}
	//获取某个分类的所有列表信息
	@RequestMapping("/frontEndGetServiceProjectListByTypeNameList")
	public Result<Object> getServiceProjectListByTypeNameList(@RequestBody ServiceProjectVo vo) { 
		return serviceProjectService.getServiceProjectList(vo); 
	}
	//根据某个分类获取分类列表
	@RequestMapping("/frontEndGetMsProjectTypeListByName")
	public Result<Object> getMsProjectTypeListByName(@RequestBody MsProjectTypeVo vo) { 
		return msProjectTypeService.getMsProjectTypeList(vo); 
	}
	//根据产品sysId获取项目详情,同时增加1次点击次数
	@RequestMapping("/frontEndGetServiceProjectListBySysId")
	public Result<Object> getServiceProjectListBySysId(@RequestBody ServiceProjectVo vo,HttpServletRequest request) { 
		serviceProjectDao.updateServiceProjectClickNumBySysId(vo);
		return serviceProjectService.getServiceProjectList(vo); 
	}
	//点击轮播增加一次点击次数
	@RequestMapping("/frontEndUpdateMsBroadcast")
	public Result<Object> updateMsBroadcast(@RequestBody MsBroadcastVo vo,HttpServletRequest request) { 
		int flag = msBroadcastDao.updateMsBroadcast(vo);
		if(flag > 0) {
			return Result.success("success"); 
		}
		return Result.error("系统异常，请联系管理员！");
	}
	//购物车
	@RequestMapping("/frontEndGetMsCartList")
	public Result<Object> getMsCartList(@RequestBody MsCartVo vo,HttpServletRequest request) { 
		return msCartService.getMsCartList(vo); 
	}
	@RequestMapping("/frontEndGetMsCartListBySpId")
	public Result<Object> getMsCartListBySpId(@RequestBody MsCartVo vo,HttpServletRequest request) { 
		return msCartService.getMsCartListBySpId(vo); 
	}
	@RequestMapping("/frontEndGetMsCartListByOpendId")
	public Result<Object> getMsCartListByOpendId(@RequestBody MsCartVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		vo.setOpendId(opendId);
		return msCartService.getMsCartListByOpendId(vo); 
	}
	@Log(operation = "新增" , remark="【购物车】新增购物车信息" , type = "1")
	@RequestMapping("/frontEndSaveMsCart")
	public Result<Object> saveMsCart(@RequestBody MsCartVo vo,HttpServletRequest request) {
		String opendId =  Common.getParam(request.getQueryString(),"opendId");
		vo.setOpendId(opendId);
		return msCartService.saveMsCart(vo); 
	}
	@Log(operation = "修改" , remark="【购物车】修改购物车数量" , type = "1")
	@RequestMapping("/frontEndUpdateMsCart")
	public Result<Object> updateMsCart(@RequestBody MsCartVo vo) { 
		return msCartService.updateMsCart(vo); 
	}
	@Log(operation = "新增" , remark="【购物车】修改购物车状态" , type = "1")
	@RequestMapping("/frontEndUpdateMsCartByStatus")
	public Result<Object> updateMsCartByStatus(@RequestBody MsCartVo vo) { 
		return msCartService.updateMsCartByStatus(vo); 
	}
	@Log(operation = "删除" , remark="【购物车】删除购物车信息" , type = "1")
	@RequestMapping("/frontEndDeleteMsCart")
	public Result<Object> deleteMsCart(@RequestBody MsCartVo vo) { 
		return msCartService.deleteMsCart(vo); 
	}
	//收货地址
	@RequestMapping("/frontEndGetMsOrderAddressList")
	public Result<Object> getMsOrderAddressList(@RequestBody MsOrderAddressVo vo,HttpServletRequest request) { 
		vo.setOpendId(Common.getParam(request.getQueryString(),"opendId"));
		return msOrderAddressService.getMsOrderAddressList(vo); 
	}
	@RequestMapping("/frontEndGetMsOrderAddressListBySysId")
	public Result<Object> getMsOrderAddressListBySysId(@RequestBody MsOrderAddressVo vo,HttpServletRequest request) { 
		return msOrderAddressService.getMsOrderAddressListBySysId(vo); 
	}
	@Log(operation = "新增" , remark="【收货地址】新增收货地址信息" , type = "1")
	@RequestMapping("/frontEndSaveMsOrderAddress")
	public Result<Object> saveMsOrderAddress(@RequestBody MsOrderAddressVo vo,HttpServletRequest request) {
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom msCustom = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(msCustom.getUserName());
		vo.setOpendId(opendId);
		return msOrderAddressService.saveMsOrderAddress(vo); 
	}
	@Log(operation = "修改" , remark="【收货地址】修改收货地址信息" , type = "1")
	@RequestMapping("/frontEndUpdateMsOrderAddress")
	public Result<Object> updateMsOrderAddress(@RequestBody MsOrderAddressVo vo) { 
		return msOrderAddressService.updateMsOrderAddress(vo); 
	}
	@Log(operation = "修改" , remark="【收货地址】修改收货地址是否常用" , type = "1")
	@RequestMapping("/frontEndUpdateMsOrderAddressByIsUse")
	public Result<Object> updateMsOrderAddressByIsUse(@RequestBody MsOrderAddressVo vo) { 
		return msOrderAddressService.updateMsOrderAddressByIsUse(vo); 
	}
	@Log(operation = "删除" , remark="【收货地址】删除收货地址信息" , type = "1")
	@RequestMapping("/frontEndDeleteMsOrderAddress")
	public Result<Object> deleteMsOrderAddress(@RequestBody MsOrderAddressVo vo) { 
		return msOrderAddressService.deleteMsOrderAddress(vo); 
	}
	//订单
	@RequestMapping("/frontEndGetMsOrderList")
	public Result<Object> getMsOrderList(@RequestBody MsOrderVo vo,HttpServletRequest request) { 
		vo.setOpendId(Common.getParam(request.getQueryString(),"opendId"));
		vo.setIsDel("0");
		return msOrderService.getMsOrderList(vo); 
	}
	@Log(operation = "删除" , remark="【订单】APP端订单信息删除" , type = "1")
	@RequestMapping("/frontEndUpdateMsOrderByIsDel")//APP端删除
	public Result<Object> updateMsOrderByIsDel(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByIsDel(vo); 
	}
	@Log(operation = "修改" , remark="【订单】APP付款订单信息修改" , type = "1")
	@RequestMapping("/frontEndUpdateMsOrderByStatus")//APP付款
	public Result<Object> updateMsOrderByStatus(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByStatus(vo); 
	}
	@Log(operation = "删除" , remark="【订单】APP端订单信息收货" , type = "1")
	@RequestMapping("/frontEndUpdateMsOrderByIsShouhuo")//APP端收货
	public Result<Object> updateMsOrderByIsShouhuo(@RequestBody MsOrderVo vo) { 
		return msOrderService.updateMsOrderByIsShouhuo(vo); 
	}
	//个人中心
	@RequestMapping("/frontEndGetMsCustomList")
	public Result<Object> getMsCustomList(@RequestBody MsCustomVo vo,HttpServletRequest request) { 
		vo.setOpendId(Common.getParam(request.getQueryString(),"opendId"));
		return msCustomService.getMsCustomList(vo); 
	}
	//会员卡信息
	@RequestMapping("/frontEndGetVipCardList")
	public Result<Object> getVipCardList(@RequestBody VipCardVo vo,HttpServletRequest request) { 
		return vipCardService.getVipCardList(vo); 
	}
	@Log(operation = "修改" , remark="【个人信息】修改个人信息" , type = "1")
	@RequestMapping("/frontEndUpdateMsCustomBySysId")
	public Result<Object> updateMsCustomBySysId(@RequestParam(required = false,value="picture") MultipartFile picture,@RequestParam("vo")  String vo1,@RequestParam(required = false,value="base64") String base64,HttpServletRequest request) throws Exception { 
		JSON json = JSONObject.parseObject(vo1);
		MsCustomVo vo = JSONObject.toJavaObject(json,MsCustomVo.class);
		if(picture != null) {
			vo.setPicture(Tools.savePhoto(picture));
		}
		/*
		 * if(base64 != null) { vo.setPicture(Tools.base64ToFile(base64, "tx\\")); }
		 */
		if(vo.getPassWord() != null && vo.getPassWord() != "") {
			vo.setPassWord(EnandeDecrypt.encryptBASE64(vo.getPassWord().getBytes()));
		}
		return msCustomService.updateMsCustomBySysId(vo); 
	}
	//获取会员消费记录
	@RequestMapping("/frontEndGetCustomDetailList")
	public Result<Object> getCustomDetailList(@RequestBody CustomDetailVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(ms.getUserName());
		return customDetailService.getCustomDetailList(vo); 
	}
	@RequestMapping("/frontEndGetCustomDetailListByMonth")
	public Result<Object> getCustomDetailListByMonth(@RequestBody CustomDetailVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(ms.getUserName());
		return customDetailService.getCustomDetailListByMonth(vo); 
	}
	//使用积分付款
	@Log(operation = "修改" , remark="【积分付款】使用积分付款修改信息" , type = "1")
	@RequestMapping("/frontEndUpdateMsCustomByCardMoney")
	public Result<Object> updateMsCustomByCardMoney(@RequestBody MsCustomVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(ms.getUserName());
		return msCustomService.updateMsCustomByCardMoney(vo); 
	}
	//保存评论信息
	@Log(operation = "新增" , remark="【评论】新增订单的评论信息" , type = "1")
	@RequestMapping("/frontEndupdateMsOrderByIsPl")
	public Result<Object> updateMsOrderByIsPl(@RequestBody MsOrderVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(ms.getUserName());
		vo.setUserTx(ms.getPicture());
		vo.setCustomSysId(ms.getSysId());
		return msOrderService.updateMsOrderByIsPl(vo); 
	}
	//获取评论信息列表
	@RequestMapping("/frontEndGetMsCommentList")
	public Result<Object> getMsCommentList(@RequestBody MsCommentVo vo) { 
		return msCommentService.getMsCommentList(vo); 
	}
	@RequestMapping({"/frontEndGetMsCommentListByStarLv"})
	public Result<Object> getMsCommentListByStarLv(@RequestBody MsCommentVo vo,HttpServletRequest request) { 
		return msCommentService.getMsCommentListByStarLv(vo); 
	}
	
	//视频专区列表
	@RequestMapping("/frontEndGetMsVideoList")
	public Result<Object> getMsVideoList(@RequestBody MsVideoVo vo,HttpServletRequest request) { 
		return msVideoService.getMsVideoList(vo); 
	}
	@Log(operation = "修改" , remark="【视频】修改个人视频点赞数量" , type = "1")
	@RequestMapping("/frontEndUpdateMsVideo")
	public Result<Object> updateMsVideo(@RequestBody MsVideoVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setCustomSysId(ms.getSysId());
		return msVideoService.updateMsVideo(vo); 
	}
	//客户视频点赞
	@RequestMapping("/frontEndGetVideoCustomList")
	public Result<Object> getVideoCustomList(@RequestBody VideoCustomVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setCustomSysId(ms.getSysId());
		return videoCustomService.getVideoCustomList(vo); 
	}
	//信息反馈信息
	@Log(operation = "新增" , remark="【反馈信息】新增反馈信息" , type = "1")
	@RequestMapping({"/frontEndSaveMsQuestion"})
	public Result<Object> saveMsQuestion(@RequestBody MsQuestionVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setCustomId(ms.getSysId());
		return msQuestionService.saveMsQuestion(vo); 
	}
	//查询反馈信息
	@RequestMapping({"/frontEndGetMsQuestionList"})
	public Result<Object> getMsQuestionList(@RequestBody MsQuestionVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setCustomId(ms.getSysId());
		return msQuestionService.getMsQuestionList(vo); 
	}
	//前端删除反馈信息
	@Log(operation = "删除" , remark="【反馈信息】删除反馈信息" , type = "1")
	@RequestMapping({"/frontEndUpdateMsQuestion"})
	public Result<Object> updateMsQuestion(@RequestBody MsQuestionVo vo,HttpServletRequest request) { 
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserName(ms.getUserName());
		return msQuestionService.updateMsQuestion(vo); 
	}
	//获取客服电话
	@RequestMapping({"/frontEndGetMsUserList"})
	public Result<Object> frontEndGetMsUserList(@RequestBody MsUserVo vo) { 
		return msUserService.getMsUserList(vo); 
	}
	@Log(operation = "新增" , remark="【支付确认】新增支付确认" , type = "1")
	@RequestMapping({"/frontEndSavePaySure"})
	public Result<Object> frontEndSavePaySure(@RequestBody PaySureVo vo) {
		vo.setSysId(Tools.getUUID());
		return paySureService.savePaySure(vo); 
	}
	@RequestMapping({"/frontEndGetPaySureList"})
	public Result<Object> frontEndGetPaySureList(@RequestBody PaySureVo vo) {
		return paySureService.getPaySureList(vo); 
	}
	@RequestMapping({"/frontEndGetPayPictureList"})
	public Result<Object> frontEndGetPayPictureList(@RequestBody PayPictureVo vo) {
		return payPictureService.getPayPictureList(vo); 
	}
	//签到
	@RequestMapping({"/frontEndGetMsSignList"})
	public Result<Object> frontEndGetMsSignList(@RequestBody MsSignVo vo,HttpServletRequest request) {
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserId(ms.getSysId());
		return msSignService.getMsSignList(vo); 
	}
	@RequestMapping({"/frontEndSaveMsSign"})
	public Result<Object> frontEndSaveMsSign(@RequestBody MsSignVo vo,HttpServletRequest request) {
		String opendId = Common.getParam(request.getQueryString(),"opendId");
		MsCustomVo mc = new MsCustomVo();
		mc.setOpendId(opendId);
		MsCustom ms = msCustomDao.getMsCustomByOpendId(mc);
		vo.setUserId(ms.getSysId());
		return msSignService.saveMsSign(vo); 
	}
}
