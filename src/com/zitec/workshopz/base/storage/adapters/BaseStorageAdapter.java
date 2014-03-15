package com.zitec.workshopz.base.storage.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.zitec.workshopz.base.BaseEntity;
import com.zitec.workshopz.base.BaseStorageMapper;
import com.zitec.workshopz.base.storage.mapper.BaseMapper;

import android.content.Context;

public abstract class BaseStorageAdapter {

	protected Context context;
	protected BaseMapper mapper;
	
	public BaseStorageAdapter(Context ctx){
		this.context = ctx;
	}
	
	public void setStorageMapper(BaseMapper baseMapper){
		this.mapper = baseMapper;
	}
	
	public BaseMapper getStorageMapper(){
		return this.mapper;
	}
	
	
	public void find(HashMap<String, Object> params){
		
	}
	
	public void save(HashMap<String, String> data) {
	
	}
	
	public void getAll() {
		
	}
}
