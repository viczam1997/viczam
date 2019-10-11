package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;

@Repository
public class SearchDao {
	@Autowired
	private SolrServer solrServer;
	public SearchResult search(SolrQuery query) throws Exception{
		SearchResult searchResult=new SearchResult();
		QueryResponse response=solrServer.query(query);
		SolrDocumentList results = response.getResults();
		searchResult.setRecordCount(results.getNumFound());
		List<SearchItem> itemList=new ArrayList<>();
		//取高亮
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		for (SolrDocument solrDocument : results) {
			SearchItem searchItem=new SearchItem();
			searchItem.setCategory_name(solrDocument.get("item_category_name").toString());
			searchItem.setId(Long.parseLong(solrDocument.get("id").toString()));
			searchItem.setImage(solrDocument.get("item_image").toString());
			searchItem.setPrice(Long.parseLong(solrDocument.get("item_price").toString()));
			searchItem.setSell_point(solrDocument.get("item_sell_point").toString());
			List<String> listhi = highlighting.get(solrDocument.get("id")).get("item_title");
			//是否高亮为空
			String gaoliang="";
			if(listhi!=null&&listhi.size()>0){
				gaoliang=listhi.get(0);
			}
			else{
				 gaoliang=solrDocument.get("item_title").toString();
			}
			searchItem.setTitle(gaoliang);
			itemList.add(searchItem);
		}
		searchResult.setItemList(itemList);
		return  searchResult;
	}
}
