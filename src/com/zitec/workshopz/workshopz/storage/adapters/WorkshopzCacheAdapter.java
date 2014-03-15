package com.zitec.workshopz.workshopz.storage.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.SparseIntArray;

import com.zitec.workshopz.base.storage.adapters.BaseStorageAdapter;
import com.zitec.workshopz.workshopz.entities.Workshop;

public class WorkshopzCacheAdapter extends BaseStorageAdapter {

	protected static SparseIntArray idIndex;
	protected static ArrayList<Workshop> cache;
	
	public WorkshopzCacheAdapter(Context ctx) {
		super(ctx);
		if(WorkshopzCacheAdapter.cache == null){
			WorkshopzCacheAdapter.idIndex = new SparseIntArray();
			WorkshopzCacheAdapter.cache = new ArrayList<Workshop>();
		}
	}
	
	public void clear(){
		WorkshopzCacheAdapter.idIndex = new SparseIntArray();
		WorkshopzCacheAdapter.cache = new ArrayList<Workshop>();
	}
	
	public void save(Workshop obj){
		int id = Integer.valueOf(obj.getRemoteId());
		WorkshopzCacheAdapter.cache.add(obj);
		WorkshopzCacheAdapter.idIndex.append(id, (WorkshopzCacheAdapter.cache.size()-1));
	}

	public Workshop get(Integer index){
		return WorkshopzCacheAdapter.cache.get(WorkshopzCacheAdapter.idIndex.get(index));
	}
	
	public ArrayList<Workshop> get(){
		return WorkshopzCacheAdapter.cache;
	}
}
