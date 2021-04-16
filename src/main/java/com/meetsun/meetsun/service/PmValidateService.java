package com.meetsun.meetsun.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.meetsun.meetsun.entity.PmValidate;
import com.meetsun.meetsun.vo.PmValidateVo;

public interface PmValidateService {
	void sendPasswordResetEmail(SimpleMailMessage email);
    int savePmValidat(PmValidateVo validate);
    List<PmValidate> findUserByResetToken(String resetToken);
    boolean validateLimitation(String email, long requestPerDay, long interval, String token);
    boolean sendValidateLimitation(String email, long requestPerDay, long interval);
}
