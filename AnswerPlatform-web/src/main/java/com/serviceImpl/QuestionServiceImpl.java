package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.QuestionMapper;
import com.pojo.QuestionDepository;
import com.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public List<QuestionDepository> getQuestion(Integer numbers) {
		if(numbers<=0)
			return null;
		return questionMapper.getQuestion(numbers);
	}
	@Override
	public boolean insertQuestion(QuestionDepository question) {
			questionMapper.insertQuestion(question);
			List<QuestionDepository> findOneQuestion = questionMapper.findOneQuestion(question);
			if(findOneQuestion==null||findOneQuestion.size()==0)
				return false;
		return true;
	}

}
