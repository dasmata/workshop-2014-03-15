package com.zitec.workshopz.user.storage.mappers;

import java.util.HashMap;

import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;


public class UserMapper {

	public BaseStorageAdapter adapter;
	public ResponseListener listener;
	
	public void setAdapter(BaseStorageAdapter adapter){
		this.adapter = adapter;
	}
	
	public void setListener(ResponseListener listener){
		this.listener = listener;
	}
	
	public void find(HashMap<String, String> params){
		this.adapter.find(params);
	}
}
