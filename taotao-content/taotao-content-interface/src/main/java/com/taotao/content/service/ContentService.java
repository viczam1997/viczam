package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;

public interface ContentService {
    public EasyUi getContentList(Long categoryId,Integer page,Integer rows);
	public TaotaoResult addContent(TbContent tbContent);
	public List<TbContent> getContentByCategoryId(Long categoryId);
}
