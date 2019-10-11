package com.taotao.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobelException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception ex) {
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		ModelAndView andView=new ModelAndView();
		andView.setViewName("error/exception");
		andView.addObject("message", "网络异常");
		return andView;
	}

}
