package com.mapper;

import java.util.List;

import com.pojo.QuestionDepository;

public interface QuestionMapper {
	public List<QuestionDepository> getQuestion(Integer numbers);
	public void insertQuestion(QuestionDepository question);
	public List<QuestionDepository> findOneQuestion(QuestionDepository question);
}
