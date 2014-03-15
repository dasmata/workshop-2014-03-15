package com.zitec.workshopz.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;
import com.zitec.workshopz.user.entities.User;


public class UserMapper extends BaseStorageMapper{

	
	public User hydrate(HashMap<String, Object> data){
		return null;
	}
	
	public HashMap<String, Object> dehydrate(User obj){
		return null;
	}

	@Override
	public BaseEntity hydrate(HashMap<String, String> data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> dehydrate(BaseEntity data) {
		// TODO Auto-generated method stub
		return null;
	}
}
