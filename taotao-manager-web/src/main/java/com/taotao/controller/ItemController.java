package com.taotao.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.DeleteByPrimaryKey;
import com.taotao.service.ItemInsert;
import com.taotao.service.ItemService;
@Controller
public class ItemController {
	  @Autowired
	  private ItemService itemService;
	  @Autowired
	  private DeleteByPrimaryKey deleteByPrimaryKey;
	  @Autowired
	  private ItemInsert itemInsert;
	  //显示商品列表
	  @RequestMapping(value="/item/list",method=RequestMethod.GET)
	  public @ResponseBody
	  EasyUi getItemList(Integer page,Integer rows){
		  System.out.println("查询信息");
		  return itemService.getItemList(page,rows);
	  }
	  
	  //删除商品
	  @RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	  public @ResponseBody 
	  TaotaoResult delete(String ids){
		  TaotaoResult result=new TaotaoResult();
		  List<Long> list=new ArrayList<Long>();
		  String[] split=ids.split(",");
		  for (String string : split) {
			  result=deleteByPrimaryKey.deleteByPrimaryKey(Long.parseLong(string));
		}
		  return result;
	  }
	  
	  //显示编辑界面
	  @RequestMapping(value="/rest/page/item-edit")
	  public String showEdit(){
		  System.out.println("edit");
		  return "item-edit";
	  }
	  
	  //新增商品
	  @RequestMapping(value="/item/save",method=RequestMethod.POST)
	  public @ResponseBody
	  TaotaoResult addItem(TbItem tbitem){
		  tbitem.setId(111l);
		  tbitem.setCid(111l);
		  tbitem.setStatus((byte) 1);
		  tbitem.setCreated(new Date());
		  tbitem.setUpdated(new Date());
		  return  itemInsert.insert(tbitem);
	  }
	  
	  //修改商品
	  @RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	  public @ResponseBody
	  String editItem(TbItem tbitem){
		  System.out.println(tbitem);
		  return null;
	  }
}
