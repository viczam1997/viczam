package com.service;

import java.util.List;

import com.pojo.QuestionDepository;

public interface QuestionService {
	public List<QuestionDepository> getQuestion(Integer numbers);
	public boolean insertQuestion(QuestionDepository question); 
}
