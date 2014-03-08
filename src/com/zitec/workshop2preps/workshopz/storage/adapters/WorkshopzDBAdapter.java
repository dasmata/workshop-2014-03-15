package com.zitec.workshop2preps.workshopz.storage.adapters;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.zitec.workshop2preps.base.storage.adapters.BaseDbStorageAdapter;

public class WorkshopzDBAdapter extends BaseDbStorageAdapter {

	public WorkshopzDBAdapter(Context ctx) throws NameNotFoundException {
		super(ctx);
	}

}
