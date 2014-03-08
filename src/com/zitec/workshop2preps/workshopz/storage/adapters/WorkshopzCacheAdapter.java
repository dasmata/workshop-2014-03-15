package com.zitec.workshop2preps.workshopz.storage.adapters;

import android.content.Context;
import android.util.SparseArray;

import com.zitec.workshop2preps.base.storage.adapters.BaseStorageAdapter;
import com.zitec.workshop2preps.workshopz.entities.Workshop;

public class WorkshopzCacheAdapter extends BaseStorageAdapter {

	protected static SparseArray<Workshop> cache;
	
	public WorkshopzCacheAdapter(Context ctx) {
		super(ctx);
		if(WorkshopzCacheAdapter.cache == null){
			WorkshopzCacheAdapter.cache = new SparseArray<Workshop>();
		}
	}
	
	public void save(Workshop obj){
		int id = Integer.valueOf(obj.getRemoteId());
		WorkshopzCacheAdapter.cache.append(id, obj);
	}

	public Workshop get(Integer index){
		return WorkshopzCacheAdapter.cache.get(index);
	}
	
	public SparseArray<Workshop> get(){
		return WorkshopzCacheAdapter.cache;
	}
}
