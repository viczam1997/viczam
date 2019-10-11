package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping(value="/content/query/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUi showIndex(Long categoryId,Integer page,Integer rows){
		return contentService.getContentList(categoryId,page,rows);
	}
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult ContentAdd(TbContent tbContent){
		return contentService.addContent(tbContent);
	}
}
