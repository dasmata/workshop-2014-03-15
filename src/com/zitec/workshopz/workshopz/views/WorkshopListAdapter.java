package com.zitec.workshopz.workshopz.views;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zitec.workshopz.R;
import com.zitec.workshopz.workshopz.entities.Workshop;

public class WorkshopListAdapter extends ArrayAdapter<Workshop> {

	Context ctx;
	ArrayList<Workshop> items;
	OnClickListener clickListener;
	
	public WorkshopListAdapter(Context context, int resource, int textViewResourceId, ArrayList<Workshop> objects) {
		super(context, resource, textViewResourceId, objects);
		this.ctx = context;
		this.items = objects;
	}

	
	@Override
	public View getView(int position, View recycledView, ViewGroup parent){
		View elementView;
		if(recycledView == null){
			elementView = this.createElementView();
		} else {
			elementView = recycledView;
		}
		
		TextView name = (TextView) elementView.findViewById(R.id.workshopName);
		TextView date = (TextView) elementView.findViewById(R.id.workshopDate);
		
		name.setText(this.items.get(position).getName());
		date.setText(this.items.get(position).getStartDate().toLocaleString());
		elementView.setTag(this.items.get(position).getRemoteId());
		
		return elementView;
	}
	
	public void setClickListener(OnClickListener listener){
		this.clickListener = listener;
	}
	
	public View createElementView(){
		LayoutInflater inflater = LayoutInflater.from(this.ctx);
		View layout = inflater.inflate(R.layout.workshop_list_element, null);
		layout.setOnClickListener(clickListener);
		return layout;
	}
}
