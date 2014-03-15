package com.zitec.workshopz.base;

import java.util.ArrayList;
import java.util.HashMap;

import com.zitec.workshopz.base.storage.Error;
import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;

public abstract class BaseStorageMapper {

	protected BaseStorageAdapter adapter;
	protected EntityResponseListener listener;

	public BaseStorageAdapter getAdapter() {
		return adapter;
	}

	public void setListener(EntityResponseListener listener)
	{
		this.listener = listener;
	}
	
	public BaseStorageMapper setAdapter(BaseStorageAdapter adapter) {
		this.adapter = adapter;
		this.adapter.setStorageMapper(this);
		return this;
	}
	
	public ArrayList<BaseEntity> hydrate(ArrayList<HashMap<String, String>> data) {
		ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>(); 
		for(HashMap<String, String> item : data){
			entities.add(this.hydrate(item));
		}
		return entities;
	}
	
	public void onSuccess(HashMap<String, String> data){
		ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>();
		entities.add(this.hydrate(data));
		this.listener.onSuccess(entities);
	}
	
	public void onSuccess(ArrayList<HashMap<String, String>> data){
		this.listener.onSuccess(this.hydrate(data));
	}
	
	public void onError(String message){
		this.listener.onError(new Error(message));
	}
	
	public void save(BaseEntity obj){
		this.adapter.save(this.dehydrate(obj));
	}
	
	public void find(HashMap<String, Object> params){
		this.adapter.find(params);
	}
	
	public void getAll(){
		this.adapter.getAll();
	}
	
	abstract public BaseEntity hydrate(HashMap<String, String> data);
	abstract public HashMap<String, String> dehydrate(BaseEntity data);
}
