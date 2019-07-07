package com.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pojo.QuestionDepository;
import com.service.TestMapper;
import com.serviceImpl.TestMapperImpl;
@Controller
public class TEst {
	@Autowired
	private TestMapperImpl testMapperImpl;
	@RequestMapping("/test")
	public void test(){
		List<QuestionDepository> findall = testMapperImpl.findall();
		for (QuestionDepository questionDepository : findall) {
			System.out.println(questionDepository);
			System.out.println(222);
			System.out.println(111);
			System.out.println(333);
			System.out.println(444);
		}
	}
}
