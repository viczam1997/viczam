package com.taotao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemInsert;
@Service
public class ItemInsertImpl implements ItemInsert {
	@Autowired
	TbItemMapper tbItemMapper;
	@Override
	public TaotaoResult insert(TbItem record) {
		tbItemMapper.insert(record);
		return TaotaoResult.ok(record);
	}

}
