package com.zitec.workshopz.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.user.storage.adapters.UserWSAdapter;


public class UserMapper extends BaseStorageMapper {
	
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
	public HashMap<String, String> dehydrate(BaseEntity usr)
	{
		User user = (User)usr;
		HashMap<String, String> data = new HashMap<String, String>();

		data.put("email", user.getEmail());
		data.put("name", user.getName());
		data.put("phone_number", user.getPhoneNumber());
		data.put("position", user.getPosition());
		
		if (user.getRemoteId() != null) {
			data.put("remote_id", user.getRemoteId());
		}
		
		if (user.getPassword() != null) {
			data.put("password", user.getPassword());
		}
		
		return data;
	}
}
