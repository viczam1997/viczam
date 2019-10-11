package com.taotao.serviceimpl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClientPool;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper tbContentMapper;
	@Autowired 
	JedisClientPool jedisClientPool;
	@Override
	//显示已有内容
	public EasyUi getContentList(Long categoryId,Integer page,Integer rows) {
		if(page==null) page=1;
		if(rows==null) rows=30;
		//设置分页选项
		PageHelper.startPage(page,rows);
		//设置查询信息样例
		TbContentExample example=new TbContentExample();
		List<TbContent> list=tbContentMapper.selectByExample(example);
		//将分页结果信息传入PageInfo信息中
		PageInfo<TbContent> info=new PageInfo<TbContent>(list);
		//设置页面分页回传json格式
		EasyUi easyui=new EasyUi();
		easyui.setTotal((int)info.getTotal());
		easyui.setRows(info.getList());
		return easyui;
	}
	@Override
	public TaotaoResult addContent(TbContent tbContent){
		tbContent.setCreated(new Date());
		tbContent.setUpdated(tbContent.getCreated());
		tbContentMapper.insert(tbContent);
		try {
			jedisClientPool.hdel("CONTENT_KEY_LUNBOTU", tbContent.getCategoryId()+"");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}
	@Override
	//查询轮播图信息,加轮播图缓存
	public List<TbContent> getContentByCategoryId(Long categoryId) {
		try {
			String jsonString=jedisClientPool.hget("CONTENT_KEY_LUNBOTU", categoryId+"");
			if(StringUtils.isNotBlank(jsonString))
			{
				System.out.println("有缓存");
				return JsonUtils.jsonToList(jsonString,TbContent.class);
			}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		TbContentExample contentExample=new TbContentExample();
		contentExample.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list=tbContentMapper.selectByExample(contentExample);
		try{
			System.out.println("没有缓存");
			jedisClientPool.hset("CONTENT_KEY_LUNBOTU", categoryId+"", JsonUtils.objectToJson(list));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
