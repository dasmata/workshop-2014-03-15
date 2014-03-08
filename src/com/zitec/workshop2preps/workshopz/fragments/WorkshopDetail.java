package com.zitec.workshop2preps.workshopz.fragments;

import com.zitec.workshop2preps.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkshopDetail extends Fragment {

	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_workshop_detail, container, false);
	    return view;
	  }
	
}
