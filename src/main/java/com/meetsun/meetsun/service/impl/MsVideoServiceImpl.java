package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.MsVideoDao;
import com.meetsun.meetsun.dao.ServiceProjectDao;
import com.meetsun.meetsun.dao.VideoCustomDao;
import com.meetsun.meetsun.entity.MsVideo;
import com.meetsun.meetsun.entity.ServiceProject;
import com.meetsun.meetsun.entity.VideoCustom;
import com.meetsun.meetsun.service.MsVideoService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.MsVideoVo;
import com.meetsun.meetsun.vo.ServiceProjectVo;
import com.meetsun.meetsun.vo.VideoCustomVo;

@Service
public class MsVideoServiceImpl implements MsVideoService{
	
	@Autowired
	private MsVideoDao msVideoDao;
	@Autowired
	private ServiceProjectDao serviceProjectDao;
	@Autowired
	private VideoCustomDao videoCustomDao;
	
	@Override
	public Result<Object> getMsVideoList(MsVideoVo vo) {
		List<MsVideo> list = msVideoDao.getMsVideoList(vo);
		Result result = new Result();
		result.setStatus("01");
		result.setMessage("success");
		result.setRows(list);
		result.setTotal(msVideoDao.getMsVideoListTotal(vo));
		return result;
	}

	@Override
	public Result<Object> saveMsVideo(MsVideoVo vo) {
		vo.setSysId(Tools.getUUID());
		String videoAddr = Tools.savePhoto(vo.getFile());
		if(videoAddr.contains("false")) {
			return Result.error("不支持"+videoAddr.split(",")[1]+"的文件类型");
		}else {
			vo.setVideoAddr(videoAddr);
			ServiceProjectVo spvo = new ServiceProjectVo();
			spvo.setSysId(vo.getSpId());
			ServiceProject st = serviceProjectDao.getServiceProjectList(spvo).get(0);
			vo.setSpName(st.getName());
			int flag = msVideoDao.saveMsVideo(vo);
			if (flag > 0) {
				return Result.success("success");
			}
			return Result.error("error");
		}
	}

	@Override
	public Result<Object> updateMsVideo(MsVideoVo vo) {
		List<MsVideo> list = msVideoDao.getMsVideoList(vo);
		VideoCustomVo vo1 = new VideoCustomVo();
		vo1.setCustomSysId(vo.getCustomSysId());
		vo1.setVideoSysId(vo.getSysId());
		List<VideoCustom> vslist = videoCustomDao.getVideoCustomList(vo1);
		if(vslist != null && vslist.size() > 0) {
			vo.setClickNumber(String.valueOf(Integer.valueOf(list.get(0).getClickNumber())-1));
		}else {
			vo.setClickNumber(String.valueOf(Integer.valueOf(list.get(0).getClickNumber())+1));
		}
		int flag = msVideoDao.updateMsVideo(vo);
     	if (flag > 0) {
     		if(vslist != null && vslist.size() > 0) {
     			vo1.setSysId(vslist.get(0).getSysId());
     			videoCustomDao.deleteVideoCustom(vo1);
     		}else {
     			vo1.setSysId(Tools.getUUID());
     			videoCustomDao.saveVideoCustom(vo1);
     		}
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteMsVideo(MsVideoVo vo) {
		List<MsVideo> list = msVideoDao.getMsVideoList(vo);
		int flag = msVideoDao.deleteMsVideo(vo);
		if (flag > 0) {
			return Tools.deletePhotoAdrr(list.get(0).getVideoAddr());
		}
		return Result.error("error");
	}
}
