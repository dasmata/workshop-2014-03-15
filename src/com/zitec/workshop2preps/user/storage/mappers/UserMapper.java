package com.zitec.workshop2preps.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshop2preps.base.BaseEntity;
import com.zitec.workshop2preps.base.BaseStorageMapper;
import com.zitec.workshop2preps.user.entities.User;
import com.zitec.workshop2preps.user.storage.adapters.UserWSAdapter;

public class UserMapper extends BaseStorageMapper{
	
	public void getEntity(String username, String password){
		((UserWSAdapter)this.adapter).getEntity(username, password);
	}
	
	@Override
	public BaseEntity hydrate(HashMap<String, String> data) {
		User usr = new User();
		usr.setEmail(data.get("email"));
		usr.setRemoteId(data.get("remote_id"));
		usr.setName(data.get("name"));
		usr.setPhoneNumber(data.get("phone_number"));
		usr.setPosition(data.get("position"));
		usr.setId(data.get("id"));
		usr.setCurrentIdentity(data.get("current_identity"));
		return usr;
	}
	
	@Override
	public HashMap<String, String> dehydrate(BaseEntity data){
		User usr = (User) data;
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("email", usr.getEmail());
		result.put("id", usr.getId());
		result.put("name", usr.getName());
		result.put("phone_number", usr.getPhoneNumber());
		result.put("position", usr.getPosition());
		result.put("remote_id", usr.getRemoteId());
		result.put("current_identity", usr.getCurrentIdentity());
		return result;
	}

	public void checkUser(User usr){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("email", usr.getEmail());
		this.adapter.find(params);
	}
}
