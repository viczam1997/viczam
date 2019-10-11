package com.taotao.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContent;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list=tbContent.selectByExample(example);
		List<EasyUITreeNode> nodes=new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
			easyUITreeNode.setId(tbContentCategory.getId());
			easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			easyUITreeNode.setText(tbContentCategory.getName());
			nodes.add(easyUITreeNode);
		}
		return nodes;
	}
	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TbContentCategory category=new TbContentCategory();
		category.setCreated(new Date());
		category.setIsParent(false);
		category.setName(name);
		category.setParentId(parentId);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setUpdated(category.getCreated());
		tbContent.insert(category);
		TbContentCategory parent=tbContent.selectByPrimaryKey(parentId);
		if(parent.getIsParent()==false)
			parent.setIsParent(true);
		tbContent.updateByPrimaryKeySelective(parent);
		return TaotaoResult.ok(category);
	}
	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategory category=new TbContentCategory();
		category=tbContent.selectByPrimaryKey(id);
		category.setUpdated(new Date());
		category.setName(name);
		tbContent.updateByPrimaryKeySelective(category);
		TaotaoResult taotaoResult=new TaotaoResult(category);
		return taotaoResult;
	}
	@Override
	public String deleteContent(Long id){
		TbContentCategory category=tbContent.selectByPrimaryKey(id);
		if(category.getIsParent()==false){
			tbContent.deleteByPrimaryKey(id);
			return "OK";
		}
		return "FALSE";
	}
}
