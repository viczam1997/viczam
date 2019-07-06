package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListQuestion {
	private List<QuestionDepository> list=new ArrayList<>();

	public List<QuestionDepository> getList() {
		return list;
	}

	public void setList(List<QuestionDepository> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ListQuestion [list=" + list + "]";
	}
	
}
