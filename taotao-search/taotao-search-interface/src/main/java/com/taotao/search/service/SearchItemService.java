package com.taotao.search.service;


import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
	public TaotaoResult importAllSearchItem() throws Exception;
	//搜索的结果
	public SearchResult search(String queryString,Integer page, Integer rows) throws Exception;
}
