package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

@Controller
public class PageController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/index")
	public String showIndex(Model model){
		List<TbContent> contentlist=contentService.getContentByCategoryId(89l);
		List<Ad1Node> list=new ArrayList<Ad1Node>();
		for (TbContent tbContent : contentlist) {
			Ad1Node ad1Node=new Ad1Node();
			ad1Node.setAlt(tbContent.getSubTitle());
			ad1Node.setHeight("240");
			ad1Node.setHeightB("240");
			ad1Node.setHref(tbContent.getUrl());
			ad1Node.setSrc(tbContent.getPic());
			ad1Node.setSrcB(tbContent.getPic2());
			ad1Node.setWidth("550");
			ad1Node.setWidth("550");
			list.add(ad1Node);
		}
		model.addAttribute("ad1", JsonUtils.objectToJson(list));
		return "index";
	}
}
