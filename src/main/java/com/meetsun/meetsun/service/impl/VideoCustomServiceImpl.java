package com.meetsun.meetsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetsun.meetsun.dao.VideoCustomDao;
import com.meetsun.meetsun.entity.VideoCustom;
import com.meetsun.meetsun.service.VideoCustomService;
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.Tools;
import com.meetsun.meetsun.vo.VideoCustomVo;

@Service
public class VideoCustomServiceImpl implements VideoCustomService{
	
	@Autowired
	private VideoCustomDao videoCustomDao;
	
	@Override
	public Result<Object> getVideoCustomList(VideoCustomVo vo) {
		List<VideoCustom> list = videoCustomDao.getVideoCustomList(vo);
		return Result.success(list);
	}

	@Override
	public Result<Object> saveVideoCustom(VideoCustomVo vo) {
		vo.setSysId(Tools.getUUID());
		int flag = videoCustomDao.saveVideoCustom(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

	@Override
	public Result<Object> updateVideoCustom(VideoCustomVo vo) {
		int flag = videoCustomDao.updateVideoCustom(vo);
     	if (flag > 0) {
     		return Result.success("success");
     	}
     	return Result.error("error");
	}

	@Override
	public Result<Object> deleteVideoCustom(VideoCustomVo vo) {
		int flag = videoCustomDao.deleteVideoCustom(vo);
		if (flag > 0) {
			return Result.success("success");
		}
		return Result.error("error");
	}

}
