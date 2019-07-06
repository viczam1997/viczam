package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pojo.QuestionDepository;
import com.serviceImpl.QuestionServiceImpl;

@Controller
public class TeacherQuestionController {
	@Autowired
	private QuestionServiceImpl questionService;
	@RequestMapping("/InsertPlatform")
	public String gotoInsertPlatform(){
		return "/WEB-INF/jsp/insertQuestion.jsp";
	}
	@RequestMapping("/insertQuestion")
	@ResponseBody
	public String insertQuestion(QuestionDepository question){
		if(!questionService.insertQuestion(question))
			return "false";
		return "success";
	}
}
