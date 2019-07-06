package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.Test;
import com.pojo.QuestionDepository;
@Service
public class TestMapperImpl implements Test {
	@Autowired
	private Test test;
	@Override
	public List<QuestionDepository> findall() {
		
		return test.findall();
	}
	

}
