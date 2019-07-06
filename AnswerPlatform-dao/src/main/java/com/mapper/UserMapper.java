package com.mapper;

import com.pojo.UserInformation;

public interface UserMapper {
	public UserInformation login(String id);
	public void insert(UserInformation userinformation);
}
