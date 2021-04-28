package com.meetsun.meetsun.service.impl;
 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meetsun.meetsun.dao.ARoleDao;
import com.meetsun.meetsun.dao.MsUserDao;
import com.meetsun.meetsun.entity.ARole;
import com.meetsun.meetsun.entity.MsUser;
import com.meetsun.meetsun.service.MsUserService;
import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.EnandeDecrypt;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.ARoleVo;
import com.meetsun.meetsun.vo.MsUserVo;
 
 
 
@Service
public class MsUserServiceImpl implements MsUserService {
	@Autowired
	private MsUserDao msUserDao;
	@Autowired
	private ARoleDao aRoleDao;
	
	@Override
	public Result<Object> saveMsUser(MsUserVo us) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		MsUserVo vo1 = new MsUserVo();
		vo1.setUserName(us.getUserName());
		List<MsUser> list1 = msUserDao.getMsUser(vo1);
		List<MsUser> list2 = new ArrayList<MsUser>();
		if(us.getEmail() != null) {
			MsUserVo vo2 = new MsUserVo();
			vo2.setEmail(us.getEmail());
			list2 = msUserDao.getMsUser(vo2);
		}
		us.setSysId(Tools.getUUID());
     	byte[] bytes = us.getPassWord().getBytes();
     	List<ARole> listRole = aRoleDao.getARoleList(new ARoleVo());
     	if (list1.size() > 0) {
     		return Result.error("用户名已注册！");
     	}else if (list2.size() > 0) {
     		return Result.error("用户邮箱已注册！");
     	}else{
     		if(us.getMobile() != null && us.getMobile() != "") {
     			if(!Common.phoneRegex(us.getMobile())) {
     				return Result.error("手机号码格式不正确！");
     			}
     		}
     		if(us.getEmail() != null && us.getEmail() != "") {
     			if(!Common.checkEmail(us.getEmail())) {
     				return Result.error("邮箱格式不正确！");
     			}
     		}
     		try {
     			us.setPassWord(EnandeDecrypt.encryptBASE64(bytes));
     			if (us.getStatus() == null) {
     				us.setStatus("0");
     			}
     		} catch (Exception exception) {}
     		if(listRole != null && listRole.size() > 0) {
     			us.setRoleId(listRole.get(listRole.size()-1).getSysId());
     		}
     		int flag = msUserDao.saveMsUser(us);
     		if (flag > 0) {
     			if(us.getFlag() == null) {
     				//request.getSession().setAttribute("msUser", msUserDao.getMsUser(us).get(0));
     				return Result.success(us.getSysId());
     			}else {
     				return Result.success("success");
     			}
     		} 
     		return Result.error("error");
     	} 
	}
	
	@Override
	public Result<Object> getMsUserList(MsUserVo us) {
		List<MsUser> list = msUserDao.getMsUserList(us);
		return Result.success(list);
	}
	@Override
	public Result<Object> getMsUser(MsUserVo us) {
		//MsUser msUser = (MsUser)request.getSession().getAttribute("msUser");
		//MsUser msUser = msUserDao.getMsUser(us).get(0);
		//String userName = null;
//		if(msUser != null) {
//			userName = msUser.getUserName();
//		}
//		if(msUser == null || !userName.equals(us.getUserName())) {
//			return getLoginMsUser(us);
//		}else {
//			return Result.error("该账号已在别处登录！");
//		}
		return getLoginMsUser(us);
	}
	
	@Override
	public Result<Object> getMsUserBySysId(MsUserVo paramMsUserVo) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<MsUser> list = msUserDao.getMsUserBySysId(paramMsUserVo);
		if(list != null && list.size() > 0) {
			return Result.success(list);
		}
		return Result.error("error");
	}
	
	public Result<Object> getLoginMsUser(MsUserVo us) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		List<MsUser> list = msUserDao.getMsUser(us);
		String password = null;
		if (list.size() == 1 && ((MsUser)list.get(0)).getStatus().equals("0")) {
			try {
				password = new String(EnandeDecrypt.decryptBASE64(((MsUser)list.get(0)).getPassWord()));
			} catch (Exception exception) {}
			if (password.equals(us.getPassWord())) {
				if(list.get(0).getLoginWord() != null && list.get(0).getLoginWord() != "") {
					if(us.getLoginWord().equals(list.get(0).getLoginWord())) {
						//request.getSession().setAttribute("msUser", list.get(0));
						return Result.success(list.get(0).getSysId());
					}else {
						return Result.error("登录口令错误！");
					}
				}else {
					MsUserVo vo = new MsUserVo();
					vo.setSysId(list.get(0).getSysId());
					vo.setLoginWord(us.getLoginWord());
					msUserDao.updateMsUserBySysId(vo);
					//return Result.success("success");
					return Result.error("首次登录设置口令成功,牢记登录口令;请再次点击登录!");
				}
			} 
			return Result.error("密码错误！");
		} 
		if (list.size() == 1 && ((MsUser)list.get(0)).getStatus().equals("1")) {
			return Result.error("该账号暂时没有权限登录，请联系管理员！");
		}
		return Result.error("没有该账号，请注册！");
	}
	
	@Override
	public Result<Object> getAllMsUser(MsUserVo us) {
		List<MsUser> list = this.msUserDao.getAllMsUser(us);
		List<MsUser> list1 = new ArrayList<>();
		for (MsUser ms : list) {
			try {
				ARoleVo vo = new ARoleVo();
				if(ms.getRoleId() != null) {
					vo.setSysId(ms.getRoleId());
					List<ARole> li = aRoleDao.getARoleList(vo);
					if(li != null && li.size() > 0) {
						for(ARole a : li ) {
							ms.setRoleName(a.getRoleName());
						}
					}
				}else {
					ms.setRoleName("暂无权限!");
				}
				ms.setPassWord(new String(EnandeDecrypt.decryptBASE64(ms.getPassWord())));
			} catch (Exception exception) {}
			list1.add(ms);
		} 
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list1);
		result.setTotal(msUserDao.getAllMsUserTotal(us));
		return result;
	}
   
	@Override
	public Result<Object> updateMsUserBySysId(MsUserVo us) {
		if(us.getMobile() != null && us.getMobile() != "") {
 			if(!Common.phoneRegex(us.getMobile())) {
 				return Result.error("手机号码格式不正确！");
 			}
 		}
		if(us.getEmail() != null && us.getEmail() != "") {
			if(!Common.checkEmail(us.getEmail())) {
				return Result.error("邮箱格式不正确！");
			}
		}
		if(us.getIsCustomer() != null && us.getIsCustomer().equals("1")) {
			if(us.getMobile() == null || us.getMobile() == "") {
 				return Result.error("手机号码不能为空！");
	 		}
		}
		if(us.getPassWord() != null && us.getPassWord() != "") {
			try {
				us.setPassWord(EnandeDecrypt.encryptBASE64(us.getPassWord().getBytes()));
			} catch (Exception e) {}
		}
		int flag = this.msUserDao.updateMsUserBySysId(us);
		if (flag > 0) {
			return Result.success("修改成功");
		}
		return Result.error("修改失败");
	}
   
	@Override
	public Result<Object> deleteMsUserBySysId(MsUserVo us) {
		int flag = this.msUserDao.deleteMsUserBySysId(us);
		if (flag > 0) {
			return Result.success("删除成功");
		}
		return Result.error("删除失败");
	}
}
