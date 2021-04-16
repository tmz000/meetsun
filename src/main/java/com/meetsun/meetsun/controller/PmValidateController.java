package com.meetsun.meetsun.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.common.Log;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.dao.PmValidateDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.entity.PmValidate;
import com.meetsun.meetsun.service.MsUserService;
import com.meetsun.meetsun.service.PmValidateService;
import com.meetsun.meetsun.until.EnandeDecrypt;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsUserVo;
import com.meetsun.meetsun.vo.PmValidateVo;

@RestController
@RequestMapping("/pmValidate")
public class PmValidateController {
	@Autowired
    private PmValidateService validateService;
	@Autowired
	private MsUserDao userDao;
	@Autowired
	private PmValidateDao pmValidateDao;


    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.nickname}")
    private String nickname;

    /**
     * 发送忘记密码邮件请求，每日申请次数不超过5次，每次申请间隔不低于1分钟
     * @param email
     * @param request
     * @return
     */
    @Log(operation = "邮件发送" , remark= "发送忘记密码邮件", type = "0")
    @RequestMapping(value = "/sendValidationEmail", method = {RequestMethod.POST})
    public Result<Object> sendValidationEmail( @RequestBody PmValidateVo vo,HttpServletRequest request){
        try {
        	MsUserVo msvo = new MsUserVo();
        	msvo.setEmail(vo.getEmail());
        	List<MsUser> users = userDao.getMsUser(msvo);
        	if (users == null || users.size() == 0){
        		return Result.error(vo.getEmail()+"该邮箱所属用户不存在");
        	}else {
        		// 若允许重置密码，则在pm_validate表中插入一行数据，带有token
        		PmValidateVo vo1 = new PmValidateVo();
        		vo1.setUserId(users.get(0).getSysId());
        		vo1.setStatus("0");
        		List<PmValidate> pl = pmValidateDao.getPmValidateList(vo1);
        		if (pl == null || pl.size() == 0){
        			sendMail(vo,request,users);
        			return Result.success("success");
        		}else {
        			PmValidate validate = pl.get(0);
        			long minutes = getMinutes(validate);
        			if(minutes > 15) {
        				vo1.setSysId(validate.getSysId());
        				vo1.setStatus("2");
        				pmValidateDao.updatePmValidat(vo1);
        				sendMail(vo, request, users);
        				return Result.success("success");
        			}
        			return Result.error(vo.getEmail()+"该邮箱已存在可修改密码邮件，请不要重复操作！");
        		}
        	}
        }catch (Exception e) {
        	return Result.error("系统异常，请联系管理员！");
		}
    }

    /**
     * 将url的token和数据库里的token匹配，成功后便可修改密码，token有效期为60分钟
     * @param token
     * @param password
     * @param confirmPassword
     * @return
     */
    @Log(operation = "重置密码", remark = "重置密码", type = "0")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Result<Object> resetPassword(@RequestBody MsUserVo vo){
    	try {
	        byte[] bytes = vo.getPassWord().getBytes();
	        // 通过token找到validate记录
	        List<PmValidate> validates = validateService.findUserByResetToken(vo.getToken());
	        if (validates == null || validates.size() == 0){
	        	return Result.error("该链接失效，请重新发送！");
	        }else {
	        	PmValidateVo vo1 = new PmValidateVo();
	        	PmValidate validate = validates.get(0);
	        	long minutes = getMinutes(validate);
	        	if(minutes > 15) {
	        		vo1.setSysId(validate.getSysId());
					vo1.setStatus("2");
					pmValidateDao.updatePmValidat(vo1);
	        		return Result.error("该链接失效，请重新发送！");
	        	}else {
	        		if (validateService.validateLimitation(validate.getEmail(), Long.MAX_VALUE, 60, vo.getToken())){
	        			String userId = validate.getUserId();
	        			MsUserVo msvo = new MsUserVo();
	        			msvo.setUserName(vo.getUserName());
	        			List<MsUser> users = userDao.getMsUser(msvo);
	        			if(users == null || users.size() == 0) {
	        				return Result.error("用户名不存在，请重新输入！");
	        			}else {
	        				if (vo.getPassWord().equals(vo.getPasswordConfirm())) {
	        					vo.setPassWord(EnandeDecrypt.encryptBASE64(bytes));
	        					int flag = userDao.updateMsUserBySysId(vo);
	        					if(flag == 1) {
	        						vo1.setSysId(validate.getSysId());
	        						vo1.setStatus("1");
	        						pmValidateDao.updatePmValidat(vo1);
	        						return Result.success("密码重置成功！");
	        					}else {
	        						return Result.error("系统异常，请联系管理员！");
	        					}
	        				}else {
	        					return Result.error("确认密码和密码不一致，请重新输入");
	        				}
	        			}
	        		}else {
		            	return Result.error("该链接失效");
		            }
	            }
	        }
    	} catch (Exception e) {
        	return Result.error("系统异常，请联系管理员！");
		}
    }
    
    //邮件发送
    public void sendMail(PmValidateVo vo,HttpServletRequest request ,List<MsUser> users) {
    	vo.setUserId(users.get(0).getSysId());
    	vo.setResetToken(Tools.getUUID());
    	vo.setSysId(Tools.getUUID());
        validateService.savePmValidat(vo);
        // 设置邮件内容
        String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom(nickname+'<'+from+'>');
        passwordResetEmail.setTo(vo.getEmail());
        passwordResetEmail.setSentDate(new Date());
        passwordResetEmail.setSubject("【Meet_Sun】找回密码");
        passwordResetEmail.setText("您正在申请重置密码，请点击此链接重置密码: \n" + appUrl + "/reset?token=" + vo.getResetToken()+" \n(该链接15分钟内有效)");
        validateService.sendPasswordResetEmail(passwordResetEmail);
    }
    
    //查看创建时间是否大于15分钟
    public long getMinutes(PmValidate validate) throws ParseException {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = df.parse(Tools.get19DateTimes());
    	Date d2 = df.parse(validate.getCreateTime());
    	long diff = d1.getTime() - d2.getTime();
        long minutes = diff / (1000 * 60);
        return minutes;
    }
}
