package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
@Controller
public class ImportAllItems {
	@Autowired
	private SearchItemService searchItemService;
	@RequestMapping(value="/index/importAll",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult importAll() throws Exception{
		 searchItemService.importAllSearchItem();
		 return TaotaoResult.ok();
	}	
}
