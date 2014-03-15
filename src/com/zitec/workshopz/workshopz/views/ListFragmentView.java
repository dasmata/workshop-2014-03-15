package com.zitec.workshopz.workshopz.views;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseView;
import com.zitec.workshopz.workshopz.entities.Workshop;
import com.zitec.workshopz.workshopz.fragments.WorkshopzList;

public class ListFragmentView extends BaseView {

	ArrayList<Workshop> elements;
	Activity act;
	WorkshopzList fragment;
	ListView lst;
	WorkshopListAdapter listAdapter;
	
	public ListFragmentView(WorkshopzList fragment){
		this.act = fragment.getActivity();
		this.fragment = fragment;
	}
	
	@Override
	public void initView() {
		this.lst = (ListView)this.act.findViewById(R.id.workshopzList);
		
		this.listAdapter = new WorkshopListAdapter(
			this.act,
			R.layout.workshop_list_element,
			R.id.workshopName,
			this.elements
		);
		
		this.lst.setAdapter(this.listAdapter);
	}

	@Override
	public void setActions() {
		this.listAdapter.setClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ListFragmentView.this.fragment.getListener().workshopListClick(Integer.valueOf(String.valueOf(v.getTag())));
			}
			
		});
		this.fragment.getListener().workshopListClick(-1);
	}

	public void setElements(ArrayList<Workshop> elements){
		this.elements = elements;
	}
	
	public View getView(LayoutInflater inflater, ViewGroup container){
		View view = inflater.inflate(R.layout.fragment_workshopz_list, container, false);
		return view;
	}
}
