package com.controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pojo.QuestionDepository;
import com.serviceImpl.QuestionServiceImpl;
@Controller
public class questionController {
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	@RequestMapping("/getQuestion")
	public String getQuestionPlatform(Model model,HttpServletRequest request){
		List<QuestionDepository> findall = questionServiceImpl.getQuestion(3);
		if(findall==null)
			return "/WEB-INF/jsp/answerPlat.jsp";
		List<String> answer=new ArrayList<>();
		for(int i=0;i<findall.size();i++)
			answer.add(findall.get(i).getQuestion());
		request.getSession().setAttribute("myAnswer", findall);
		model.addAttribute("QuestionDepository",answer);
		return "/WEB-INF/jsp/answerPlat.jsp";
	}
	@RequestMapping("/getScore")
	@ResponseBody
	public  String getScore(String answer,HttpServletRequest request,HttpServletResponse response){
		String result="";
		Integer score=0;
		List<QuestionDepository> attribute = (List<QuestionDepository>)request.getSession().getAttribute("myAnswer");
		String[] s=answer.split(",");
		for(int i=0;i<s.length;i++)
		{
			result=result+attribute.get(i).getQuestion()+" ";
			if(attribute.get(i).getAnswer().equals(s[i]))
			{
				score=score+10;
				result=result+"true";
			}
			else{
				result=result+"false";
			}
			result=result+"\n";
		}
		result=result+"score:"+score;
		return result;
	}
}
