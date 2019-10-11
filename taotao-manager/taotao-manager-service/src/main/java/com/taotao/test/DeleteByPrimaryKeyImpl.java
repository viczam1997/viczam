package com.taotao.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.service.DeleteByPrimaryKey;
@Service
public class DeleteByPrimaryKeyImpl implements DeleteByPrimaryKey {
	@Autowired
	private TbItemMapper ibItemMapper;
	@Override
	public TaotaoResult deleteByPrimaryKey(Long id) {
		ibItemMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}
