package com.taotao.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUi;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private TbItemMapper mapper;
	/*@Autowired
	private TbItemDescMapper descmapper;
	
	@Autowired
	private JmsTemplate jmstemplate;
	
	@Resource(name="topicDestination")
	private Destination destination;*/
	@Override
	public EasyUi getItemList(Integer page, Integer rows) {
		if(page==null) page=1;
		if(rows==null) rows=30;
		//设置分页选项
		PageHelper.startPage(page,rows);
		//设置查询信息样例
		TbItemExample example=new TbItemExample();
		List<TbItem> list=mapper.selectByExample(example);
		//将分页结果信息传入PageInfo信息中
		PageInfo<TbItem> info=new PageInfo<TbItem>(list);
		//设置页面分页回传json格式
		EasyUi easyui=new EasyUi();
		easyui.setTotal((int)info.getTotal());
		easyui.setRows(info.getList());
		return easyui;
	}
	/*@Override
	public TaotaoResult saveItem(TbItem item, String desc) {
		// 生成商品的id
		final long itemId = IDUtils.genItemId();
		// 1.补全item 的其他属性
		item.setId(itemId);
		item.setCreated(new Date());
		// 1-正常，2-下架，3-删除',
		item.setStatus((byte) 1);
		item.setUpdated(item.getCreated());
		// 2.插入到item表 商品的基本信息表
		mapper.insertSelective(item);
		// 3.补全商品描述中的属性
		TbItemDesc desc2 = new TbItemDesc();
		desc2.setItemDesc(desc);
		desc2.setItemId(itemId);
		desc2.setCreated(item.getCreated());
		desc2.setUpdated(item.getCreated());
		// 4.插入商品描述数据
		// 注入tbitemdesc的mapper
		descmapper.insertSelective(desc2);

		// 添加发送消息的业务逻辑
		jmstemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				//发送的消息
				return session.createTextMessage(itemId+"");
			}
		});
		// 5.返回taotaoresult
		return TaotaoResult.ok();
	}
	*/
}
