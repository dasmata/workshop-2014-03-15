package com.zitec.workshopz.base.storage.adapters;

import java.util.HashMap;

import android.content.Context;

import com.zitec.workshopz.base.BaseStorageMapper;

public abstract class BaseStorageAdapter {

	protected Context context;
	protected BaseStorageMapper mapper;
	
	public BaseStorageAdapter(Context ctx){
		this.context = ctx;
	}
	
	public void setStorageMapper(BaseStorageMapper baseMapper){
		this.mapper = baseMapper;
	}
	
	public BaseStorageMapper getStorageMapper(){
		return this.mapper;
	}
	
	
	public void find(HashMap<String, String> params){
		
	}
	
	public void save(HashMap<String, String> data) {
	
	}
	
	public void getAll() {
		
	}
}
