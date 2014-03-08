package com.zitec.workshop2preps.workshopz.views;

import android.app.Activity;
import android.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zitec.workshop2preps.R;
import com.zitec.workshop2preps.base.BaseView;
import com.zitec.workshop2preps.workshopz.entities.Workshop;
import com.zitec.workshop2preps.workshopz.fragments.WorkshopzList;

public class ListFragmentView extends BaseView {

	SparseArray<Workshop> elements;
	Activity act;
	WorkshopzList fragment;
	ListView lst;
	
	public ListFragmentView(WorkshopzList fragment){
		this.act = fragment.getActivity();
		this.fragment = fragment;
	}
	
	@Override
	public void initView() {
		this.lst = (ListView)this.act.findViewById(R.id.workshopzList);
		
		WorkshopListAdapter adapter = new WorkshopListAdapter(
			this,
			R.layout.meteo_element,
			R.id.meteo_element,
			this.elements
		);
		
		this.lst.setAdapter(adapter);
	}

	@Override
	public void setActions() {
		// TODO Auto-generated method stub

	}

	public void setElements(SparseArray<Workshop> elements){
		this.elements = elements;
	}
	
	public View getView(LayoutInflater inflater, ViewGroup container){
		View view = inflater.inflate(R.layout.fragment_workshopz_list, container, false);
		return view;
	}
}
