package com.zitec.workshopz.workshopz.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zitec.workshopz.workshopz.WorkshopClickListener;
import com.zitec.workshopz.workshopz.storage.adapters.WorkshopzCacheAdapter;
import com.zitec.workshopz.workshopz.views.ListFragmentView;

public class WorkshopzList extends Fragment {
	
	Activity activity;
	ListFragmentView viewClass;
	WorkshopClickListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		WorkshopzCacheAdapter cache = new WorkshopzCacheAdapter(this.activity);
		this.viewClass.setElements(cache.get());
		return this.viewClass.getView(inflater, container);
	}

	@Override
	public void onStart() {
		super.onStart();
		this.viewClass.initView();
		this.viewClass.setActions();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.viewClass = new ListFragmentView(this);
		this.activity = activity;
		
		if (activity instanceof WorkshopClickListener) {
			this.listener = (WorkshopClickListener) activity;
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
	
	public WorkshopClickListener getListener(){
		return this.listener;
	}
}
