package com.taotao.search.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
@Service
public class SearchItemServiceImpl implements SearchItemService {
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer server;
	@Autowired
	private SearchDao searchDao;
	@Override
	public TaotaoResult importAllSearchItem() throws Exception {
		List<SearchItem> list=searchItemMapper.getSearchItemList();
		for (SearchItem searchItem : list) {
			SolrInputDocument document=new SolrInputDocument();
			document.addField("id", searchItem.getId().toString());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			server.add(document);
		}
		server.commit();
		return TaotaoResult.ok();
	}
	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
		SolrQuery query=new SolrQuery();
		//设置主查询条件
		if(StringUtils.isNotBlank(queryString)){
			query.setQuery(queryString);
		}
		else{
			query.setQuery("*:*");
		}
		//设置过滤条件和分页
		if(page==null) page=1;
		if(rows==null) rows=60;
		query.setStart((page-1)*rows);
		query.setRows(rows);
		query.set("df", "item_keywords");
		//设置高亮
		query.setHighlight(true);
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		query.addHighlightField("item_title");//设置高亮显示的域
		//调用Dao方法
		SearchResult search = searchDao.search(query);
		//设置总页数
		Long pageCout=0l;
		pageCout=search.getRecordCount()/rows;
		if(search.getRecordCount()%rows>0)
			pageCout++;
		search.setPageCount(pageCout);
		return search;
	}


}
