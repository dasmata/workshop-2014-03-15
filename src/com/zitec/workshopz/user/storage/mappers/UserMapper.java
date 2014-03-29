package com.zitec.workshopz.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.storage.adapters.UserWSAdapter;


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
		return usr;
	}

	@Override
	public HashMap<String, String> dehydrate(BaseEntity data) {
		User user = (User) data;
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("email", user.getEmail());
		values.put("name", user.getName());
		values.put("phone_number", user.getPhoneNumber());
		values.put("position", user.getPosition());
		if(user.hasPassword()) {
			values.put("password", user.getPassword());
		}
		return values;
	}
}
