package com.meetsun.meetsun.until;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

public class Common {
	//上传图片的位置
	public static String realPath = "/opt/meetsun/ms_picture/";
	//public static String realPath = "F:/test/";
	public static String handPhoto = "img/logo.jpg";
	public static String type = "BMP、JPEG、JP2、GIF、TIFF、PNG、EXIF、WBMP、MBM、JPG、MP4";
	
	//手机号验证
	public static boolean phoneRegex(String phone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        return isMatch;
	}
	
	//邮箱验证
	public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
		  String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		  Pattern regex = Pattern.compile(check);
		  Matcher matcher = regex.matcher(email);
		  flag = matcher.matches();
	  }catch(Exception e){
		  flag = false;
	  }
	  return flag;
	}
	
	//校验座机
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean isPhone = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			isPhone = m.matches();
		} else {
			m = p2.matcher(str);
			isPhone = m.matches();
		}
		return isPhone;
	}
	
	public static List<String> getBillType() {
		String[] str = new String[] {
			"柴米油盐",
			"日用百货",
			"医疗保健",
			"零食小吃",
			"餐饮美食",
			"交通出行",
			"家具家装",
			"网上购物",
			"数码电器",
			"文教娱乐",
			"美容美发",
			"母婴亲子",
			"爱车养车",
			"运动健康",
			"住房缴费",
			"投资理财",
			"通讯物流",
			"充值缴费",
			"红包转账",
			"亲友代付",
			"工资收入",
			"亲朋送礼",
			"其他消费"
		};
		List<String> li = new ArrayList<String>();
		for(String s:str) {
			li.add(s);
		}
		return li;
	}
	
	public static String getParam(String url, String name) {
	   // String params = url.substring(url.indexOf("?") + 1, url.length());
	    Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(url);
	    return split.get(name);
	}
	//不用过滤或拦截的请求
	public static String[] getIncludeUrls() {
		return new String[] { "/","reset","/logInfo/getLogInfoList", "/msUser/loginMsUser", "/msUser/saveMsUser","/msCustom/getMsCustomByName","/register","/msCustom/saveMsCustom", "/home",".js", ".css", ".png", ".jpg", ".jpeg",".mp4", ".gif", ".woff", ".ttf", ".ico" };
	}
}
