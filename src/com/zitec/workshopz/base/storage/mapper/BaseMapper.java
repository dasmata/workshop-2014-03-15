package com.zitec.workshopz.base.storage.mapper;

import java.util.HashMap;

import com.zitec.workshopz.base.EntityResponseListener;
import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;

public abstract class BaseMapper<T> {
	public BaseStorageAdapter adapter;
	public EntityResponseListener listener;
	
	public void setAdapter(BaseStorageAdapter adapter){
		this.adapter = adapter;
		this.adapter.setStorageMapper(this);
	}
	
	public void setListener(EntityResponseListener listener){
		this.listener = listener;
	}
	
	public void find(HashMap<String, Object> params){
		this.adapter.find(params);
	}
	
	abstract public T hydrate(HashMap<String, Object> data);
	abstract public HashMap<String, Object> dehydrate(T obj);
}
