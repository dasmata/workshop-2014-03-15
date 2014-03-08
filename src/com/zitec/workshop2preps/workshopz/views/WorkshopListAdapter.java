package com.zitec.workshop2preps.workshopz.views;

import java.util.List;

import com.zitec.workshop2preps.workshopz.entities.Workshop;

import android.content.Context;
import android.widget.ArrayAdapter;

public class WorkshopListAdapter extends ArrayAdapter<Workshop> {

	public WorkshopListAdapter(Context context, int resource, int textViewResourceId, List<Workshop> objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
