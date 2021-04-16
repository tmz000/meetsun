package com.meetsun.meetsun.common;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.MsCustomDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.entity.MsCustom;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.LogInfoService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.IpUtil;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsCustomVo;
import com.meetsun.meetsun.vo.MsUserVo;

@Aspect
@Component
public class LogAspect {
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private MsUserDao msUserDao;
	@Autowired
	private MsCustomDao msCustomDao;
	/** 
     * 日志业务逻辑方法切入点 
     */ 
	@Pointcut("@annotation(com.meetsun.meetsun.common.Log)")  
	public void logInfo() { 
	}  
      
	
    /** 
     * 管理员添加操作日志(后置通知) 
     * @param joinPoint 
     * @param rtv 
     * @throws Throwable 
     */  
    @AfterReturning(value="logInfo()", argNames="rtv", returning="rtv")  
    public void insertServiceCallCalls(JoinPoint point, Object rtv) throws Throwable{  
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        String token = null;
        if(request.getQueryString() != null) {
        	token = Common.getParam(request.getQueryString(),"token");
        }	
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
    	Log slog = method.getAnnotation(Log.class);
    	//判断参数  
        if(point.getArgs() == null){
            return;  
        }  
        //获取方法名  
        //String methodName = joinPoint.getSignature().getName();  
        //获取操作内容  
        Object args = point.getArgs()[0];
        String className = null;
        LogInfo log = new LogInfo();
        if(args != null) {
        	className = point.getArgs()[0].getClass().getName();
        	className = className.substring(className.lastIndexOf(".") + 1);
        	log.setModule(className.toLowerCase());
        }else {
        	log.setModule(className);
        }
        String opContent = adminOptionContent(point.getArgs(), slog.operation());  
        //创建日志对象  
        log.setSysId(Tools.getUUID());
        if(slog.type().equals("0")) {
        	MsUser user = getUser(request);
        	if(user != null) {
        		log.setUserName(user.getUserName());  
        		log.setRealName(user.getRealName());  
        	}else {
        		log.setUserName(null);  
        		log.setRealName(null);  
        	}
        }else {
        	MsCustom custom = getCustom(request);
        	if(custom != null) {
        		log.setUserName(custom.getUserName());  
        		log.setRealName(custom.getRealName());  
        	}else {
        		log.setUserName(null);  
        		log.setRealName(null);  
        	}
        }
        log.setMethod(request.getRequestURI());
        //操作内容  
        log.setContent(opContent);
        Result rt = (Result)rtv;
       // log.setResult(JSON.toJSONString(rt));
        log.setResult("{状态:"+returnStatus(rt.getStatus())+",返回信息:"+rt.getMessage()+"}");
        log.setRemark(slog.remark());
        //操作
        log.setOperation(slog.operation());
        log.setType(slog.type());
        log.setIp(IpUtil.getIpAddr());
        logInfoService.insertLog(log);
    }  
    
    
	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	private String adminOptionContent(Object[] args, String type) throws Exception {
		if (args == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Object info = args[0];
		String className = null;
		if(info != null) {
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			sb.append(type+className+" 属性名和值：");
			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();
			// 遍历方法，判断get方法
			for (Method method : methods) {
				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {
					continue;// 不处理
				}
				Object rsValue = null;
				try {
					// 调用get方法，获取返回值
					rsValue = method.invoke(info);
					if (rsValue == null) {
						continue;
					}
				} catch (Exception e) {
					continue;
				}
				// 将值加入内容中
				sb.append(" " + methodName.substring(3) + "-->" + rsValue + "  ");
			}
		}
		return sb.toString();
	}
	
	public String returnStatus(String status) {
		if(status.equals("01")) {
			return "成功";
		}else {
			return "失败";
		}
	}
	
	public MsUser getUser(HttpServletRequest request) {
		String token = null;
        if(request.getQueryString() != null) {
        	token = Common.getParam(request.getQueryString(),"token");
        }	
		MsUserVo mvo =new MsUserVo();
		mvo.setSysId(token);
		MsUser us = null;
		List<MsUser> list = msUserDao.getMsUser(mvo);
		if(list != null && list.size() == 1) {
			us = list.get(0);
		}
		return us;
	}
	
	public MsCustom getCustom(HttpServletRequest request) {
		String opendId = null;
        if(request.getQueryString() != null) {
        	opendId = Common.getParam(request.getQueryString(),"opendId");
        }	
		MsCustomVo mvo =new MsCustomVo();
		mvo.setOpendId(opendId);
		MsCustom us = msCustomDao.getMsCustomByOpendId(mvo);
		return us;
	}
	
}
