package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.pojo.UserInformation;
import com.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	@Override
	public UserInformation login(String id,String password) {
		UserInformation login = usermapper.login(id);
		if(login==null)
			return null;//没找到该账号，账号为空
		if(login.getPassword().equals(password))
			return login;
		return null;//密码错误
	}
	@Override
	public Boolean insert(UserInformation userInformation) {
		UserInformation login = usermapper.login(userInformation.getId());
		if(login!=null){
			return false;
		}
		usermapper.insert(userInformation);
		return true;
	}

}
