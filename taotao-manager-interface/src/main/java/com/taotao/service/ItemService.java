package com.taotao.service;

import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

//商品处理的接口
public interface ItemService {
	//根据当前页码和每页行数分页查询
	public EasyUi getItemList(Integer page,Integer rows);
	/*public TaotaoResult saveItem(TbItem item, String desc);*/
}
