package com.zitec.workshop2preps.workshopz.fragments;

import com.zitec.workshop2preps.R;
import com.zitec.workshop2preps.workshopz.storage.adapters.WorkshopzCacheAdapter;
import com.zitec.workshop2preps.workshopz.views.ListFragmentView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkshopzList extends Fragment {
	
	Activity activity;
	ListFragmentView viewClass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		WorkshopzCacheAdapter cache = new WorkshopzCacheAdapter(this.activity);
		this.viewClass.setElements(cache.get());
		this.viewClass.initView();
		
		return this.viewClass.getView(inflater, container);
	}

	public interface OnItemSelectedListener {
		public void onRssItemSelected(String link);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		 this.viewClass = new ListFragmentView(activity);
		this.activity = activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}
}
