package com.taotao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping(value="/content/category/list",method=RequestMethod.GET)
	@ResponseBody
	public List<EasyUITreeNode> showListTree(@RequestParam(value="id",defaultValue="0")Long parentId){
		return contentCategoryService.getContentCategoryList(parentId);
	}
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult  createContent(Long parentId,String name){
		return contentCategoryService.createContentCategory(parentId, name);
	}
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateContent(Long id,String name){
		return contentCategoryService.updateContentCategory(id, name);
	}
	@RequestMapping(value="/content/category/delete/")
	@ResponseBody
	public String deleteContent(Long id){
		return contentCategoryService.deleteContent(id);
	}
}
