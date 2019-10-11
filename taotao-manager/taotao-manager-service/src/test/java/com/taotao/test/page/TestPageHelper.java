package com.taotao.test.page;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	@org.junit.Test
   public void Test(){
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper tbItemMapper=applicationContext.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 3);//只有紧跟的第一个查询有效
		TbItemExample example=new TbItemExample();
		List<TbItem> list=tbItemMapper.selectByExample(example);
		List<TbItem> list2=tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageinfo=new PageInfo<>(list);
		System.out.println("第一个list集合长度"+list.size());
		System.out.println("第二个list集合长度"+list2.size());
		System.out.println("查询记录数"+pageinfo.getTotal());
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getId()+">>>>>>>>"+tbItem.getTitle());
		}
	}
}
