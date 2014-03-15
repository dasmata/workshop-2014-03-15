package com.zitec.workshopz.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshopz.base.storage.mapper.BaseMapper;
import com.zitec.workshopz.user.entities.User;


public class UserMapper extends BaseMapper<User>{

	
	public User hydrate(HashMap<String, Object> data){
		return null;
	}
	
	public HashMap<String, Object> dehydrate(User obj){
		return null;
	}
}
