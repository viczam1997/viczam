package com.taotao.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchItemService;

@Controller
public class SearchController {
   @Autowired
   private SearchItemService searchItemService;
   @RequestMapping(value="/search")
   public String search(Integer page,String q,Model model) throws Exception{
	   if(page==null)
		   page=1;
	   //处理乱码
	   q=new String(q.getBytes("iso-8859-1"),"utf-8");
	   SearchResult search = searchItemService.search(q, page, 60);
	   //设置数据传输到json
	   model.addAttribute("query", q);
	   model.addAttribute("totalPages", search.getPageCount());
	   model.addAttribute("itemList", search.getItemList());
	   model.addAttribute("page", page);
	   return "search";
   }
}
