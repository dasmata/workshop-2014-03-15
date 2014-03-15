package com.zitec.workshopz.workshopz.views;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zitec.workshopz.R;
import com.zitec.workshopz.base.BaseView;
import com.zitec.workshopz.user.entities.User;
import com.zitec.workshopz.workshopz.entities.Workshop;
import com.zitec.workshopz.workshopz.fragments.WorkshopDetail;

public class WorkshopDetailView extends BaseView {

	LinearLayout view;
	TextView name;
	TextView theme;
	TextView date;
	LinearLayout workshopSpeakers;
	Activity act;
	Fragment fragment;
	Workshop item;
	
	public WorkshopDetailView(WorkshopDetail fragment){
		this.act = fragment.getActivity();
		this.fragment = fragment;
	}
	
	@Override
	public void initView() {
		this.name = (TextView) this.view.findViewById(R.id.workshopName);
		this.theme = (TextView) this.view.findViewById(R.id.workshopTheme);
		this.date = (TextView) this.view.findViewById(R.id.workshopDate);
		this.workshopSpeakers = (LinearLayout)this.view.findViewById(R.id.workshopSpeakers);
	}

	public void populateLayout(){
		this.name.setText(this.item.getName());
		this.theme.setText(this.item.getTheme());
		String date = this.item.getStartDate().toLocaleString();
		this.date.setText(date);
		this.workshopSpeakers.removeAllViews();
		this.addSpeakers();
	}
	
	public void addSpeakers(){
		LayoutInflater inflater = this.act.getLayoutInflater();
		LinearLayout speakerLayout;
		for(User speaker : this.item.getSpeakers()){
			speakerLayout = (LinearLayout)inflater.inflate(R.layout.workshop_speaker, null);
			((TextView)speakerLayout.findViewById(R.id.speakerName)).setText(speaker.getName());
			((TextView)speakerLayout.findViewById(R.id.speakerPosition)).setText(speaker.getPosition());
			((TextView)speakerLayout.findViewById(R.id.speakerPhone)).setText(speaker.getPhoneNumber());
			((TextView)speakerLayout.findViewById(R.id.speakerEmail)).setText(speaker.getEmail());
			this.workshopSpeakers.addView(speakerLayout);
		}
		
	}
	
	@Override
	public void setActions() {
		// TODO Auto-generated method stub

	}

	public View getView(LayoutInflater inflater, ViewGroup container){
		View view = inflater.inflate(R.layout.fragment_workshop_detail, null, false);
		this.view = (LinearLayout) view;
		return view;
	}
	
	public void setWorkshop(Workshop item){
		this.item = item;
		this.populateLayout();
	}
}
