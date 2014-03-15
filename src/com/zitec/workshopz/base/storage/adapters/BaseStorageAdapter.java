package com.zitec.workshopz.base.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;

import android.content.Context;

public abstract class BaseStorageAdapter {

	protected Context context;
	protected BaseStorageMapper mapper;
	
	public BaseStorageAdapter(Context ctx){
		this.context = ctx;
	}
	
	public void setStorageMapper(BaseStorageMapper mapper){
		this.mapper = mapper;
	}
	
	public BaseStorageMapper getStorageMapper(){
		return this.mapper;
	}
	
	
	public void find(HashMap<String, Object> params){
		
	}
	
	public void save(HashMap<String, String> data) {
	
	}
	
	public void getAll() {
		
	}
}
