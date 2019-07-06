package com.service;

import com.pojo.UserInformation;

public interface UserService {
	public UserInformation login(String id,String password);
	public Boolean insert(UserInformation userInformation);
}
