package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pojo.UserInformation;
import com.serviceImpl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		UserInformation login = userService.login((String)request.getParameter("id"),(String)request.getParameter("password"));
		if(login==null){
			return "/WEB-INF/jsp/loginFault.jsp";
		}
		else{
		request.getSession().setAttribute("id", (String)request.getParameter("id"));
		response.sendRedirect("/html/MainIndex.html");
		}
		return null;
	}
	@RequestMapping(value="/insert")
	public String insert(HttpServletRequest request,HttpServletResponse Response) throws Exception{
		UserInformation userInformation=new UserInformation();
		userInformation.setId(request.getParameter("id"));
		userInformation.setPassword(request.getParameter("password"));
		if(userInformation.getId()==null||userInformation.getId().equals(""))
			return "/WEB-INF/jsp/insertFault.jsp";
		userInformation.setStatus(Integer.parseInt(request.getParameter("status")));
		Boolean insert = userService.insert(userInformation);
		if(insert==false)
			return "/WEB-INF/jsp/insertFault.jsp";
		Response.sendRedirect("/html/login.html");
		return null;
	}
}
