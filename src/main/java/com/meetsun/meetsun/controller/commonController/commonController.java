package com.meetsun.meetsun.controller.commonController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetsun.meetsun.until.Common;
import com.meetsun.meetsun.until.Result;

@RestController
@RequestMapping("/common")
public class commonController {

	@RequestMapping({"/getBillType"})
	public Result<Object> getBillType() { 
		return Result.success(Common.getBillType()); 
	}
}
