package com.taotao.search.serviceImpl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class TestSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		add();	
	}
    public static  void add() throws SolrServerException, IOException{
    	//创建solr连接
    	SolrServer server=new HttpSolrServer("http://192.168.35.113:8080/solr");
    	//solrinputdocument
    	SolrInputDocument document=new SolrInputDocument();
    	//向文档中添加域
    	document.addField("id", "test001");
    	document.addField("item_title", "测试");
    	//提交文件到索引库
		server.add(document);
		server.commit();
		System.out.println(1);
    }
}
