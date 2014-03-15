package com.zitec.workshopz.workshopz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzCacheAdapter;
import com.zitec.workshopz.workshopz.views.WorkshopDetailView;

public class WorkshopDetail extends Fragment {
	
	public static final String WORKSHOP_ID = "id";
	protected WorkshopDetailView fragmentView;
	protected boolean viewInitialized;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.fragmentView = new WorkshopDetailView(this);
		View view = this.fragmentView.getView(inflater, container);
		this.fragmentView.initView();
        
		return view;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Bundle args = this.getArguments();
        if (args != null) {
        	setWorkshopId(args.getInt(WORKSHOP_ID));
        }
	}
	
	public void setWorkshopId(int id){
		WorkshopzCacheAdapter cache = new WorkshopzCacheAdapter(this.getActivity());
		this.fragmentView.setWorkshop(cache.get(id));
		  
	}
}
