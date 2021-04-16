package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.PmValidateDao;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.entity.PmValidate;
import com.meetsun.meetsun.service.PmValidateService;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.PmValidateVo;

@Service
public class PmValidateServiceImpl implements PmValidateService{
	
	@Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PmValidateDao pmValidateDao;

    /**
     * 发送邮件：@Async进行异步调用发送邮件接口
     * @param email
     */
    @Override
    @Async
    public void sendPasswordResetEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

    /**
     * 在pm_validate表中插入一条validate记录，userid，email属性来自pm_user表，token由UUID生成
     * @param validate
     * @param users
     * @param token
     * @return
     */
    @Override
    public int savePmValidat(PmValidateVo validate){
        validate.setType("passwordReset");
        return pmValidateDao.savePmValidat(validate);
    }

    /**
     * pm_validate表中，通过token查找重置申请记录
     * @param token
     * @return
     */
    @Override
    public List<PmValidate> findUserByResetToken(String token){
        PmValidateVo vo = new PmValidateVo();
        vo.setResetToken(token);
        return pmValidateDao.getPmValidateList(vo);
    }

    /**
     * 验证是否发送重置邮件：每个email的重置密码每日请求上限为requestPerDay次，与上一次的请求时间间隔为interval分钟。
     * @param email
     * @param requestPerDay
     * @param interval
     * @return
     */
    @Override
    public boolean sendValidateLimitation(String email, long requestPerDay, long interval){

        return true;
    }

    /**
     * 验证连接是否失效：链接有两种情况失效 1.超时 2.最近请求的一次链接自动覆盖之前的链接（待看代码）
     * @param email
     * @param requestPerDay
     * @param interval
     * @return
     */
    @Override
    public boolean validateLimitation(String email, long requestPerDay, long interval, String token){
    	
        return true;
    }

}
